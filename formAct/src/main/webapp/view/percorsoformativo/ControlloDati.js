$(document).ready(function() {
	$("#controlloDati").click(function() {
		var giorno = $('#giorno').val();
		var idDisponibilita = $('#orario').val();
		var costo = $('#costo').val();
		var email = $('#email').val();
		var numeroCarta = $('#numeroCarta').val();
		var cvv = $('#cvv').val();
		var meseScadenza = $('#meseScadenza').val();
		var annoScadenza = $('#annoScadenza').val();
		var societa = ""
				
		var regExpressionVisa = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
		var regExpressionMasterCard =  /^(?:5[1-5][0-9]{14})$/;
		var regExpressionNumeri = /^[0-9]+$/;
		
		if (!isEmpty(giorno) && !isEmpty(idDisponibilita) && !isEmpty(numeroCarta) 
				&& !isEmpty(cvv) && !isEmpty(meseScadenza) && !isEmpty(annoScadenza)) {
			
			// controllo numero carta
			if (numeroCarta.match(regExpressionVisa)) {
				societa = "Visa";
			}
			else if (numeroCarta.match(regExpressionMasterCard)) {
				societa = "MasterCard";
			}
			else {
				alert ("Numero carta non valida");
				return;
			}
			
			// controllo cvv
			if(!cvv.match(regExpressionNumeri) || cvv.length != 3) {
				alert ("Numero CVV non valido");
				return;
			}
			
			// controllo meseScadenza
			var dataAttuale = new Date();
			var meseAttuale = dataAttuale.getMonth() + 1;
			var annoAttuale = dataAttuale.getFullYear();
			//alert(dataAttuale + '\n' + meseAttuale + '\n' + annoAttuale + '\n');
			if (annoScadenza == annoAttuale) {
				if (meseScadenza < meseAttuale) {
					alert ("Data non valida");
				}
			}
		}
		else {
			alert ("Dati mancanti");
			return;
		}
		
		$.post("http://localhost:8080/formAct/IscrizionePercorsoFormativoServlet",
		{
			action: "iscrizionePercorsoFormativo",
			giorno: giorno,
			idDisponibilita: idDisponibilita,
			costo: costo,
			email: email,
			numeroCarta: numeroCarta,
			cvv: cvv,
			meseScadenza: meseScadenza,
			annoScadenza: annoScadenza,
			societa: societa
		},
		function() {
			alert("Iscrizione effettuata");
			window.location.href = '/formAct/view/percorsoformativo/IndexIscrizionePercorsoFormativo.jsp';
		})
    	.fail(function() {
    		alert("Fallimento");
    	});
	});
	
	function isEmpty (parametro) {
		if (parametro == "" || parametro == null || parametro == undefined) {
			return true;
		}
		return false;
	}
});