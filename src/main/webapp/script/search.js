$(document).ready(function () {
    $("#search-form").submit(function (event) {
        event.preventDefault(); // Impedisce la ricarica della pagina

        const searchTerm = $("#search-input").val();

        // Effettua la richiesta AJAX con jQuery
        $.ajax({
            type: "GET",
            url: `/search?query=${searchTerm}`,
            dataType: "json",
            success: function (data) {
                // Aggiungi i risultati alla pagina
                const resultsContainer = $("#search-results");
                resultsContainer.empty();
                for (let i = 0; i < data.length; i++) {
                    resultsContainer.append("<div>" + data[i] + "</div>");
                }
            },
            error: function (xhr, status, error) {
                console.error("Errore nella richiesta AJAX: " + error);
            }
        });
    });
});