<link href="https://fonts.googleapis.com/css?family=Encode+Sans+Semi+Condensed:100,200,300,400" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/errore.css"
      <script src="${pageContext.request.contextPath}/script/errore.js"></script>
<body class="loading">
<h1>500</h1>
<%String error=request.getParameter("errore");
if(error!=null){
%>
<h2><b><%=error%>:(</b></h2>
<%}else{
 %>
<h2><b>Unexpected error :(</b></h2>
<%
}%>
<div class="gears">
    <div class="gear one">
        <div class="bar"></div>
        <div class="bar"></div>
        <div class="bar"></div>
    </div>
    <div class="gear two">
        <div class="bar"></div>
        <div class="bar"></div>
        <div class="bar"></div>
    </div>
    <div class="gear three">
        <div class="bar"></div>
        <div class="bar"></div>
        <div class="bar"></div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="js/main.js" type="text/javascript"></script>
</body>