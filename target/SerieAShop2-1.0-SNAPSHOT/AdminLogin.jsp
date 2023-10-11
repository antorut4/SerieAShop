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

    <form action="admlog-in" method="post">
        <%--@declare id="username"--%>
        <label for="username">e-Mail</label>
        <input type="username" id="username" name="username"
               placeholder="admin1"
               onkeyup="usernameValidation(this.form.username)"><br><br>

        <label for="password">password</label>
        <input type="password" id="password" name="password"
               onkeyup="passwordValidation(this.form.password)"><br><br>

        <input type="submit" value="Accedi">
        <a href="newUser.jsp">Crea il tuo account</a>
        <a href="accedi.jsp">Clicca qui se sei un utente</a>
    </form>
</fieldset>

</body>
</html>