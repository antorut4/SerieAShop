<%@ page import="model.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyAccount</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account.css">
    <jsp:include page="nav.jsp"></jsp:include>
</head>
<body>

<div class="logo">
    <img src="${pageContext.request.contextPath}/image/logo.png" alt="Logo" title="Logo">
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
            <iframe id="orderTableFrame" src="TabellaOrdini"></iframe>
    </div>

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

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const showOrderBtn = document.getElementById('showOrderBtn');
        const orderTableContainer = document.getElementById('orderTableContainer');

        const toggleBtns = document.querySelectorAll('.toggle-btn');

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
</script>