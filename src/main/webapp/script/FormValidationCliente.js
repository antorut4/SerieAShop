function LogRegForm(){
    $("#logreg_form").toggle();
}
function nomeClienteValidation(obj)
{
    var nomeCliente= obj.value;
    var regEx= new RegExp ("^[a-zA-Z,.'-]{1,50}$");

    if (regEx.exec(nomeCliente) == null) {
        obj.style.border = "2px solid red";
    }

    else
        obj.style.border="2px solid lime";

}

function cognomeValidation(obj)
{
    var cognome= obj.value;
    var regEx= new RegExp ("^[a-zA-Z,.'-]{1,50}$");

    if (regEx.exec(cognome) == null) {
        obj.style.border = "2px solid red";
    }

    else
        obj.style.border="2px solid lime";


}

function usernameValidation(obj)
{
    var cognome= obj.value;
    var regEx= new RegExp ("^[a-zA-Z,.'-]{1,50}$");

    if (regEx.exec(cognome) == null) {
        obj.style.border = "2px solid red";
    }

    else
        obj.style.border="2px solid lime";


}

function emailValidation(obj)
{
    var email = obj.value;
    var regEx = new RegExp("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,100}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,100}[a-zA-Z0-9])?)*$")

    if(regEx.exec(email)==null) {
        obj.style.border = "2px solid red";
    }

    else
        obj.style.border="2px solid lime";
}

function passwordValidation(obj)
{
    var password = obj.value;
    var regEx = new RegExp("^([a-zA-Z0-9@*#]{4,})$")

    if (regEx.exec(password) == null)
        obj.style.border = "2px solid red";

    else
        obj.style.border = "2px solid lime";
}

function indirizzoValidation(obj)
{
    var indirizzo = obj.value;
    var regEx= new RegExp ("^[a-zA-Z\\s\\d'’\\/\\-\\.]+$");

    if (regEx.exec(indirizzo) == null) {
        obj.style.border = "2px solid red";
    }
    else
        obj.style.border="2px solid lime";

}

function telefonoValidation(obj)
{
    var telefono = obj.value;
    var regEx= new RegExp ("^(\\+39)?\\s?(\\d{3})\\s?(\\d{3})\\s?(\\d{4})$");

    if (regEx.exec(telefono) == null) {
        obj.style.border = "2px solid red";
    }
    else
        obj.style.border="2px solid lime";

}

