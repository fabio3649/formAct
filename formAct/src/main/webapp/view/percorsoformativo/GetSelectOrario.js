$(document).ready(function() {
	$("#giorno").change(function() {
		var giorno = $("#giorno option:selected");
		giorno = giorno.text();
		var idPercorsoFormativo = $("#idPercorsoFormativo").val();
		//alert(idPercorsoFormativo);
		//alert (giorno);
		
		$.post("http://localhost:8080/formAct/IscrizionePercorsoFormativoServlet",
		{
			action: "ottieniOrariGiorno",
			giorno: giorno,
			idPercorsoFormativo: idPercorsoFormativo
		},
		function(orariJSON) {
    		let orari = JSON.parse(orariJSON);
    		var elementoSelect = "";
    		
    		if(orari.length <= 0) {
				$("#labelOrario").append( "<p>Orari non presenti</p>" );
			}
			else {
				elementoSelect += "<select id='orario'>";
				var elementoOption = "<option value=''>Seleziona un orario</option>";
				elementoSelect += elementoOption;
	    		for(let i = 0; i < orari.length; i+=2) {
					elementoOption = "<option value='"+orari[i]+"'>"+orari[i+1]+"</option>";
					elementoSelect += elementoOption;
				}
				elementoSelect += "</select>";
			}
			
				
			$("#labelOrario").append(elementoSelect);
    		//const map1 = new Map();
			//map1.set('a', 1);
			//map1.set('b', 2);
			//map1.set('c', 3);
    		
    		
    		
    		
    		//orari = JSON.parse(orariJSON);
    		//alert(orari)
    		//for (let i = 0; i < orari.length; i++) {
			//	alert (orari[i]);
			//}
    	})
    	.fail(function() {
    		alert("Fallimento");
    	});

	});
});