<%@ page import="model.Prodotto" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.Squadra" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/prodottoSingolo.css">

    <jsp:include page="nav.jsp"></jsp:include>
</head>
<body>
<%--Caricamento Automatico Foto e info--%>
<%response.setContentType("text/html");
    String categoria=(String)request.getSession().getAttribute("categoria");
    String idSquadra=(String)request.getSession().getAttribute("idSquadra");
    List<Squadra> squadre=(List<Squadra>) request.getSession().getAttribute("squadre");
    Squadra squadra=new Squadra();
    Prodotto prodotto = (Prodotto) request.getSession().getAttribute("prodotto");
    for(Squadra sq: squadre){
        if(sq.getNomeSquadra().equals(prodotto.getIdSquadra())){
           squadra=sq;
        }
    }
    PrintWriter outp = response.getWriter();
%>
<div id="container">
    <div class="colonna" id="colonna1">
        <img src="${pageContext.request.contextPath}/image/PathOggetti/<%=prodotto.getId()%>/1.jpg" alt="Immagine Principale" id="immaginePrincipale"  >
        <div class="immagini-piccole">
            <img src="${pageContext.request.contextPath}/image/PathOggetti/<%=prodotto.getId()%>/1.jpg" alt="Immagine 1" class="piccola" onclick="selezionaImmagine(this)">
            <img src="${pageContext.request.contextPath}/image/PathOggetti/<%=prodotto.getId()%>/2.jpg" alt="Immagine 2" class="piccola" onclick="selezionaImmagine(this)">
            <img src="${pageContext.request.contextPath}/image/PathOggetti/<%=prodotto.getId()%>/3.jpg" alt="Immagine 3" class="piccola" onclick="selezionaImmagine(this)">
        </div>
    </div>
    <div class="colonna" id="colonna2">
        <!-- Contenuto della seconda colonna -->
        <div class="contenitore-riga">
            <div class="stemma">
                <img src=".<%=squadra.getPathLogo()%>" alt="Altra Immagine">
            </div>
            <div class="fraseDiv">
                <p>Officially Licensed Gear</p>
            </div>
        </div>

        <h1><%=prodotto.getNome()%></h1>

        <h2>Prezzo: <%= prodotto.getPrezzo()%>&euro;</h2>

        <%Prodotto prodScelto=prodotto;
            request.getSession().setAttribute("prodAgg", prodScelto);
        %>

        <form action="aggiungi-al-carrello">
        <div class="taglie">
            <p class="taglie-paragrafo">Taglie</p>

            <select name="taglia" class="bottone">
                <option value="s">S</option>
                <option value="m">M</option>
                <option value="l">L</option>
                <option value="xl">XL</option>
                <option value="2xl">2XL</option>
            </select>


        <h1 class="quantita">Quantita: </h1>

            <div class="select-and-button">
                <select class="bottone" name="quantita">
                <%for(int i=0;i<prodotto.getQuantita();i++){%>
                    <option value="<%=i%>"><%=i%></option>
                    <%}%>
                </select>



                <div class="bottone">
                        <button type="submit" class="bottone" value="Aggiungi Al Carrello">Aggiungi Al Carrello </button>
                </div>
            </div>
        </div>
        </form>

        <div class="box-titolo">
            <h2 class="titolo-spedizione" onclick="toggleDescrizione(this)">- Spedizione</h2>
            <div class="descrizione-spedizione">&bull; Questo articolo verrà spedito entro 7 giorni lavorativi</div>
        </div>


        <div class="box-titolo">
            <h2 class="titolo-spedizione" onclick="toggleDescrizione(this)">- Descrizione</h2>
            <div class="descrizione-spedizione">
                &bull; <%=prodotto.getDescrizione()%>
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

