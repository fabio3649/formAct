$(document).ready(function() {
	
	$(".disiscrivitiButton").click(function() {
		var idPercorsoFormativo = $(this).prev().val();
		var domanda = confirm("Vuoi davvero disiscriverti?");
		if (domanda == true) {
  			var action = "disiscrizionePercorsoFormativo";
  			
  			$.post("http://localhost:8080/formAct/DisiscrizionePercorsoFormativoServlet",
			{
				action: action,
				idPercorsoFormativo: idPercorsoFormativo
			},
			function() {
				alert("Disiscrizione percorso formativo effettuata");
				window.location.href = '/formAct/view/percorsoformativo/IscrizioniStudente.jsp';
			})
			.fail(function() {
				alert("Disiscrizione percorso formativo Fallita");
			});
			
		}
		else {
	  		alert("Operazione annullata");
		}
	});
});