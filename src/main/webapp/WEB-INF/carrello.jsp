<link href="${pageContext.request.contextPath}/css/carrello.css" rel="stylesheet">

<div class="container">
    <div class="heading">
        <h1>
            <span class="shopper">s</span> Shopping Cart
        </h1>

        <a href="#" class="visibility-cart transition is-open">X</a>
    </div>

    <div class="cart transition is-open">

        <a href="#" class="btn btn-update">Update cart</a>


        <div class="table">

            <div class="layout-inline row th">
                <div class="col col-pro">Product</div>
                <div class="col col-price align-center ">
                    Price
                </div>
                <div class="col col-qty align-center">QTY</div>
                <div class="col">VAT</div>
                <div class="col">Total</div>
            </div>

            <div class="layout-inline row">

                <div class="col col-pro layout-inline">
                    <img src="${pageContext.request.contextPath}/image/PathOggetti/1/1.jpg" alt="kitten" />
                    <p>Happy Little Critter</p>
                </div>

                <div class="col col-price col-numeric align-center ">
                    <p>£59.99</p>
                </div>

                <div class="col col-qty layout-inline">
                    <a href="#" class="qty qty-minus">-</a>
                    <input type="numeric" value="3" />
                    <a href="#" class="qty qty-plus">+</a>
                </div>

                <div class="col col-vat col-numeric">
                    <p>£2.95</p>
                </div>
                <div class="col col-total col-numeric">               <p> £182.95</p>
                </div>
            </div>

            <div class="layout-inline row row-bg2">

                <div class="col col-pro layout-inline">
                    <img src="${pageContext.request.contextPath}/image/PathOggetti/2/1.jpg" alt="kitten" />
                    <p>Scared Little Kittie</p>
                </div>

                <div class="col col-price col-numeric align-center ">
                    <p>£23.99</p>
                </div>

                <div class="col col-qty  layout-inline">
                    <a href="#" class="qty qty-minus ">-</a>
                    <input type="numeric" value="1" />
                    <a href="#" class="qty qty-plus">+</a>
                </div>

                <div class="col col-vat col-numeric">
                    <p>£1.95</p>
                </div>
                <div class="col col-total col-numeric">
                    <p>£25.94</p>
                </div>

            </div>

            <div class="layout-inline row">

                <div class="col col-pro layout-inline">
                    <img src="${pageContext.request.contextPath}/image/PathOggetti/5/1.jpg" alt="kitten" />
                    <p>Curious Little Begger</p>
                </div>

                <div class="col col-price col-numeric align-center ">
                    <p>£59.99</p>
                </div>

                <div class="col col-qty layout-inline">
                    <a href="#" class="qty qty-minus">-</a>
                    <input type="numeric" value="3" />
                    <a href="#" class="qty qty-plus">+</a>
                </div>

                <div class="col col-vat col-numeric">
                    <p>£2.95</p>
                </div>
                <div class="col col-total col-numeric">
                    <p>£182.95</p>
                </div>
            </div>

            <div class="tf">
                <div class="row layout-inline">
                    <div class="col">
                        <p>VAT</p>
                    </div>
                    <div class="col"></div>
                </div>
                <div class="row layout-inline">
                    <div class="col">
                        <p>Shipping</p>
                    </div>
                    <div class="col"></div>
                </div>
                <div class="row layout-inline">
                    <div class="col">
                        <p>Total</p>
                    </div>
                    <div class="col"></div>
                </div>
            </div>
        </div>

        <a href="#" class="btn btn-update">Update cart</a>

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

    // SHOPPING CART PLUS OR MINUS
    $('a.qty-minus').on('click', function(e) {
        e.preventDefault();
        var $this = $(this);
        var $input = $this.closest('div').find('input');
        var value = parseInt($input.val());

        if (value > 1) {
            value = value - 1;
        } else {
            value = 0;
        }

        $input.val(value);

    });

    $('a.qty-plus').on('click', function(e) {
        e.preventDefault();
        var $this = $(this);
        var $input = $this.closest('div').find('input');
        var value = parseInt($input.val());

        if (value < 100) {
            value = value + 1;
        } else {
            value =100;
        }

        $input.val(value);
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