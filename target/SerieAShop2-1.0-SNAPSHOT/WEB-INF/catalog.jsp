<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="${pageContext.request.contextPath}/css/catalog.css" rel="stylesheet">
<html>
<head>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>

<div class="Banner">
    <img src="banner.png">
</div>

<%--Creare Ciclo per la stampa di tutti i prdotti--%>
<div class="Grid">
    <div class="Card">
        <a href="">
            <%@include file="productCard.jsp"%>
        </a>
    </div>

    <div class="Card">
        <a href="">
            <%@include file="productCard.jsp"%>
        </a>
    </div>
</div>


</body>
</html>