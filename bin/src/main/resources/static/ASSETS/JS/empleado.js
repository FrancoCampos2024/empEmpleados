document.addEventListener("DOMContentLoaded", function() {
    let searchBox = document.getElementById("searchBox");
    searchBox.addEventListener("keyup", function() {
        let filter = searchBox.value.toUpperCase();
        let tableRows = document.querySelectorAll("tbody tr");

        tableRows.forEach(row => {
            let codigo = row.cells[1].textContent || row.cells[1].innerText;
            if (codigo.toUpperCase().indexOf(filter) > -1) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        });
    });
});