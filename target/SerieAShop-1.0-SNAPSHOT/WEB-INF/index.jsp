<!DOCTYPE html>
<html>
<head>
    <%@ page import="model.Squadra"%>
    <%@ include file="nav.jsp" %>
    <link href="css/index.css" rel="stylesheet">
</head>
<body>
<br>
<br>
<br>
<br>
<h2 style="text-align: center;">Serie A Teams</h2>
<div class="containerIndex">
    <div class="stemma">
        <c:forEach var="squadra" items="${squadre}">
            <a href="#">
                <img src="<c:out value='.${squadra.pathLogo}' />" alt="<c:out value='${squadra.nomeSquadra}' />">
            </a>
        </c:forEach>
    </div>
</div>
<br>
<br>
<br>
<div>
    <a href="">
        <img src="image/Banner1.jpg">
    </a>
</div>
<br>
<div>
    <a href="">
        <img src="image/Banner2.jpg">
    </a>
</div>
</body>
</html>