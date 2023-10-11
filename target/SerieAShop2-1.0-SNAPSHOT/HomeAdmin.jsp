<!DOCTYPE html>
<html>
<head>
    <title>Gestione Admin</title>
    <link rel="stylesheet" type="text/css" href="css/HomeAdmin.css">
</head>
<body>

<h1>Benvenuto ${admin.username}</h1>

<div class="admin-panel">
    <h2>Aggiungi Prodotto</h2>
    <button class="toggle-btn">Mostra Form Aggiungi Prodotto</button>
    <form class="add-product-form" action="aggiungi-prodotto" method="post">
        <%--@declare id="nome"--%><%--@declare id="prezzo"--%><%--@declare id="descrizione"--%>

        <%--@declare id="image"--%>
            <%--@declare id="categoria"--%>
            <label for="nome">Nome Prodotto:</label>
        <input type="text" name="nome" required>


            <label for="descrizione">Descrizione:</label>
            <textarea name="descrizione"></textarea>


        <label for="prezzo">Quantita:</label>
            <input type="number" name="quantita" step="0.01" required>

            <label for="prezzo">idSquadra:</label>
            <select name="idSquadra" class="select" >
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
            <input type="number" name="prezzo" step="0.01" required>

        <label for="image">ImagePath:</label>
        <input type="text" name="image"  required>

        <label for="categoria">Categoria:</label>
        <select name="categoria" class="select">
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
        <input type="text" name="nome">

        <label for="prezzo">Nuovo Prezzo:</label>
        <input type="number" name="prezzo" step="0.01">

        <label for="descrizione">Nuova Descrizione:</label>
        <textarea name="descrizione"></textarea>

            <input type="submit" value="Modifica">
    </form>
</div>

<div class="admin-panel">
    <h2>Cancella Prodotto</h2>
    <button class="toggle-btn">Mostra Form Cancella Prodotto</button>
    <form class="delete-product-form" action="cancella-prodotto" method="post">
        <label for="id">ID Prodotto:</label>
        <input type="text" name="id" required>

        <input type="submit" value="Cancella">
    </form>
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