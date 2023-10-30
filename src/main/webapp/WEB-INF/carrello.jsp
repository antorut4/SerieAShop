<%@ page import="model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
<%@ page import="control.ManageProdottoCarrello" %>
<%@ page import="model.ProdottiCarrello" %>

<link href="${pageContext.request.contextPath}/css/carrello.css" rel="stylesheet">
<jsp:include page="nav.jsp"></jsp:include>
<%
    List<ProdottiCarrello> pc=new ArrayList<>();
    pc=(List<ProdottiCarrello>)request.getSession().getAttribute("pc");
    List<Prodotto> prodotti=new ArrayList<>();
    prodotti=(List<Prodotto>)request.getSession().getAttribute("prodottiDaStampare");
    User user=(User)request.getSession().getAttribute("user");
    int totale=(int)request.getSession().getAttribute("totale");

%>

<div class="container" id="CartForm">
    <div class="heading">
        <h1>
                Carrello di <%=user.getNome()%>
        </h1>

        <a href="#" class="visibility-cart transition is-open">X</a>
    </div>

    <div class="cart transition is-open">

        <div class="table">

            <div class="layout-inline row th">
                <div class="col col-pro">Product</div>
                <div class="col col-price align-center ">
                    Price
                </div>
                <div class="col col-qty align-center">QTY</div>
            </div>
<%for(Prodotto p: prodotti) {%>
            <div class="layout-inline row">
            <%System.out.println(p.getId());%>
                <div class="col col-pro layout-inline">
                    <img src="${pageContext.request.contextPath}/image/PathOggetti/<%=p.getId()%>/1.jpg" />
                    <p><%=p.getNome()%></p>
                </div>

                <div class="col col-price col-numeric align-center ">
                    <p><%=p.getPrezzo()%></p>
                </div>

                <div class="col col-qty layout-inline">
                    <%for(ProdottiCarrello pcar: pc){
                    if(p.getId()== pcar.getIdProdotto()){
                    %>
                    <a href="manage-prodotto-carrello?valore=piu&Prod=<%=p.getId()%>" class="qty qty-plus">+</a>
                    <input type="numeric" value="<%=pcar.getQuantita()%>" />
                    <a href="manage-prodotto-carrello?valore=meno&Prod=<%=p.getId()%>" class="qty qty-minus">-</a>
                    <%
                        }
                        }
                    %>
                </div>
                <div class="col col-total layout-inline">
                    <a href="manage-prodotto-carrello?valore=rimuovi&Prod=<%=p.getId()%>"> X </a>
                </div>

            </div>
            <%}%>

            <div class="tf">
                <div class="row layout-inline">
                    <div class="col">
                        <p>Total: <%=totale%>&euro;</p>
                    </div>
                    <div class="col"></div>
                </div>
            </div>
        </div>

        <a href="#" class="btn btn-update">Acquista</a>

    </div>
</div>
<script>
    $('.visibility-cart').on('click',function(){

        var $btn =  $(this);
        var $cart = $('.cart');
        console.log($btn);

        if ($btn.hasClass('is-open')) {
            $btn.removeClass('is-open');
            $btn.text('O')
            $cart.removeClass('is-open');
            $cart.addClass('is-closed');
            $btn.addClass('is-closed');
        } else {
            $btn.addClass('is-open');
            $btn.text('X')
            $cart.addClass('is-open');
            $cart.removeClass('is-closed');
            $btn.removeClass('is-closed');
        }


    });


    // RESTRICT INPUTS TO NUMBERS ONLY WITH A MIN OF 0 AND A MAX 100
    $('input').on('blur', function(){

        var input = $(this);
        var value = parseInt($(this).val());

        if (value < 0 || isNaN(value)) {
            input.val(0);
        } else if
        (value > 100) {
            input.val(100);
        }
    });

</script>