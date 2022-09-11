/**
 * 
 */
 
 $.getScript("/formAct/view/js/validazioniForm/Controlli.js");
 
 function iscrizionePercorsoFormativo() {
	
	// Svuoto i vari tag di errore 
	$("#erroreMetodoPagamento").text("");
	$("#erroreNumeroCarta").text("");
	$("#erroreCVV").text("");
	$("#erroreMeseScadenza").text("");
	$("#erroreAnnoScadenza").text("");
	$("#erroreDisponibilita").text("");
	
	// Variabili associate ai campi del modulo
	var metodoPagamento, numeroCarta, cvv, meseScadenza, annoScadenza, giorniSelezionati;
	metodoPagamento = $("#metodoPagamento").val();
	numeroCarta = $("#numeroCarta").val();
	cvv = $("#cvv").val();
	meseScadenza = $("#meseScadenza").val();
	annoScadenza = $("#annoScadenza").val();
    giorniSelezionati = Array();
    $("input[class=giorni]").each(function () {
        var ischecked = $(this).is(":checked");
        if (ischecked) {
            giorniSelezionati.push($(this).val());
        }
    });
    
    // espressioni regolari
    var regexVisa = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
    var regexMastercard = /^(?:5[1-5][0-9]{14})$/;
    
    var errori = 0;
    
    // controllo il metodo di pagamento.
    errori += erroreEmpty(metodoPagamento, "#erroreMetodoPagamento", "*Selezionare un tipo di carta.");
    let tmp = 0;
	if (errori == 0) {
		// controllo il numero della carta e se il metodo di pagamento è valido.
		if (metodoPagamento === "visa" || metodoPagamento === "mastercard") {
		    tmp = errori;
		    errori += erroreEmpty(numeroCarta, "#erroreNumeroCarta", "*Inserire il numero della carta.");
		    if (tmp == errori && metodoPagamento === "visa") {
			    errori += erroreParametroRegex (numeroCarta, regexVisa, "#erroreNumeroCarta", "*Il numero della carta e\' errato.");
		    }
		    else if (tmp == errori && metodoPagamento === "mastercard") {
			    errori += erroreParametroRegex (numeroCarta, regexMastercard, "#erroreNumeroCarta", "*Il numero della carta e\' errato.");
		    }
		}
		else {
			errori++;
			$("#erroreMetodoPagamento").text("*Tipo di carta non valida.");
		}
	}
	
	tmp = errori;
	// controllo il cvv.
	errori += erroreEmpty(cvv, "#erroreCVV", "*Inserire il codice CVV.");
	if (tmp == errori) {
		errori += erroreParametroRangeValEsterno(cvv.length, 3, 3, "#erroreCVV", "*Il codice CVV deve contenere 3 cifre.");
	}
	tmp = errori;
	// controllo il mese di scadenza.
	errori += erroreEmpty(meseScadenza, "#erroreMeseScadenza", "*Inserire il mese della scadenza.");
	if (tmp == errori) {
		errori += erroreParametroRangeValEsterno(meseScadenza, 1, 12, "#erroreMeseScadenza", "*Il mese deve avere un valore: 0 < mese < 13.");
	}
	tmp = errori;
	// controllo l'anno di scadenza.
	errori += erroreEmpty(annoScadenza, "#erroreAnnoScadenza", "*Inserire l\'anno della scadenza.");
	if (tmp == errori) {
	    var annoAttuale = new Date().getFullYear();
	    errori += erroreParametroValMin(annoScadenza, annoAttuale + 1, "#erroreAnnoScadenza", "*L\'anno di scadenza deve essere maggiore di " + annoAttuale + ".");
    }
    tmp = errori;
    // controllo le disponibilita'
    errori += erroreParametroValMin(giorniSelezionati.length, 1, "#erroreDisponibilita", "*Selezionare almeno una disponibilita\'");
    
    if (errori >= 1) {
		$('html, body').animate({scrollTop: 0}, 100);
	}
	else {
		$("#iscrizionePercorsoForm").submit();
	}
	
}









