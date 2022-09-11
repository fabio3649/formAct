/**
 * 
 */
$.getScript("/formAct/view/js/validazioniForm/Controlli.js");

function registrazioneUtente () {

    // Svuoto i vari tag di errore 
	$("#erroreEmail").text("");
	$("#errorePassword").text("");
	$("#erroreConfermaPassword").text("");
	$("#erroreName").text("");
	$("#erroreSurname").text("");
	$("#erroreBirthdate").text("");
	$("#erroreCountry").text("");
	$("#erroreCF").text("");
	$("#erroreNumCC").text("");

    var tipoIscrizione = $("#tipoIscrizione").val();

    // Variabili associate ai campi del modulo
	var email, password, confermaPassword, name, surname, gender, birthdate, country;
    var cf, numCC;
	if (tipoIscrizione == 1 || tipoIscrizione == 2) {
		
		email = $("#email").val();
        password = $("#password").val();
    	confermaPassword = $("#confermaPassword").val();
    	name = $("#name").val();
    	surname = $("#surname").val();
    	gender = $("input[name='gender']:checked").val();
    	birthdate = $("#birthdate").val();
    	country = $("#country").val();
		
	}
	if (tipoIscrizione == 2) {
		cf = $("#cf").val();
		numCC = $("#numCC").val();
	}

    // espressioni regolari
    var regexEmail = /^[A-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]{2,10}$/;
    var regexName = new RegExp("^[A-z\xC0-\xF9 '-]{2,30}$");
    var regexSurname = new RegExp("^[A-z\xC0-\xF9 '-]{2,30}$");
    var regexCodiceFiscale = /^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$/;

    var errori = 0;
    
    // studente (1) oppure formatore(2)
	if (tipoIscrizione == 1 || tipoIscrizione == 2) {
        // controllo se almeno un campo è vuoto
        if (       isEmpty(email)     || isEmpty(password) || isEmpty(confermaPassword) 
        		|| isEmpty(name)      || isEmpty(surname)  || isEmpty(gender) 
        		|| isEmpty(birthdate) || isEmpty(country)) { 
            
            alert ("Dati mancanti");
    		errori = 1;
            
        }
    }
	// solo formatore(2)
	if (tipoIscrizione == 2) {
        // controllos se almeno un campo è vuoto
		if (isEmpty(cf) || isEmpty(numCC)) {
			if (errori == 0) {
            	alert ("Dati mancanti");
				errori = 1;
			}
		}
	}
    
	if (errori >= 1) {
		$('html, body').animate({scrollTop: 0}, 100);
	}
	else {

		// studente (1) oppure formatore (2)
		if (tipoIscrizione == 1 || tipoIscrizione == 2) {
                
			errori += erroreParametroRegex(email, regexEmail, "#erroreEmail", "*Email non valida");
			errori += erroreParametroValMin(password.length, 8, "#errorePassword", "*Password non valida, inserisci almeno 8 caratteri");
			errori += erroreParametroStringEquals(password, confermaPassword, "#erroreConfermaPassword", "*Conferma password non corrisponde");
			errori += erroreParametroRegex(name, regexName, "#erroreName", "*Nome non valido");
			errori += erroreParametroRegex(surname, regexSurname, "#erroreSurname", "*Cognome non valido");
			var dataAttuale = new Date();
			var annoAttuale = dataAttuale.getFullYear();
			var dataNascita = new Date(birthdate); 
			var annoNascita = dataNascita.getFullYear();
			var limiteMin, limiteMaxS , limiteMaxF;
			limiteMin = annoAttuale - 99;
			// studente (1)
			if (tipoIscrizione == 1) {
				limiteMaxS = annoAttuale - 14;
				errori += erroreParametroValMax(annoNascita, limiteMaxS , "#erroreBirthdate", "*Anno non valido, devi avere almeno 14 anni");
			}
			// formatore (2)
			if (tipoIscrizione == 2) {
				limiteMaxF = annoAttuale - 20;
				errori += erroreParametroValMax(annoNascita, limiteMaxF , "#erroreBirthdate", "*Anno non valido, devi avere almeno 20 anni");
			}
			
			errori += erroreParametroValMin(annoNascita, limiteMin  , "#erroreBirthdate", "*Anno non valido");
			
			errori += erroreParametroValMax(annoNascita, 2021 , "#erroreBirthdate", "*Anno non valido");
			
		}
		// solo formatore (2) 
		if (tipoIscrizione == 2) {

			errori += erroreParametroRegex(cf, regexCodiceFiscale, "#erroreCF", "*Codice Fiscale non valido");
			errori += erroreParametroRangeValEsterno(numCC.length, 28, 28, "#erroreNumCC", "*Conto Corrente non valido");

		}

		if (errori >= 1) {
			$('html, body').animate({scrollTop: 0}, 100);
		}
		else {
			$("#registrazioneUtenteForm").submit();
		}

	}

}









