<!DOCTYPE html>
<html>
<head>
    <title>Gestione Admin</title>
    <%@page import="java.io.PrintWriter"%>
    <%@ page import="control.TabellaProdotti" %>
    <link rel="stylesheet" type="text/css" href="css/HomeAdmin.css">
    <script src="../script/FormValidationProdotto.js"></script>
</head>
<body>

<h1>Benvenuto ${admin.username}</h1>
<div class="admin-panel">
    <h2>Aggiungi Prodotto</h2>
    <button class="toggle-btn">Mostra Form Aggiungi Prodotto</button>
    <form class="add-product-form" action="new-prodotto" method="post">


        <%--@declare id="nome"--%><%--@declare id="prezzo"--%><%--@declare id="descrizione"--%>

        <%--@declare id="image"--%>
            <%--@declare id="categoria"--%>
            <label for="nome">Nome Prodotto:</label>
        <input type="text" name="nome" id="nome" onkeyup="nomeProdValidation(this.form.nome)" required>


            <label for="descrizione">Descrizione:</label>
            <textarea id="descrizione" name="descrizione" onkeyup="descrizioneValidation(this.form.descrizione)"></textarea>


        <label for="quantita">Quantita:</label>
            <input type="number" id="quantita" name="quantita" step="0.01" onkeyup="quantitaValidation(this.form.quantita)" required>

            <label for="prezzo">idSquadra:</label>
            <select id="squadra" name="idSquadra" class="select" >
                <option value="Juventus">Juventus</option>
                <option value="Milan">Milan</option>
                <option value="Inter">Inter</option>
                <option value="Napoli">Napoli</option>
                <option value="Roma">Roma</option>
                <option value="Lazio">Lazio</option>
                <option value="Fiorentina">Fiorentina</option>
                <option value="Atalanta">Atalanta</option>
                <option value="Verona">Verona</option>
                <option value="Bologna">Bologna</option>
                <option value="Sassuolo">Sassuolo</option>
                <option value="Udinese">Udinese</option>
                <option value="Sampdoria">Sampdoria</option>
                <option value="Empoli">Empoli</option>
                <option value="Venezia">Venezia</option>
                <option value="Cagliari">Cagliari</option>
                <option value="Torino">Torino</option>
                <option value="Salernitana">Salernitana</option>
                <option value="Spezia">Spezia</option>
                <option value="Genoa">Genoa</option>
            </select>

            <label for="prezzo">Prezzo:</label>
            <input type="number" id="prezzo" name="prezzo" step="0.01" onkeyup="prezzoValidation(this.form.prezzo)" required>

        <label for="image">ImagePath:</label>
        <input type="text" id="image" name="image"  onkeyup="imageValidation(this.form.image)" required>

        <label for="categoria">Categoria:</label>
        <select name="categoria" id="categoria" class="select">
            <option value="maglia">Maglia</option>
            <option value="calzatura">Calzatura</option>
            <option value="t-shirt">T-shirt</option>
            <option value="Retro">Retro</option>
        </select>

        <input type="submit" value="Aggiungi">
    </form>
</div>

<div class="admin-panel">
    <h2>Modifica Prodotto</h2>
    <button class="toggle-btn">Mostra Form Modifica Prodotto</button>
    <form class="edit-product-form" action="modifica-prodotto" method="post">
        <%--@declare id="id"--%>
            <label for="id">ID Prodotto:</label>
        <input type="text" name="id" required>

        <label for="nome">Nuovo Nome Prodotto:</label>
        <input type="text" name="nome" id="nome" onkeyup="nomeProdValidation(this.form.nome)">

        <label for="prezzo">Nuovo Prezzo:</label>
        <input type="number" name="prezzo" id="prezzo" step="0.01" onkeyup="prezzoValidation(this.form.prezzo)">

        <label for="descrizione">Nuova Descrizione:</label>
        <textarea name="descrizione" id="descrizione" onkeyup="this.form.descrizione"></textarea>

            <label for="quantita">Quantita:</label>
            <input type="number" name="quantita" id="quantita" onkeyup="quantitaValidation(this.form.quantita)">


        <label for="image">ImagePath:</label>
        <input type="text" name="image" id="image" onkeyup="imageValidation(this.form.image)">

            <input type="submit" value="Modifica">

    </form>
</div>

<div class="admin-panel">
    <h2>Elimina Prodotto</h2>
    <button class="toggle-btn">Mostra Form Cancella Prodotto</button>
    <form class="delete-product-form" action="delete-prodotto" method="post">
        <label for="id">ID Prodotto:</label>
        <input type="text" name="id" required>
        <input type="submit" value="Elimina">
    </form>
</div>

<div class="admin-panel">
    <h2>Modifica Prezzo</h2>
    <button class="toggle-btn">Mostra Form Cancella Prodotto</button>
    <form class="delete-product-form" action="update-prezzo" method="post">
        <label for="id">ID Prodotto:</label>
        <input type="text" name="id" required>
        <label for="prezzo">Prezzo:</label>
        <input type="number" name="prezzo" id="prezzo" onkeyup="prezzoValidation(this.form.prezzo)">
        <input type="submit" value="Modifica Prezzo">
    </form>
</div>

<div class="admin-panel">
    <button class="toggle-btn">Visualizza Prodotti</button>
<%
    PrintWriter outt=response.getWriter();
    String table= TabellaProdotti.stampa(request, response);
    outt.println(table);
%>
</div>

<script>
    const toggleBtns = document.querySelectorAll('.toggle-btn');
    const forms = document.querySelectorAll('form');

    toggleBtns.forEach((btn, index) => {
        btn.addEventListener('click', () => {
            if (forms[index].style.display === 'none') {
                forms[index].style.display = 'block';
            } else {
                forms[index].style.display = 'none';
            }
        });
    });
</script>

</body>
</html>