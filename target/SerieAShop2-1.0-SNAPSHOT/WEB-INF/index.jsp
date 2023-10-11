
<%@ page import="model.Squadra"%>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<!--<%//@ include file="nav.jsp" %>-->
<link href="css/index.css" rel="stylesheet">

<h2 style="text-align: center;">Serie A Teams</h2>
<div class="containerIndex">
    <div class="stemma">
        <%
            response.setContentType("text/html");
            List<Squadra> squadre = (List<Squadra>) request.getSession().getAttribute("squadre");
            String stampa="";
            PrintWriter outf=response.getWriter();
            for(Squadra s: squadre){
                outf.println("<img src='."+s.getPathLogo()+"' alt='"+s.getNomeSquadra()+"'>");
            }
        %>
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