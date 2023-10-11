<%@ page import="model.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyAccount</title>
    <link rel="stylesheet" type="text/css" href="css/account.css">
    <jsp:include page="nav.jsp"></jsp:include>
</head>
<body>

<div class="logo">
    <img src="image/logo.png" alt="Logo" title="Logo">
</div>

<h1 class="titolo">Account di ${user.nome}</h1>

<div class="para">
    <p>Nome: ${user.nome}</p>
    <p>Cognome: ${user.cognome} </p>
    <p>E-Mail: ${user.email}</p>
    <p>Numero di Telefono: ${user.telefono}</p>
</div>

<br>
<div style="text-align: center">
    <br>
    <form action="visualizza-ordini" method="get">
        <input type="hidden" name="idCliente" value="${user.id}">
        <input type="submit" value="Visualizza ordini">
    </form>

    <br><br>
    <form class="logout" action="log-out" method="post">
        <input type="submit" value="Esci-LogOut">
    </form>
    <form class="deleteaccount" action="delete-user">
        <input type="submit" value="Elimina account">
    </form>
</div>
</body>
</html>
