charset="UTF-8"
/**
 * 
 */

var j = 0;

controlButton();

// funzione per controllare e disabilitare i bottoni
function controlButton() {
  var subButton = document.getElementById("sub");
  var addButton = document.getElementById("add");
  if(j <= 0) {
    subButton.className = "buttonBlocked";
    subButton.disabled = true; 
    addButton.className = "addButton";
    addButton.disabled = false; 
  }
  else if (j > 0 && j <= 9) {
    subButton.className = "subButton";
    subButton.disabled = false; 
    addButton.className = "addButton";
    addButton.disabled = false; 
  }
  else if (j > 9) {
    subButton.className = "subButton";
    subButton.disabled = false; 
    addButton.className = "buttonBlocked";
    addButton.disabled = true;
  }
};

// funzione per creare e ritornare una disponibilità
function getElDisp() {


  var elDisp = document.createElement("div");
  elDisp.classList.add("disp" + j);
  elDisp.style.display = "block";

  // Giorni
  elDisp.appendChild(getElSelect(getGiorni(), "giorno" + j, "giorno" + j));

  // Orari
  elDisp.appendChild(getElSelect(getOrari(), "orario" + j, "orario" + j));
  
  return elDisp;
};

// funzione per creare e ritornare un elemento select
function getElSelect(parametri, id, name) {

  let elSelect  = document.createElement("select");
  elSelect.id   = id
  elSelect.name = name;
  
  for(var i = 0; i < parametri.length; i++) {
    elSelect.appendChild(getElOption(parametri[i]));
  }
  //alert(elSelect.innerHTML);
  return elSelect;
  
};

// funzione per creare e ritornare un elemento option
function getElOption(parametro) {
  
  let elOption  = document.createElement("option");
  elOption.value = parametro.toLowerCase();
  elOption.innerText = parametro;
  return elOption;
  
};

// funzione che ritorna un array contenente i giorni della settimana
function getGiorni () {
	
	return ["Luned\u00EC", "Marted\u00EC", "Mercoled\u00EC", "Gioved\u00EC", "Venerd\u00EC"];
	
};

// funzione che ritorna un array contenente gli orari di una disponibilità
function getOrari() {
  
  let orari = Array();

  for (var i = 7; i <= 20; i++) {
    let orario = "";
    if (i < 10) {
      orario = "0" + i + ":00";
    }
    else {
      orario = i + ":00";
    }
    orari.push(orario);
  }

  return orari;

};

function addDisp() {
  
  controlButton();
  
  if (j <= 9) {
    let listDisp = document.getElementById("disponibilita");
    listDisp.appendChild(getElDisp());
    j++;
  }
  
  controlButton();
  
}

// funzione per eliminare una disponibilità
function subDisp() {
  
  controlButton();
  
  if (j > 0) {
    var listDisp = document.getElementById("disponibilita");
    listDisp.removeChild(listDisp.lastElementChild);
    j--;
  }
  
  controlButton();
  
}









