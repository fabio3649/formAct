$(document).ready(function() {
	$("#iscrizioneFormatoreButton").click(function() {
		var email = $("#email").val();
		var password = $("#password").val();
		var confermaPassword = $("#password2").val();
		var nome = $("#name").val();
		var cognome = $("#surname").val();
		var sesso = $("input[name='gender']:checked").val();
		var birthdate = $("#birthdate").val();
		var paeseOrigine = $("#country").val();
		var codiceFiscale = $("#cf").val();
		var contoCorrente = $("#numCC").val();
		
		var regexEmail = /^[A-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]{2,10}$/;
		var regexNome = new RegExp("^[A-z\xC0-\xF9 '-]{2,30}$");
		var regexCognome = new RegExp("^[A-z\xC0-\xF9 '-]{2,30}$");
		var regexCodiceFiscale = /^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$/;
			
		if (!isEmpty(email) && !isEmpty(password) && !isEmpty(confermaPassword) 
				&& !isEmpty(nome) && !isEmpty(cognome) && !isEmpty(sesso) && !isEmpty(birthdate)
				&& !isEmpty(paeseOrigine) && !isEmpty(codiceFiscale) && !isEmpty(contoCorrente)) {
			
			var controllo = false;
			
			$("#erroreEmail").text("");
			$("#errorePassword1").text("");
			$("#errorePassword2").text("");
			$("#erroreName").text("");
			$("#erroreSurname").text("");
			$("#erroreBirthdate").text("");
			$("#errorePaeseOrigine").text("");
			$("#erroreCF").text("");
			$("#erroreCC").text("");
						
			if (!email.match(regexEmail)) {
				//Errore
				var str = "*Email non valida";
				$("#erroreEmail").text(str);
				controllo = true;
			}
			if (password < 8) {
				//Errore
				var str = "*Password non valida";
				$("#errorePassword1").text(str);
				controllo = true;
			}
			if(!confermaPassword.match(password)) {
				//Errore
				if (password.length >= 8) {
					var str = "*Password ripetuta diversa";
					$("#errorePassword2").text(str);
					controllo = true;
				}
			}
			if(!nome.match(regexNome)) {
				//Errore
				var str = "*Nome non valido";
				$("#erroreName").text(str);
				controllo = true;
			}
			if(!cognome.match(regexCognome)) {
				//Errore
				var str = "*Cognome non valido";
				$("#erroreSurname").text(str);
				controllo = true;
			}
			
			var dataAttuale = new Date();
			var annoCorrente = dataAttuale.getFullYear();
			var meseCorrente = dataAttuale.getMonth() + 1;
			var giornoCorrente = dataAttuale.getDate();
			var dataNascita = new Date(birthdate); 
			var annoNascita = dataNascita.getFullYear();
			var meseNascita = dataNascita.getMonth() + 1;
			var giornoNascita = dataNascita.getDate();
			
			if ( (annoNascita > annoCorrente - 20) || (annoNascita < annoCorrente - 100) ) {
				// Errore
				var str = "*Data di nascita non valida";
				$("#erroreBirthdate").text(str);
				controllo = true;
			}
			
			if(!codiceFiscale.match(regexCodiceFiscale)) {
				//Errore
				var str = "*Codice Fiscale non valido";
				$("#erroreCF").text(str);
				controllo = true;
			}
			if(contoCorrente.length < 27 || contoCorrente.length > 27) {
				//Errore
				var str = "*Conto Corrente non valido";
				$("#erroreCC").text(str);
				controllo = true;
			}
			if (controllo == true) {
				$('html, body').animate({scrollTop: 0}, 100);
			}
			else {
				$.post("http://localhost:8080/formAct/CheckTrainerRegister",
				{
					email: email,
					password: password,
					password2: confermaPassword,
					name: nome,
					surname: cognome,
					gender: sesso,
					birthdate: birthdate,
					country: paeseOrigine,
					cf: codiceFiscale,
					numCC: contoCorrente
				},
				function(risultatiJSON) {
					var risultati = JSON.parse(risultatiJSON);
					if (risultati[0] == true) {
						var str = "*Esiste gia\' un formatore con questo codice fiscale";
						$("#erroreCF").text(str);			
					}
					if (risultati[1] == true) {
						var str = "*Esiste gia\' un formatore con questa email";
						$("#erroreEmail").text(str);		
					}
					if ((risultati[0] == false) && (risultati[1] == false)) {
						if (risultati[2] == true) {
							alert("Registrazione effettuata con successo");
						}
						else {
							alert("Registrazione fallita");
						}
					}
					else {
						$('html, body').animate({scrollTop: 0}, 100);
					}
				})
    			.fail(function() {
 					alert("Errore");
 				});
    		}
		}
		else {
			alert ("Dati mancanti");
		}
	});
	
	function isEmpty (parametro) {
		if (parametro == "" || parametro == null || parametro == undefined) {
			return true;
		}
		return false;
	}
});