<!DOCTYPE html>
<html>
<head>
    <title>Gestione Admin</title>
    <%@page import="java.io.PrintWriter"%>
    <%@ page import="control.TabellaProdotti" %>
    <link rel="stylesheet" type="text/css" href="css/HomeAdmin.css">
    <script src="${pageContext.request.contextPath}/script/FormValidationProdotto.js"></script>
</head>
<div>

<a href="${pageContext.request.contextPath}/home">
    <img src="image/home.png" width="100px">
</a>

    <h1>Benvenuto ${user.username}</h1>

    <div class="admin-panel">
    <h2>Aggiungi Prodotto</h2>
    <button class="toggle-btn">Mostra Form Aggiungi Prodotto</button>
    <form class="add-product-form" action="new-prodotto" method="post">


        <%--@declare id="nome"--%><%--@declare id="prezzo"--%><%--@declare id="descrizione"--%>

        <%--@declare id="image"--%>
            <%--@declare id="categoria"--%>
            <label for="nome">Nome Prodotto:</label>
        <input type="text" name="nome" id="nome" onblur="nomeProdValidation(this.form.nome)" required>


            <label for="descrizione">Descrizione:</label>
            <textarea id="descrizione" name="descrizione" onblur="descrizioneValidation(this.form.descrizione)"></textarea>


        <label for="quantita">Quantita:</label>
            <input type="number" id="quantita" name="quantita" step="1" onblur="quantitaValidation(this.form.quantita)" required>

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
                <option value="Monza">Monza</option>
                <option value="Empoli">Empoli</option>
                <option value="Frosinone">Frosinone</option>
                <option value="Cagliari">Cagliari</option>
                <option value="Torino">Torino</option>
                <option value="Salernitana">Salernitana</option>
                <option value="Lecce">Lecce</option>
                <option value="Genoa">Genoa</option>
            </select>

            <label for="prezzo">Prezzo:</label>
            <input type="number" id="prezzo" name="prezzo" step="0.01" onblur="prezzoValidation(this.form.prezzo)" required>


        <label for="categoria">Categoria:</label>
        <select name="categoria" id="categoria" class="select">
            <option value="maglia">Maglia</option>
            <option value="pantaloncini">Pantaloncini</option>
            <option value="calzettoni">Calzettoni</option>
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
        <input type="text" name="nome" id="nome" onblur="nomeProdValidation(this.form.nome)">

        <label for="prezzo">Nuovo Prezzo:</label>
        <input type="number" name="prezzo" id="prezzo" step="0.01" onblur="prezzoValidation(this.form.prezzo)">

        <label for="descrizione">Nuova Descrizione:</label>
        <textarea name="descrizione" id="descrizione" onblur="descrizioneValidation(this.form.descrizione)"></textarea>

            <label for="quantita">Quantita:</label>
            <input type="number" name="quantita" id="quantita" >
            <label for="categoria">Categoria:</label>
            <select name="categoria" id="categoria" class="select">
                <option value="maglia">Maglia</option>
                <option value="pantaloncini">Pantaloncini</option>
                <option value="calzettoni">Calzettoni</option>
            </select>

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
        <input type="text" name="idProdotto" required>
        <label for="prezzo">Prezzo:</label>
        <input type="number" name="prezzo" id="prezz" onkeyup="prezzoValidation(this.form.prezz)">
        <input type="submit" value="Modifica Prezzo">
    </form>
</div>

<div class="admin-panel">
    <h2>Elimina Utente</h2>
    <button class="toggle-btn">Mostra Form Cancella Utente</button>
    <form class="delete-product-form" action="delete-user" method="post">
        <label for="id">Username:</label>
        <input type="text" name="username" required>
        <input type="submit" value="Elimina">
    </form>
</div>

<div class="admin-panel">
    <h2>Visualizza Prodotti</h2>
    <button class="toggle-btn" id="showProductsBtn">Visualizza Prodotti</button>
    <div id="productsTableContainer" style="display: none;">
        <iframe id="productsTableFrame" src="TabellaProdotti"></iframe>
    </div>
</div>

<div class="admin-panel">
    <h2>Visualizza Ordini</h2>
    <button class="toggle-btn" id="showOrderBtn">Visualizza Ordini</button>
    <div id="orderTableContainer" style="display: none;">
        <iframe id="orderTableFrame" src="TabellaOrdini"></iframe>
    </div>
</div>

<div class="admin-panel">
    <h2>Visualizza Utenti</h2>
    <button class="toggle-btn" id="showUserBtn">Visualizza Utenti</button>
    <div id="userTableContainer" style="display: none;">
        <iframe id="userTableFrame" src="TabellaUtenti"></iframe>
    </div>
</div>

<div class="admin-panel">
    <form class="delete-product-form" action="log-out" method="post">
        <input type="submit" id="logout" value="Log Out">
    </form>
</div>
</div>
</html>


<script>

    document.addEventListener('DOMContentLoaded', function () {
        const showProductsBtn = document.getElementById('showProductsBtn');
        const productsTableContainer = document.getElementById('productsTableContainer');
        const showOrderBtn = document.getElementById('showOrderBtn');
        const orderTableContainer = document.getElementById('orderTableContainer');
        const showUserBtn= document.getElementById("showUserBtn");
        const userTableContainer= document.getElementById("userTableContainer");

        const toggleBtns = document.querySelectorAll('.toggle-btn');
        const forms = document.querySelectorAll('form');

        toggleBtns.forEach((btn, index) => {
            btn.addEventListener('click', () => {
                if (forms[index] && forms[index].classList) {
                    if (forms[index].classList.contains('visible')) {
                        forms[index].classList.remove('visible');
                    } else {
                        forms[index].classList.add('visible');
                    }
                }
            })
        });

        if (showProductsBtn) {
            showProductsBtn.addEventListener('click', () => {
                if (productsTableContainer.style.display === "none" || productsTableContainer.style.display === "") {
                    fetch("TabellaProdotti")
                        .then(response => response.text())
                        .then(data => {
                            productsTableContainer.innerHTML = data;
                        });
                    productsTableContainer.style.display = "block";
                } else {
                    productsTableContainer.innerHTML = '';
                    productsTableContainer.style.display = "none";
                }
            });
        }

        if (showOrderBtn) {
            showOrderBtn.addEventListener('click', () => {
                if (orderTableContainer.style.display === "none" || orderTableContainer.style.display === "") {
                    fetch("TabellaOrdini")
                        .then(response => response.text())
                        .then(data => {
                            orderTableContainer.innerHTML = data;
                        });
                    orderTableContainer.style.display = "block";
                } else {
                    orderTableContainer.innerHTML = '';
                    orderTableContainer.style.display = "none";
                }
            });
        }

        if (showUserBtn) {
            showUserBtn.addEventListener('click', () => {
                if (userTableContainer.style.display === "none" || userTableContainer.style.display === "") {
                    fetch("TabellaUtenti")
                        .then(response => response.text())
                        .then(data => {
                            userTableContainer.innerHTML = data;
                        });
                    userTableContainer.style.display = "block";
                } else {
                    userTableContainer.innerHTML = '';
                    userTableContainer.style.display = "none";
                }
            });
        }
    });


</script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Seleziona il pulsante "UOMO" per il reindirizzamento
        var logout = document.getElementById('logout');


        // Aggiungi un gestore di eventi al clic del pulsante "UOMO"
        logout.addEventListener('click', function() {
            // Reindirizza l'utente alla servlet "direct-servlet"
            window.location.href = 'log-out';
        });
        });
</script>
</body>
</html>