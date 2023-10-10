<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
    <link rel="stylesheet" href="../css/form.css">
    <script src="../script/FormValidationCliente.js"></script>

</head>
<body>

<div class="titolo">
    <img src="../image/logo.png" title="Serie A Shop Logo">
</div>

<h1 class="titolo">Inserimento Nuovo Cliente:</h1>
<fieldset class="titolo">
    <legend>Form inserimento:</legend>

    <form action="new-user" method="post">

        <%--@declare id="username"--%><%--@declare id="datanascita"--%><label for="nome">Nome</label>
        <input type="text" id="nome" name="nome"
               onkeyup="nomeClienteValidation(this.form.nome);" required><br><br>

        <label for="lastName">Cognome</label>
        <input type="text" id="lastName" name="cognome"
               onkeyup="cognomeValidation(this.form.cognome);" required><br><br>

        <label for="username">Usermame</label>
            <input type="text" id="username" name="username"
            onkeyup="usernameValidation(this.form.username);" required><br><br>

        <label for="email">e-Mail</label>
        <input type="email" id="email" name="email"
               placeholder="nomecognome@serieashop.it"
               onkeyup="emailValidation(this.form.email);" required><br><br>

        <label for="password">password</label>
        <input type="password" id="password" name="password"
               placeholder="Minimo 4 caratteri"
               onkeyup="passwordValidation(this.form.password);" required><br><br>

        <label for="indirizzo">Indirizzo</label>
        <input type="text" id="indirizzo" name="indirizzo" placeholder="Città/Via"
               onkeyup="indirizzoValidation(this.form.indirizzo);" required><br><br>

        <label for="telefono">Numero di telefono</label>
        <input type="text" id="telefono" name="telefono" title="facoltativo"
               placeholder="es: 1234567890"
               onkeyup="telefonoValidation(this.form.telefono);" required><br><br>

        <input type="submit" value="Inserisci">
        <a href="accedi.jsp">Torna al login</a>

    </form>
</fieldset>