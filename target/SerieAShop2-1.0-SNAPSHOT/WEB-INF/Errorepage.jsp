<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ERROR</title>
    <link rel="icon" type="image/x-icon" href="./icons/logoBankai.png">
    <jsp:include page="nav.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="./CSS/styles.css">
    <link rel="stylesheet" href="./CSS/tavola.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
</head>
<body>
<div class="titolo">
    <img src="./image/logo.png" alt="Logo" title="Logo">
</div>

<h1 class="message"><%=request.getParameter("errore") %></h1>

</body>
</html>
