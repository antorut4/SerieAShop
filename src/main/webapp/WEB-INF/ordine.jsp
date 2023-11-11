<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ordine.css">
</head>
<body>

<div class="wrapper">
    <div class="container">
        <form action="crea-ordine">
            <h1 class="heading">
                <i class="fas fa-shipping-fast"></i>
                Dettagli di spedizione
            </h1>
            <div class="street">
                <label for="name">Indirizzo</label>
                <input type="text" name="address" id="name" class="input" onblur="checkAddress(this.form.address)">
            </div>
            <div class="address-info">
                <div>
                    <label for="city">Città</label>
                    <input type="text" name="city" id="city" class="input" onblur="checkCity(this.form.city)">
                </div>
                <div>
                    <label for="zip">CAP</label>
                    <input type="text" name="cap" id="zip" class="input" onblur="checkZip(this.form.zip)">
                </div>
            </div>
            <h1 class="heading">
                <i class="far fa-credit-card"></i> Informazioni di pagamento
            </h1>
            <div class="cc-num">
                <label for="cardnum">Numero di carta di credito</label>
                <input type="text" name="card-num" id="cardnum" class="input" onblur="checkCreditCardNumber(this.form.cardnum)">
            </div>
            <div class="cc-info">
                <div>
                    <label for="scad">Scadenza</label>
                    <input type="text" name="scad" id="scad" class="input" onblur="checkCreditScad(this.form.scad)">
                </div>
                <div>
                    <label for="security">CVV</label>
                    <input type="text" name="security" id="security" class="input" onblur="checkCvc(this.form.security)">
                </div>
            </div>
            <div class="btns">
                <button type="submit" name="button" value="acquista" class="btn">Acquista</button>
                <button type="submit" name="button" value="back" class="btn">Torna al carrello</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>

<script>
    function checkZip(obj) {
        var cap = obj.value;
        var regEx = new RegExp("^[0-9]{5,}$");

        if (regEx.exec(cap) == null) {
            obj.style.border = "2px solid red";
            alert("Invalid cap format. Please enter a valid CAP.");
        } else {
            obj.style.border = "2px solid lime";
        }
    }

    function checkCreditCardNumber(obj) {
        var card = obj.value;
        var regEx = new RegExp("^[0-9]{16}$");

        if (regEx.exec(card) == null) {
            obj.style.border = "2px solid red";
            alert("Invalid card number format. Please enter a valid CreditCard.");
        } else {
            obj.style.border = "2px solid lime";
        }
    }

    function checkCreditScad(obj){
        var card = obj.value;
        var regEx = new RegExp("^([0-9]{2})/([0-9]{2})$");

        if (regEx.exec(card) == null) {
            obj.style.border = "2px solid red";
            alert("Invalid scad format. Please enter a valid CreditCard Scad.");
        } else {
            obj.style.border = "2px solid lime";
        }
    }

    function checkCvc(obj){
        var cardcvc = obj.value;
        var regEx = new RegExp("^[0-9]{3}$");

        if (regEx.exec(cardcvc) == null) {
            obj.style.border = "2px solid red";
            alert("Invalid cvc format. Please enter a valid CVC.");
        } else {
            obj.style.border = "2px solid lime";
        }
    }

    function checkCity(obj){
        var address = obj.value;
        var regEx = new RegExp("^[a-zA-Z ]{3,}$");

        if (regEx.exec(address) == null) {
            obj.style.border = "2px solid red";
            alert("Invalid city format. Please enter a valid City.");
        } else {
            obj.style.border = "2px solid lime";
        }
    }

    function checkAddress(obj){
        var address = obj.value;
        var regEx = new RegExp("^[a-zA-Z ]{3,}$");

        if (regEx.exec(address) == null) {
            obj.style.border = "2px solid red";
            alert("Invalid scad format. Please enter a valid address.");
        } else {
            obj.style.border = "2px solid lime";
        }
    }
</script>
