<%@ page import="model.Squadra" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="${pageContext.request.contextPath}/css/catalog.css" rel="stylesheet">
<html>
<head>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>

<%
    response.setContentType("text/html");
    String categoria=(String)request.getSession().getAttribute("categoria");
    String idSquadra=(String)request.getSession().getAttribute("idSquadra");
    List<Prodotto> prodotti = (List<Prodotto>)request.getSession().getAttribute("prodotti");
    PrintWriter outp = response.getWriter();
    boolean flag=false;
    for (Prodotto s : prodotti) {
        if(s.getIdSquadra().equals(idSquadra) && flag==false){
%>
<div class="Banner">
    <img src="${pageContext.request.contextPath}/image/Banner/<%= s.getIdSquadra() %>/banner.jpg">
</div>
<%
        flag=true;
    }
    }
%>
<%--Creare Ciclo per la stampa di tutti i prdotti--%>
<div class="Grid">
    <div class="Carta">
        <a href="">
            <div class="card">
                <%
                    if(categoria==null){
                    for (Prodotto s : prodotti) {
                        if(s.getIdSquadra().equals(idSquadra)){
                %>
                <a href="prodotto-singolo?idProdotto=<%=s.getId()%>">
                    <img height="80px" src="${pageContext.request.contextPath}/image/PathOggetti/<%= s.getId() %>/1.jpg" %>
                </a>
                <div classs="price">
                    <p>Prezzo: <%=s.getPrezzo()%></p>
                </div>
                <div class="name">
                    <p><%=s.getNome()%></p>
                </div>
                <%
                    }
                    }
                    }else{
                        for(Prodotto s: prodotti){
                            if(s.getCategoria().equals(categoria)){
                                %>
                <a href="prodotto-singolo?idProdotto=<%=s.getId()%>">
                    <img height="80px" src="${pageContext.request.contextPath}/image/PathOggetti/<%= s.getId() %>/1.jpg" %>
                </a>
                <div classs="price">
                    <p>Prezzo: <%=s.getPrezzo()%></p>
                </div>
                <div class="name">
                    <p><%=s.getNome()%></p>
                </div>

                <%
                    }
                    }
                    }
                %>
            </div>
        </a>
    </div>
</div>
</body>
</html>