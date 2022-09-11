/**
 * 
 */

function isEmpty (parametro) {
    if (parametro == "" || parametro == null || parametro == undefined) {
        return true;
    }
    return false;
};

function erroreEmpty (parametro, idErrore, messaggioErrore) {
	if (isEmpty(parametro)) {
		// errore
        $(idErrore).text(messaggioErrore);
        return 1;
	}
	return 0;
};

function erroreParametroRegex (parametro, regexParametro, idErrore, messaggioErrore) {

    if (!parametro.match(regexParametro)) {
        // errore
        $(idErrore).text(messaggioErrore);
        return 1;
    }
    return 0;

};

function erroreParametroValMin(valore, min, idErrore, messaggioErrore) {

    if (valore < min) {
        // errore
        $(idErrore).text(messaggioErrore);
        return 1;
    }
    return 0;

};

function erroreParametroValMax(valore, max, idErrore, messaggioErrore) {

    if (valore > max) {
        // errore
        $(idErrore).text(messaggioErrore);
        return 1;
    }
    return 0;

};



function erroreParametroStringEquals(parametro1, parametro2, idErrore, messaggioErrore) {

    if (parametro1 !== parametro2) {
        // errore
        $(idErrore).text(messaggioErrore);
        return 1;
    }
    return 0;

};

function erroreParametroRangeValInterno(valore, limiteMin, limiteMax, idErrore, messaggioErrore) {

    if (valore >= limiteMin && valore <= limiteMax) {
        // errore
        $(idErrore).text(messaggioErrore);
        return 1;
    }
    return 0;

};

function erroreParametroRangeValEsterno(valore, limiteMin, limiteMax, idErrore, messaggioErrore) {

    if (valore < limiteMin || valore > limiteMax) {
        // errore
        $(idErrore).text(messaggioErrore);
        return 1;
    }
    return 0;

};









