//Mostra e nasconde il form di login e registrazione
function LogRegForm(){
    $("#LogRegForm").toggle();
}

//cambia da login form a register form senza dover ricaricare la pagina
function SwitchToLogin(){
    $("#login_form").toggle();
    $("#registration_form").toggle();
}

function SwitchToRegister(){
    $("#login_form").toggle();
    $("#registration_form").toggle();
}
//endregion

//region AJAX FUNCTIONS
function checkLogin(){
    let email = $("#login_email").val();
    let pass = $("#login_password").val();
    let errorBox = $("#login_error");

    // if(!email || !pass){
    //     errorBox.text("Riempi correttamente i campi!");
    //     return;
    // }

    $.post(
        "LogRegServlet",
        {
            login_email: email,
            login_password: pass,
            form_type: 0
        },
        function(xml){
            if(xml) {
                let errorMessage = $(xml).find("error_message").text();
                errorBox.text(errorMessage);
            } else
                location.reload();
        }
    );
}

function checkRegister(){
    let email = $("#registration_email").val();
    let confirm_email = $("#confirm_registration_email").val();
    let username = $("#registration_username").val();
    let password = $("#registration_password").val();

    let errorBox = $("#registration_error");

    if(!email || !password || !username){
        errorBox.text("Riempi correttamente i campi!");
        return;
    }

    if(email !== confirm_email){
        errorBox.text("Le email non combaciano! JS");
        return;
    }

    $.post(
        "LogRegServlet",
        {
            registration_email: email,
            registration_confirm_email: confirm_email,
            registration_password: password,
            registration_username: username,
            form_type: 1
        },
        function(xml){
            if(xml) {
                let errorMessage = $(xml).find("error_message").text();
                errorBox.text(errorMessage);
            } else
                location.reload();
        }
    );
}

function logout() {
    console.log("logout");
    $.post(
        "LogRegServlet",
        {
            form_type: 2
        },
        function(xml){
            location.reload();
        }
    );
}
//endregion