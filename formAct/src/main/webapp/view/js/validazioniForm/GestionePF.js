/**
 * 
 */

$.getScript("/formAct/view/js/validazioniForm/Controlli.js");

function creaPercorsoFormativo() {
	
	// Svuoto i vari tag di errore 
	$("#erroreNome").text("");
	$("#erroreDescrizione").text("");
	$("#erroreIndiceContenuti").text("");
	$("#erroreCategoria").text("");
	$("#erroreNumLezioni").text("");
	$("#erroreGiorno").text("");
	$("#erroreOrario").text("");
	$("#erroreCosto").text("");
	
	// Variabili associate ai campi del modulo
	var nome, descrizione, indiceContenuti, categoria, numLezioni, giorno0, orario0, costo;
	nome = $("#nome").val();
	descrizione = $("#descrizione").val();
	indiceContenuti = $("#indice").val();
    categoria = $("#categoria").val();
	numLezioni = $("#lezioni").val();
	giorno0 = $("#giorno0").val();
	orario0 = $("#orario0").val();
	costo = $("#costo").val();
	
	var errori = 0;
	var tmp = errori;
	errori += erroreEmpty (nome, "#erroreNome", "Inserisci un nome.");
	if (errori == tmp) {
		errori += erroreParametroRangeValEsterno(nome.length, 10, 50, "#erroreNome", "La lunghezza del nome deve essere maggiore di 9 e minore di 51.");		
	}
	tmp = errori;
	errori += erroreEmpty (descrizione, "#erroreDescrizione", "Inserisci una descrizione.");
	if (errori == tmp) {
		errori += erroreParametroRangeValEsterno(descrizione.length, 101, 300, "#erroreDescrizione", "La lunghezza della descrizione deve essere maggiore di 100 e minore di 301.");	
	}
	tmp = errori;
	errori += erroreEmpty (indiceContenuti, "#erroreIndiceContenuti", "Inserisci un indice dei contenuti.");
	if (errori == tmp) {
		errori += erroreParametroRangeValEsterno(indiceContenuti.length, 100, 5000, "#erroreIndiceContenuti", "La lunghezza degli indici dei contenuti deve essere maggiore di 99 e minore di 5001.");	
	}
	errori += erroreEmpty (categoria, "#erroreCategoria", "Seleziona una categoria.");
	tmp = errori;
	errori += erroreEmpty (numLezioni, "#erroreNumLezioni", "Inserisci il numero delle lezioni.");
	if (errori == tmp) {
		errori += erroreParametroValMin(numLezioni, 1, "#erroreNumLezioni", "il numero delle lezioni deve essere maggiore di 0.");	
	}
	errori += erroreEmpty (giorno0, "#erroreGiorno", "Seleziona uno o piu' giorni.");
	errori += erroreEmpty (orario0, "#erroreOrario", "Seleziona uno o piu' orari.");
	tmp = errori
	errori += erroreEmpty (costo, "#erroreCosto", "Inserisci il costo.");
    if (errori == tmp) {
	 	errori += erroreParametroValMin(costo, 1, "#erroreCosto", "il costo deve essere maggiore di 0 euro.");	
	}
	
	if (errori >= 1) {
		$('html, body').animate({scrollTop: 0}, 100);
	}
	else {
		$("#creaPercorsoFormativoForm").submit();
	}
	
};









