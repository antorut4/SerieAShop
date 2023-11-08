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
                <input type="text" name="address" id="name" class="input">
            </div>
            <div class="address-info">
                <div>
                    <label for="city">Città</label>
                    <input type="text" name="city" id="city" class="input">
                </div>
                <div>
                    <label for="zip">CAP</label>
                    <input type="text" name="cap" id="zip" class="input">
                </div>
            </div>
            <h1 class="heading">
                <i class="far fa-credit-card"></i> Informazioni di pagamento
            </h1>
            <div class="cc-num">
                <label for="card-num">Numero di carta di credito</label>
                <input type="text" name="card-num" id="card-num" class="input">
            </div>
            <div class="cc-info">
                <div>
                    <label for="scad">Scadenza</label>
                    <input type="text" name="scad" id="scad" class="input">
                </div>
                <div>
                    <label for="security">CVV</label>
                    <input type="text" name="security" id="security" class="input">
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
