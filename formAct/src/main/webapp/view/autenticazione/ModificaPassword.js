$(document).ready(function() {
	$("#modificaPasswordButton").click(function() {
		var passwordAttuale = $("#password").val();
		var nuovaPassword = $("#password2").val();
		var confermaPassword = $("#confermaPassword").val();
		
		$("#errorePassword1").text("");
		$("#errorePassword2").text("");
		$("#errorePassword3").text("");
		
		if (passwordAttuale.length != "" && passwordAttuale.length != "" && nuovaPassword.length != "") {
			var controllo = false;
			
			// controllo se la password attuale inserita ha una lunghezza < 8		
			if (passwordAttuale.length < 8) {
				//Errore
				var str = "*Password sbagliata";
				$("#errorePassword1").text(str);
				controllo = true;
				$('html, body').animate({scrollTop: 0}, 100);			
			}
			// se la sua lunghezza è >= 8 allora è valida e vado avanti
			else {
				// controllo se la nuova password inserita ha una lunghezza < 8	
				if (nuovaPassword.length < 8) {
					//Errore
					var str = "*Nuova password non valida";
					$("#errorePassword2").text(str);
					controllo = true;
					$('html, body').animate({scrollTop: 0}, 100);
				}
				// se la sua lunghezza è >= 8 allora è valida e vado avanti
				else {
					// controllo se la password di conferma è diversa dalla nuova password
					if (confermaPassword != nuovaPassword) {
						//Errore
						var str = "*Nuova password ripetuta diversa";
						$("#errorePassword3").text(str);
						controllo = true;
						$('html, body').animate({scrollTop: 0}, 100);			
					}
					// se sono uguali allora la password di conferma è valida e vado avanti
					else {
						// eseguo una richiesta post
						$.post("http://localhost:8080/formAct/ModificaPasswordServlet",
						{
							passwordAttuale: passwordAttuale,
							nuovaPassword: nuovaPassword,
							confermaPassword: confermaPassword
						},
						// nel caso in cui la richiesta post è andata a buon fine
						function(risultatiJSON) {
							
							var risultati = JSON.parse(risultatiJSON);
							// controllo ae la password attuale non corrisponde a quella del DB (errore)
							if (risultati[0] == true) {
								var str = "*Password sbagliata";
								$("#errorePassword1").text(str);
							}
							// se corrisponde allora non c'è nessun errore e vado avanti
							if (risultati[0] == false) {
								// se risultati[1] = true allora significa che la password è stata modificata
								if (risultati[1] == true) {
									alert("Modifica password effettuata con successo");
									window.location.href = '/formAct/view/autenticazione/ModificaPassword.jsp';
								}
								// altrimenti la password non è stata salvata
								else {
									alert("Modifica password fallita");
								}
							}
							else {
								$('html, body').animate({scrollTop: 0}, 100);
							}
							
						})
						// nel caso in cui la richiesta post non è andata a buon fine
    					.fail(function() {
 							var str = "*La nuova password e\' uguale a quella esistente";
							$("#errorePassword2").text(str);
 						});
					}
				}
			}
			if (controllo == true) {
				$('html, body').animate({scrollTop: 0}, 100);
			}
    	}
    	else {
			alert ("Dati mancanti");
		}
	});
});