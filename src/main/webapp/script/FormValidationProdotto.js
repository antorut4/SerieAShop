function nomeProdValidation(obj)
{
    var nomeProdotto= obj.value;
    var regEx= new RegExp ("^[a-zA-Z, .'-]{1,50}$");

    if (regEx.exec(nomeProdotto) == null) {
        obj.style.border = "2px solid red";
        alert("Nome prodotto errato");
    }

    else
        obj.style.border="2px solid lime";

}

function prezzoValidation(obj)
{
    var prezzo= obj.value;
    var regEx= new RegExp ("(?:0\\.[0-9]{1,3}|[1-9]{1}[0-9]*(\\.[0-9]{1,3})?|0)$");

    if (regEx.exec(prezzo) == null) {
        obj.style.border = "2px solid red";
        alert("Prezzo del prodotto errato");
    }

    else
        obj.style.border="2px solid lime";


}

function descrizioneValidation(obj)
{
    var descr = obj.value;
    var regEx = new RegExp("^[a-zA-Z ,.'-]{1,400}$")

    if(regEx.exec(descr)==null) {
        obj.style.border = "2px solid red";
        alert("Descrizione del prodotto errata");
    }

    else
        obj.style.border="2px solid lime";
}

function quantitaValidation(obj)
{
    var quantita = obj.value;
    var regEx = new RegExp("^0$|^[1-9][0-9]*$")

    if(regEx.exec(quantita)==null) {
        obj.style.border = "2px solid red";
        alert("Quantità del prodotto errata");
    }

    else
        obj.style.border="2px solid lime";
}

function imageValidation(obj)
{
    var img= obj.value;
    var regEx= new RegExp ("[^\\s]+(.*?)\\.(jpg|jpeg|png|gif|JPG|JPEG|PNG|GIF)$");

    if (regEx.exec(img) == null) {
        obj.style.border = "2px solid red";
        alert("immagine del prodotto errata");
    }
    else
        obj.style.border="2px solid lime";

}