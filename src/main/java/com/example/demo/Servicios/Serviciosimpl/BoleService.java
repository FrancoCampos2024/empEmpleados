package com.example.demo.Servicios.Serviciosimpl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entidades.Beneficio;
import com.example.demo.Entidades.Boletapago;
import com.example.demo.Entidades.Contrato;
import com.example.demo.Repositorio.BeneEmpleaRepositorio;
import com.example.demo.Repositorio.BoletaPagoRepositorio;
import com.example.demo.Repositorio.ContratoRepositorio;

import jakarta.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service("boleService")
public class BoleService {
    @Autowired
    private ContratoRepositorio contratoRepository;

    @Autowired
    private BoletaPagoRepositorio boletaPagoRepository;

    @Autowired
    private BeneEmpleaRepositorio beneEmpleadoRepository;

    // Verifica los empleados listos para generar boleta
    public List<Contrato> verificarBoletasPendientes() {
        List<Contrato> contratos = contratoRepository.findAll();
        List<Contrato> contratosPendientes = new ArrayList<>();

        for (Contrato contrato : contratos) {
            if (esContratoActivo(contrato) && estaListoParaBoleta(contrato)) {
                contratosPendientes.add(contrato);
            }
        }
        return contratosPendientes;
    }

    private boolean esContratoActivo(Contrato contrato) {
        Boolean estadoContrato = false;
        System.out.println("Estado contrato: " + contrato.getContEstadoContrato());
        if (contrato.getContEstadoContrato() == 1) {
            estadoContrato = true;

        }
        return estadoContrato;
    }

    private boolean estaListoParaBoleta(Contrato contrato) {
        Date fecInicio = contrato.getContFecIni();
        if (fecInicio == null) {
            System.err.println("Advertencia: La fecha de inicio del contrato es null para el contrato ID: "
                    + contrato.getIdContrato());
            return false;
        }
        Date ultimaFechaBoletaSql = boletaPagoRepository.findUltimaFechaByContrato(contrato.getIdContrato())
                .orElse(fecInicio);

        if (ultimaFechaBoletaSql == null) {

            System.err.println(
                    "Advertencia: La fecha de última boleta (o inicio de contrato) es null para el contrato ID: "
                            + contrato.getIdContrato());
            return false;
        }
        LocalDate fechaUltimaBoleta = ultimaFechaBoletaSql.toLocalDate();

        LocalDate fechaSiguienteBoleta = fechaUltimaBoleta.plusDays(27);
        System.out.println("Fecha siguiente boleta: " + fechaSiguienteBoleta);
        System.out.println("Fecha : " + LocalDate.now().isAfter(fechaSiguienteBoleta));
        return LocalDate.now().isAfter(fechaSiguienteBoleta);
    }

    public Boletapago generarBoleta(Contrato contrato) {
        LocalDate fechaActual = LocalDate.now();
        Date fecActual = Date.valueOf(fechaActual);
        double salarioBase = contrato.getContSalario();
        String nombreEmpleado = contrato.getContempleado().getEmpNombre();

        // Beneficios aplicables según el mes
        List<Beneficio> beneficios = beneEmpleadoRepository.findByEmpleadoAndMesBeneficio(
                contrato.getContempleado().getIdEmpleado(), fechaActual.getMonthValue() - 1);

        System.out.println("Mes actual: " + fechaActual.getMonthValue());
        beneficios = beneficios.stream()
                .filter(b -> Beneficio.esBeneficioAplicable(b, fechaActual.getMonth()))
                .collect(Collectors.toList());

        System.out.println("Beneficios: " + beneficios);
        double totalBeneficios = beneficios.stream()
                .mapToDouble(Beneficio::getBenMonto)
                .sum();

        Boletapago boleta = new Boletapago();
        boleta.setBolContrato(contrato);
        boleta.setBolFecEmisionBole(fecActual);
        boleta.setBolMontoFijo(salarioBase);
        boleta.setBolMontoBeneficio(totalBeneficios);
        boleta.setBolMontoNetoBole(salarioBase + totalBeneficios);
        boleta.setBeneficiosAplicados(generarDescripcionBeneficios(beneficios));

        boletaPagoRepository.save(boleta);

        System.out.println("Boleta generada para: " + nombreEmpleado);
        return boleta;
    }

    private String generarDescripcionBeneficios(List<Beneficio> beneficios) {
        return beneficios.stream()
                .map(b -> b.getBenDescripcion() + ": S/" + b.getBenMonto())
                .collect(Collectors.joining(", "));
    }

    public void generarBoletaPDF(int idBoleta, HttpServletResponse response) throws IOException {
        Boletapago boleta = boletaPagoRepository.findById(idBoleta)
                .orElseThrow(() -> new IllegalArgumentException("Boleta no encontrada."));

        String bancoContrato = contratoRepository.buscarBanco(boleta.getBolContrato().getIdContrato());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=Boleta_Pago_" + boleta.getBolContrato().getContempleado().getEmpNombre() + "_"
                        + boleta.getBolFecEmisionBole().toString() + ".pdf");

        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font fontTexto = FontFactory.getFont(FontFactory.HELVETICA, 11);

