/**
 * 
 */

$.getScript("/formAct/view/js/validazioniForm/Controlli.js");

function accedi() {
    
    // Svuoto i vari tag di errore 
    $("#erroreEmail").text("");
    $("#errorePassword").text("");
    $("#errore").text("");
    
    // Variabili associate ai campi del modulo
    var email, password;
    email = $("#email").val();
    password = $("#password").val();
    //alert(email + "\n" + password);
    
    // espressioni regolari
    var regexEmail = /^[A-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]{2,10}$/;

    var errori = 0;

    // controllo se almeno un campo è vuoto
    if(isEmpty(email) || isEmpty(password)) {
        alert ("Dati mancanti");
    	errori = 1;
    }

    if (errori >= 1) {
		$('html, body').animate({scrollTop: 0}, 100);
	}
	else {

        errori += erroreParametroRegex (email, regexEmail, "#erroreEmail", "*Email non valida");
        errori += erroreParametroValMin (password.length, 8, "#errorePassword", "*Password non valida");

    }

    if (errori >= 1) {
        $('html, body').animate({scrollTop: 0}, 100);
    }
    else {
        $("#loginForm").submit();
    }

};

function modificaPassword () {
    
    // Svuoto i vari tag di errore 
    $("#errorePasswordAttuale").text("");
    $("#erroreNuovaPassword").text("");
    $("#erroreConfermaPassword").text("");

    // Variabili associate ai campi del modulo
    var passwordAttuale, nuovaPassword, confermaPassword;
    passwordAttuale = $("#passwordAttuale").val();
    nuovaPassword = $("#nuovaPassword").val();
    confermaPassword = $("#confermaPassword").val();

    var errori = 0;

    // controllo se almeno un campo è vuoto
    if(isEmpty(passwordAttuale) || isEmpty(nuovaPassword) || isEmpty(confermaPassword)) {
        alert ("Dati mancanti");
    	errori = 1;
    }

    if (errori >= 1) {
		$('html, body').animate({scrollTop: 0}, 100);
	}
    else {

        var tmp = errori;
        errori += erroreParametroValMin(passwordAttuale.length, 8, "#errorePasswordAttuale", "*La Password inserita non e' quella attuale");
        if (tmp == errori) {
            tmp = errori
            errori += erroreParametroValMin(nuovaPassword.length, 8, "#erroreNuovaPassword", "*Nuova password non valida");
            if (tmp == errori) {
                errori += erroreParametroStringEquals(nuovaPassword, confermaPassword, "#erroreConfermaPassword", "*Password ripetuta diversa");
            }
        }

        if (errori >= 1) {
			$('html, body').animate({scrollTop: 0}, 100);
		}
		else {
			$("#modificaPasswordForm").submit();
		}

    }
};

function modificaProfilo () {

    // Svuoto i vari tag di errore 
    $("#erroreEmail").text("");
    $("#erroreCC").text("");

    // Variabili associate ai campi del modulo
    var ruolo, email, cc;
    ruolo = $("#ruolo").val();
    if (ruolo === "Studente" || ruolo === "Formatore") {
        email = $("#email").val();
    }
    if (ruolo === "Formatore") {
        cc = $("#cc").val();
    }
    
    // espressioni regolari
    var regexEmail = /^[A-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]{2,10}$/;
    
    var errori = 0;
    
    // controllo se almeno un campo è vuoto
    if (ruolo === "Studente" || ruolo === "Formatore") {
        if(isEmpty(email)) {
            alert ("Dati mancanti");
    	    errori = 1;
        }
    }
    if (ruolo === "Formatore") {
        if(isEmpty(cc)) {
            if (errori == 0) {
            	alert ("Dati mancanti");
				errori = 1;
			}
        }
    }
    
    //alert(ruolo); 
    //alert(email);
    //if (ruolo === "Formatore") {
    //    alert(cc);
    //}
    
    //alert (email + "\n" + country);
    //alert(errori);
    if (errori >= 1) {
		$('html, body').animate({scrollTop: 0}, 100);
	}
	else {
		errori += erroreParametroRegex(email, regexEmail, "#erroreEmail", "*Email non valida");
        if (ruolo === "Formatore") {
            errori += erroreParametroRangeValEsterno(cc.length, 28, 28, "#erroreCC", "*Conto Corrente non valido");
        }
	}

    if (errori >= 1) {
        $('html, body').animate({scrollTop: 0}, 100);
    }
    else {
        $("#modificaProfiloForm").submit();
    }

}









