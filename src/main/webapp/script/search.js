$(document).ready(function () {
    $("#search-results").hide(); // Nascondi inizialmente l'elemento dei risultati

    $("#search").on("input", function () {
        var searchQuery = $(this).val();
        console.log("Search Query: " + searchQuery); // Messaggio di log per la query di ricerca

        // Dividi la query in parole chiave separate da spazi e rimuovi spazi vuoti
        var keywords = searchQuery.split(" ").filter(function (keyword) {
            return keyword.trim() !== "";
        });

        // Esegui la ricerca solo se ci sono almeno 3 caratteri in ciascuna parola chiave
        var minLength = 3;
        var areKeywordsValid = keywords.every(function (keyword) {
            return keyword.length >= minLength;
        });

        if (areKeywordsValid) {
            $.ajax({
                url: "searchServlet",
                method: "GET",
                data: { query: keywords.join(" ") },
                dataType: "json",
                success: function (data) {
                    console.log(data); // Verifica i dati ricevuti in console
                    var resultsHtml = "<ul>";

                    data.forEach(function (item) {
                        resultsHtml += "<a href='prodotto-singolo?idProdotto=" + item.id + "'>";
                        resultsHtml += "<img class='result-image' src='image/PathOggetti/" + item.id + "/1.jpg'>";
                        resultsHtml += "<li class='result-name'>" + item.nome + "</li>";
                        resultsHtml += "</a>";
                    });

                    resultsHtml += "</ul>";

                    // Inserisci i risultati nell'elemento con ID "search-results"
                    $("#search-results").html(resultsHtml).show(); // Mostra i risultati
                },
                error: function (error) {
                    console.log("Errore nella richiesta AJAX: " + error);
                }
            });
        } else {
            // Se le parole chiave non hanno almeno 3 caratteri ciascuna, nascondi e cancella i risultati
            $("#search-results").empty().hide();
        }
    });
});
