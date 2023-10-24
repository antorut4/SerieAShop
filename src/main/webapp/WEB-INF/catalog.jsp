<%@ page import="model.Squadra" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tuo Titolo Pagina</title>
    <link href="${pageContext.request.contextPath}/css/catalog.css" rel="stylesheet">
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>

<%
    response.setContentType("text/html");
    String categoria = (String) request.getSession().getAttribute("categoria");
    String idSquadra = (String) request.getSession().getAttribute("idSquadra");
    List<Prodotto> prodotti = (List<Prodotto>) request.getSession().getAttribute("prodotti");
    PrintWriter outp = response.getWriter();
    boolean flag = false;
    for (Prodotto s : prodotti) {
        if (s.getIdSquadra().equals(idSquadra) && !flag) {
%>
<div class="Banner">
    <img src="${pageContext.request.contextPath}/image/Banner/<%= s.getIdSquadra() %>/banner.jpg">
</div>
<%
            flag = true;
        }
    }
%>

<div class="Grid">
    <%
        if (categoria == null) {
            for (Prodotto s : prodotti) {
                if (s.getIdSquadra().equals(idSquadra)) {
    %>
    <div class="Carta">
            <div class="card">
                <a href="prodotto-singolo?idProdotto=<%= s.getId() %>">
                <img height="80px" src="${pageContext.request.contextPath}/image/PathOggetti/<%= s.getId() %>/1.jpg">
                </a>
                <div class="price">
                    <p>Prezzo: <%= s.getPrezzo() %></p>
                </div>
                <div class="name">
                    <p><%= s.getNome() %></p>
                </div>
            </div>

    </div>
    <%
            }
        }
    } else {
        for (Prodotto s : prodotti) {
            if (s.getCategoria().equals(categoria)) {
    %>
    <div class="Carta">

            <div class="card">
                <a href="prodotto-singolo?idProdotto=<%= s.getId() %>">
                <img height="80px" src="${pageContext.request.contextPath}/image/PathOggetti/<%= s.getId() %>/1.jpg">
            </a>
                <div class="price">
                    <p>Prezzo: <%= s.getPrezzo() %></p>
                </div>
                <div class="name">
                    <p><%= s.getNome() %></p>
                </div>
            </div>
    </div>
    <%
                }
            }
        }
    %>
</div>

</body>
</html>