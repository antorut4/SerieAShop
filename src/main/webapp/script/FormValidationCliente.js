function LogRegForm() {
    $("#LogRegForm").toggle();
}

function nomeClienteValidation(obj) {
    var nomeCliente = obj.value;
    var regEx = new RegExp("^[a-zA-Z,.'-]{1,50}$");

    if (regEx.exec(nomeCliente) == null) {
        obj.style.border = "2px solid red";
        window.alert("Errore Inserimento Nome. Modificare in modo appropriato, niente caratteri speciali");
    } else {
        obj.style.border = "2px solid lime";
    }
}

function cognomeValidation(obj) {
    var cognome = obj.value;
    var regEx = new RegExp("^[a-zA-Z,.'-]{1,50}$");

    if (regEx.exec(cognome) == null) {
        obj.style.border = "2px solid red";
        window.alert("Errore Inserimento Cognome. Modificare in modo appropriato, niente caratteri speciali");
    } else {
        obj.style.border = "2px solid lime";
    }
}

function usernameValidation(obj) {
    var username = obj.value;
    var regEx = new RegExp("^[a-zA-Z0-9._%+-]{1,50}$");

    if (regEx.exec(username) == null) {
        obj.style.border = "2px solid red";
        window.alert("Errore Inserimento Username. Modificare in modo appropriato, niente spazi");
    } else {
        obj.style.border = "2px solid lime";
    }
}

function emailValidation(obj) {
    var email = obj.value;
    var regEx = new RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");

    if (regEx.exec(email) == null) {
        obj.style.border = "2px solid red";
        window.alert("Formato email non valido. Inserire un indirizzo email valido.");
    } else {
        obj.style.border = "2px solid lime";
    }
}

// Attach the validation functions to the form's onchange event
document.getElementById("myForm").onchange = function () {
    emailValidation(document.getElementById("emailInput"));
};

function passwordValidation(obj) {
    var password = obj.value;
    var regEx = /^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+]).{8,}$/;

    if (regEx.test(password)) {
        obj.style.border = "2px solid lime";
    } else {
        obj.style.border = "2px solid red";
    }
}

function indirizzoValidation(obj) {
    var indirizzo = obj.value;
    var regEx = new RegExp("^[a-zA-Z\\s\\d'’\\/\\-\\.]+$");

    if (regEx.exec(indirizzo) == null) {
        obj.style.border = "2px solid red";
    } else {
        obj.style.border = "2px solid lime";
    }
}

function telefonoValidation(obj) {
    var telefono = obj.value;
    var regEx = new RegExp("^(\\+39)?\\s?(\\d{3})\\s?(\\d{3})\\s?(\\d{4})$");

    if (regEx.exec(telefono) == null) {
        obj.style.border = "2px solid red";
    } else {
        obj.style.border = "2px solid lime";
    }
}