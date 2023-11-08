$(document).ready(function () {
    var searchResults = $("#search-results");
    var searchInput = $("#search");

    // Nascondi inizialmente l'elemento dei risultati
    searchResults.hide();

    // Gestisci l'input nel campo di ricerca
    searchInput.on("input", function () {
        var searchQuery = $(this).val();
        console.log("Search Query: " + searchQuery);

        var keywords = searchQuery.split(" ").filter(function (keyword) {
            return keyword.trim() !== "";
        });

        var minLength = 3;
        var areKeywordsValid = keywords.every(function (keyword) {
            return keyword.length >= minLength;
        });

        if (areKeywordsValid) {
            // Esegui la ricerca solo se le parole chiave sono valide
            $.ajax({
                url: "searchServlet",
                method: "GET",
                data: { query: keywords.join(" ") },
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    var resultsHtml = "<ul>";

                    data.forEach(function (item) {
                        resultsHtml += "<a href='prodotto-singolo?idProdotto=" + item.id + "'>";
                        resultsHtml += "<img class='result-image' src='image/PathOggetti/" + item.id + "/1.jpg'>";
                        resultsHtml += "<li class='result-name'>" + item.nome + "</li>";
                        resultsHtml += "</a>";
                    });

                    resultsHtml += "</ul>";

                    searchResults.html(resultsHtml).show();
                },
                error: function (error) {
                    console.log("Errore nella richiesta AJAX: " + error);
                }
            });
        } else {
            // Nascondi e cancella i risultati se le parole chiave non sono valide
            searchResults.empty().hide();
        }
    });

    // Nascondi i risultati quando si fa clic al di fuori del form
    $(document).on("click", function (e) {
        if (!$(e.target).closest("#search-form").length) {
            searchResults.empty().hide();
        }
    });
});