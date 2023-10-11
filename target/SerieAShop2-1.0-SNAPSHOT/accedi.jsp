<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accedi</title>
    <link rel="stylesheet" type="text/css" href="css/form.css">
    <script src="script/FormValidationCliente.js"></script>
</head>

<% String errorMessage = (String) request.getAttribute("errorMessageLogIn"); %>
<% if (errorMessage != null && errorMessage.length() > 0) { %>
<script>
    var errorMessage = <%= new com.google.gson.Gson().toJson(errorMessage) %>;
    alert(errorMessage);
</script>
<% } %>

<body>
<div class="titolo">
    <img src="image/logo.png" alt="Logo" title="Logo">
</div>

<h1 class="titolo">Accedi al tuo account:</h1>
<fieldset class="titolo">
    <legend>Bentornato!</legend>

    <form action="log-in" method="post">
        <label for="email">e-Mail</label>
        <input type="email" id="email" name="email"
               placeholder="nomecognome@serieashop.it"
               onkeyup="emailValidation(this.form.email)"><br><br>

        <label for="password">password</label>
        <input type="password" id="password" name="password"
               onkeyup="passwordValidation(this.form.password)"><br><br>

        <input type="submit" value="Accedi">
        <a href="newUser.jsp">Crea il tuo account</a>
        <a href="AdminLogin.jsp">Clicca qui se sei un admin</a>
    </form>
</fieldset>

</body>
</html>