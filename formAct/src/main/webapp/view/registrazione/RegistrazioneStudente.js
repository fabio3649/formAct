$(document).ready(function() {
	$("#registrazioneStudenteButton").click(function() {
		// mi ricavo tutti i valori dei campi del form
		// anche quelli non compilati
		var email = $("#email").val();
		var password = $("#password").val();
		var confermaPassword = $("#password2").val();
		var nome = $("#name").val();
		var cognome = $("#surname").val();
		var sesso = $("input[name='gender']:checked").val();
		var birthdate = $("#birthdate").val();
		var paeseOrigine = $("#country").val();
		
		var regexEmail = /^[A-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]{2,10}$/;
		var regexNome = new RegExp("^[A-z\xC0-\xF9 '-]{2,30}$");
		var regexCognome = new RegExp("^[A-z\xC0-\xF9 '-]{2,30}$");

		// se tutti i campi sono compilati
		if (!isEmpty(email) && !isEmpty(password) && !isEmpty(confermaPassword) 
				&& !isEmpty(nome) && !isEmpty(cognome) && !isEmpty(sesso) && !isEmpty(birthdate)
				&& !isEmpty(paeseOrigine)) {
						
			var controllo = false;
			
			// Svuoto i vari tag <p> di errore 
			$("#erroreEmail").text("");
			$("#errorePassword1").text("");
			$("#errorePassword2").text("");
			$("#erroreName").text("");
			$("#erroreSurname").text("");
			$("#erroreBirthdate").text("");
			$("#errorePaeseOrigine").text("");
			
			// controllo se l'email inserita è valida
			if (!email.match(regexEmail)) {
				//Errore
				var str = "*Email non valida";
				$("#erroreEmail").text(str);
				controllo = true;
			}
			
			// controllo se la password inserita è valida
			if (password < 8) {
				//Errore
				var str = "*Password non valida";
				$("#errorePassword1").text(str);
				controllo = true;
			}
			
			// Controllo se la password di conferma inserita 
			// è uguale alla password
			if(confermaPassword != password) {
				//Errore
				var str = "*Password ripetuta diversa";
				$("#errorePassword2").text(str);
				controllo = true;
			}
			
			// controllo se il nome inserito è valido
			if(!nome.match(regexNome)) {
				//Errore
				var str = "*Nome non valido";
				$("#erroreName").text(str);
				controllo = true;
			}
			
			// controllo se il cognome inserito è valido
			if(!cognome.match(regexCognome)) {
				//Errore
				var str = "*Cognome non valido";
				$("#erroreSurname").text(str);
				controllo = true;
			}
			
			// mi ricavo la data e l'anno attuale
			var dataAttuale = new Date();
			var annoCorrente = dataAttuale.getFullYear();
			
			// mi ricavo la data di nascita e l'anno di nascita
			var dataNascita = new Date(birthdate); 
			var annoNascita = dataNascita.getFullYear();
			
			// controllo se la data di nascita inserita è valida
			if ( (annoNascita > annoCorrente - 14) || (annoNascita < annoCorrente - 100) ) {
				// Errore
				var str = "*Data di nascita non valida";
				$("#erroreBirthdate").text(str);
				controllo = true;
			}
			
			// se almento un informazione inserita non è valida
			// allora ritorno in alto alla pagina
			if (controllo == true) {
				$('html, body').animate({scrollTop: 0}, 100);
			}
			// se tutte le informazioni inserite sono valide allora
			// eseguo una richiesta post
			else {
				$.post("http://localhost:8080/formAct/CheckStudentRegister",
				{
					email: email,
					password: password,
					password2: confermaPassword,
					name: nome,
					surname: cognome,
					gender: sesso,
					birthdate: birthdate,
					country: paeseOrigine,
				},
				// se la richiesta post è andata a buon fine
				function(risultatiJSON) {
					var risultati = JSON.parse(risultatiJSON);
					
					// se l'email inserita è già presente nel DB
					if (risultati[0] == true) {
						var str = "*Esiste gia\' uno studente con questa email";
						$("#erroreEmail").text(str);		
					}
					
					// se l'email inserita
					// non è presente nel DB (nessun errore)
					if (risultati[0] == false) {
						// se lo studente è stato salvato nel DB
						if (risultati[1] == true) {
							alert("Registrazione effettuata con successo");
							window.location.href = '/formAct/view/registrazione/registrazioneStudente.jsp';
						}
						// se lo studente non è stato salvato nel DB
						else {
							alert("Registrazione fallita");
						}
					}
					// se l'email inserita 
					// è già presente nel DB (errore)
					// allora ritorno in alto alla pagina
					else {
						$('html, body').animate({scrollTop: 0}, 100);
					}
				})
				// se la richiesta post è fallita
    			.fail(function() {
 					alert("Errore");
 				});
			}
		}
		// se almeno un campo è vuoto
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