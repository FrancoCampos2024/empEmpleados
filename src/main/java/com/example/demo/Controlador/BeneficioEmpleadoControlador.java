package com.example.demo.Controlador;


import com.example.demo.Entidades.Beneficio;
import com.example.demo.Entidades.BeneficiosEmpleado;
import com.example.demo.Entidades.Empleado;
import com.example.demo.Servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/BeneficioEmpleado")
public class BeneficioEmpleadoControlador {

  // LLamada de servicios de cada entidad
  @Autowired
  @Qualifier("empleadoservicios")
  private EmpleadoServicios empleadoServicios;

  @Autowired
  @Qualifier("contratoservicios")
  private ContratoServicios contratoservicios;

  @Autowired
  @Qualifier("bancoservicios")
  private BancoServicios bancoservicios;

  @Autowired
  @Qualifier("estadocivilservicios")
  private EstadoCivilServicios estadocivilservicios;

  @Autowired
  @Qualifier("historialsalarioservicios")
  private HistorialSalarioServicios historialsalarioservicios;

  @Autowired
  @Qualifier("areaservicios")
  private AreaServicios areaservicios;

  @Autowired
  @Qualifier("jornadalaboralservicios")
  private JornadalaboralServicios jornadalaboralServicios;

  @Autowired
  @Qualifier("modalidadcontratoservicios")
  private ModalidadContratoServicios modalidadContratoServicios;

  @Autowired
  @Qualifier("beneficioservicios")
  private BeneficioServicios beneficioServicios;

  @Autowired
  @Qualifier("beneempleaservicios")
  private BeneEmpleaServicios beneempleaservicios;


  @GetMapping("/AsignarPreBeneficio/{idemp}")
  public String PrepararBeneficios(@PathVariable("idemp") int idemp, Model model) {
    List<Beneficio> benficios = beneficioServicios.listarbeneficios();
    List<Integer> beneficiosAsignados= beneempleaservicios.idbeneficioasignadoxempelado(idemp);

    model.addAttribute("beneficiosAsignados", beneficiosAsignados);
    model.addAttribute("beneficios",benficios);
    return "BeneficioEmpleado/AsignarBeneficio";
  }

  @PostMapping("/guardarBeneficios/{idemp}")
  public String guardarBeneficios(@PathVariable ("idemp") int idemp ,@RequestParam("beneficiosSeleccionados") List<Integer> idsSeleccionados) {

    List<Beneficio> beneficios= beneficioServicios.listarbeneficios();
    Empleado emp= empleadoServicios.buscarid(idemp);

    for(Beneficio b : beneficios) {
      if(idsSeleccionados.contains(b.getIdBeneficio())){
          if(!beneempleaservicios.existeBeneficioxEmpleado(idemp,b.getIdBeneficio())){
            BeneficiosEmpleado be=new BeneficiosEmpleado();
            be.setBEBeneficio(b);
            be.setBEempleado(emp);

            beneempleaservicios.insertarBeneEmpleado(be);
          }
      }else{
          if(beneempleaservicios.existeBeneficioxEmpleado(idemp,b.getIdBeneficio())){
              beneempleaservicios.borrarBeneficioxEmpleado(idemp,b.getIdBeneficio());
          }
      }
    }
    return "redirect:/Empleado/ListaEmpleados";
  }

}