            Paragraph titulo = new Paragraph("BOLETA DE PAGO", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph("\n"));

            Paragraph mes = new Paragraph(
                    "MES DE " + LocalDate.now().getMonth().toString() + " " + LocalDate.now().getYear(), fontTexto);
            mes.setAlignment(Element.ALIGN_CENTER);
            document.add(mes);
            document.add(new Paragraph("\n"));

            Paragraph FechaEmi = new Paragraph("FECHA DE EMISIÓN: " + boleta.getBolFecEmisionBole().toString(),
                    fontTexto);
            FechaEmi.setAlignment(Element.ALIGN_RIGHT);
            document.add(FechaEmi);

            Paragraph Empresa = new Paragraph("DATOS DE LA EMPRESA", fontTexto);
            Empresa.setAlignment(Element.ALIGN_LEFT);
            document.add(Empresa);

            document.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[] { 3, 3, 2, 2, 2, 2 });

            table.addCell("RUC:");
            table.addCell("123456789");
            table.addCell("EMPRESA:");
            table.addCell("CHIQUITOS CORP.");
            table.addCell("DIRECCIÓN:");
            table.addCell("Av. Principal 123");

            document.add(table);
            document.add(new Paragraph("\n"));

            Paragraph DatosEmpleado = new Paragraph("DATOS DEL EMPLEADO", fontTexto);
            DatosEmpleado.setAlignment(Element.ALIGN_LEFT);
            document.add(DatosEmpleado);

            document.add(new Paragraph("\n"));

            PdfPTable empleado = new PdfPTable(4);
            empleado.setWidthPercentage(100);
            empleado.setWidths(new float[] { 3, 3, 2, 2 });

            empleado.addCell("NOMBRE:");
            empleado.addCell(boleta.getBolContrato().getContempleado().getEmpNombre());
            empleado.addCell("DNI:");
            empleado.addCell(boleta.getBolContrato().getContempleado().getEmpDni());
            empleado.addCell("F. NACIMIENTO:");
            empleado.addCell(boleta.getBolContrato().getContempleado().getEmpFecNaci().toString());
            empleado.addCell("ESTADO CIVIL:");
            empleado.addCell(boleta.getBolContrato().getContempleado().getEmpestadocivil().getEstTipo());

            document.add(empleado);
            document.add(new Paragraph("\n"));

            Paragraph DatosLab = new Paragraph("DATOS LABORALES", fontTexto);
            DatosLab.setAlignment(Element.ALIGN_LEFT);
            document.add(DatosLab);

            document.add(new Paragraph("\n"));

            PdfPTable DatosLaborales = new PdfPTable(4);
            DatosLaborales.setWidthPercentage(100);
            DatosLaborales.setWidths(new float[] { 3, 3, 2, 2 });

            DatosLaborales.addCell("AREA:");
            DatosLaborales.addCell(boleta.getBolContrato().getContarea().getANombre());
            DatosLaborales.addCell("FECHA INGRESO:");
            DatosLaborales.addCell(boleta.getBolContrato().getContFecIni().toString());
            DatosLaborales.addCell("TIPO CONTRATO:");
            DatosLaborales.addCell(boleta.getBolContrato().getContmodContrato().getModNombre());
            DatosLaborales.addCell("TIPO JORNADA:");
            DatosLaborales.addCell(boleta.getBolContrato().getContjornada().getJorTipo());
            DatosLaborales.addCell("BANCO:");
            DatosLaborales.addCell(bancoContrato);
            DatosLaborales.addCell("NUMERO CUENTA:");
            DatosLaborales.addCell(boleta.getBolContrato().getContempleado().getEmpCci());

            document.add(DatosLaborales);
            document.add(new Paragraph("\n"));

            PdfPTable ingresos = new PdfPTable(3);
            ingresos.setWidthPercentage(100);
            ingresos.setWidths(new float[] { 3, 2, 1 });

            ingresos.addCell("DESCRIPCIÓN");
            ingresos.addCell("MONTO");
            ingresos.addCell("APORTE EMPLEADOR");
            ingresos.addCell("Salario Base");
            ingresos.addCell("S/ " + boleta.getBolMontoFijo());
            ingresos.addCell("-");
            ingresos.addCell("Beneficios");
            ingresos.addCell("S/ " + boleta.getBolMontoBeneficio());
            ingresos.addCell("-");

            document.add(ingresos);
            document.add(new Paragraph("\n"));

            Paragraph total = new Paragraph("TOTAL NETO A PAGAR: S/ " + boleta.getBolMontoNetoBole(), fontTitulo);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
