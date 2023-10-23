<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/prodottoSingolo.css">
    <jsp:include page="nav.jsp"></jsp:include>
</head>
<body>
<%--Caricamento Automatico Foto e info--%>
<div id="container">
    <div class="colonna" id="colonna1">
        <img src="P1.jpg" alt="Immagine Principale" id="immaginePrincipale"  >
        <div class="immagini-piccole">
            <img src="P1.jpg" alt="Immagine 1" class="piccola" onclick="selezionaImmagine(this)">
            <img src="P2.jpg" alt="Immagine 2" class="piccola" onclick="selezionaImmagine(this)">
            <img src="P3.jpg" alt="Immagine 3" class="piccola" onclick="selezionaImmagine(this)">
        </div>
    </div>
    <div class="colonna" id="colonna2">
        <!-- Contenuto della seconda colonna -->
        <div class="contenitore-riga">
            <div class="stemma">
                <img src="napoli.png" alt="Altra Immagine">
            </div>
            <div class="fraseDiv">
                <p>Officially Licensed Gear</p>
            </div>
        </div>

        <h1>SSC Napoli Maglia Gara 2023/2024</h1>

        <h2>Prezzo: 130&#8364</h2>

        <div class="taglie">
            <p class="taglie-paragrafo">Taglie</p>

            <button class="bottone">S</button>
            <button class="bottone">M</button>
            <button class="bottone">L</button>
            <button class="bottone">XL</button>
            <button class="bottone">2XL</button>

            <h1 class="quantita">Quantit&#224</h1>

            <div class="select-and-button">
                <select class="dropdown-select">
                    <option value="opzione1" selected>1</option>
                    <option value="opzione2">2</option>
                    <option value="opzione3">3</option>
                 </select>

                 <button class="aggiungi-carrello">Aggiungi al carrello</button>
            </div>
        </div>

        <div class="box-titolo">
            <h2 class="titolo-spedizione" onclick="toggleDescrizione(this)">Spedizione</h2>
            <div class="descrizione-spedizione">&bull; Questo articolo verrà spedito entro 7 giorni lavorativi</div>
        </div>

        <div class="box-titolo">
            <h2 class="titolo-spedizione" onclick="toggleDescrizione(this)">Descrizione</h2>
            <div class="descrizione-spedizione">
                &bull;SSC Napoli Maglia gara home 2023/2024<br>

                &bull;Dal brand EA7 by Giorgio Armani Group<br>

                &bull;La Maglia EA7/SSC Napoli Home 2023/2024 rappresenta l’unicit&#224 e la New Era del mondo azzurro.<br>

                &bull;Nuovo styline con manica raglan su tessuto tecnico stretch, piping in contrasto bianco, collo a V ad incrocio con stampa tricolore come sulla linea fondo della manica. Nuovo motivo allover “N” con tecnica di stampa a caldo su materiale tecnico traspirante con tecnologia Dry Touch. Patch classico Napoli in silicone e scudetto tricolore personalizzato.<br>

                &bull;Vestibilit&#224: Slim Fit. Composizione: 82% poliestere, 18% elastane<br>
            </div>
        </div>



        </div>

    </div>
</div>




<script>
    function selezionaImmagine(img) {
        var immaginePrincipale = document.getElementById("immaginePrincipale");
        immaginePrincipale.src = img.src;
        immaginePrincipale.alt = img.alt;
    }
</script>

<script>
    function toggleDescrizione(titolo) {
        var descrizione = titolo.nextElementSibling; // Ottieni l'elemento successivo al titolo

        if (descrizione.style.display === 'block' || descrizione.style.display === '') {
            descrizione.style.display = 'none'; // Chiudi la descrizione
        } else {
            descrizione.style.display = 'block'; // Apri la descrizione
        }
    }
</script>

<link rel="stylesheet" type="text/css" href="prodottoSingolo.css">

</body>
</html>

