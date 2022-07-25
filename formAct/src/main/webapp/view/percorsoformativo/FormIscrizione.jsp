<%@ 
	page language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList"
	import="java.util.Iterator"
	import="java.time.LocalDate"	
	import="model.dao.PercorsoFormativoDao"
	import="model.dao.FormatoreDao"
	import="model.dao.DisponibilitàDao"
	import="model.dao.StudenteDao"
	import="model.entity.PercorsoFormativoEntity"
	import="model.entity.FormatoreEntity"
	import="model.entity.DisponibilitaEntity"
	import="model.entity.StudenteEntity"
%>

<%
PercorsoFormativoEntity percorsoFormativo = (PercorsoFormativoEntity) session.getAttribute("percorsoFormativo");
	FormatoreEntity formatore = (FormatoreEntity) session.getAttribute("formatore");
	ArrayList<DisponibilitaEntity> aLDisponibilita = (ArrayList<DisponibilitaEntity>) session.getAttribute("aLDisponibilita");
	StudenteDao studenteDao = new StudenteDao();
	StudenteEntity studente = (StudenteEntity) studenteDao.doRetrieveByKey(2);
	LocalDate dataAttuale = LocalDate.now();
	int meseAttuale = dataAttuale.getMonthValue();
	int annoAttuale = dataAttuale.getYear();
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	    <script src="GetSelectOrario.js"></script>
	    <script src="ControlloDati.js"></script>
		<title>Insert title here</title>
	</head>
	
	<body>
		<input type="hidden" id="idPercorsoFormativo" value="<%=percorsoFormativo.getId()%>">
		<h1>Iscrizione al percorso formativo</h1>
		<p>Nome percorso formativo: <%=percorsoFormativo.getNome()%></p>
		<p>Formatore: <%=formatore.getName()%> <%=formatore.getSurname()%></p>
		
		<label>
    	        Giorno:<br>
    	        <select name="giorno" id="giorno">
    	        	<option value="">Seleziona un giorno</option>
<%
for (DisponibilitaEntity disponibilita : aLDisponibilita) {
%>
						<option value="<%= disponibilita.getGiornoSettimana()%>"><%= disponibilita.getGiornoSettimana() %></option>
<%
					}
%> 	        
    	        </select>
    	    </label>
    	    
    	    <br><br>
    	    
    	    <label id="labelOrario">
    	    	Orario:<br>
    	    </label>
    	    
    	    <br><br>
    	    
    	    <label>
    	    	Costo: <input type="number" id="costo" name="costoMin" value="<%= percorsoFormativo.getCosto() %>" disabled> €
    	    </label>
    	    
    	    <br><br>
    	    
    	    <label>
    	    	Email: <input type="email" id="email" name="email" value="<%= studente.getEmail() %>" disabled>
    	    </label>
    	    <br><br>
    	    Metodo di pagamento<br>
    	    <br><br>
    	    
    	    Dati carta (Visa, MasterCard):
    	    <br><br>
    	    <label>
    	    	Numero carta: <input type="text" id="numeroCarta" name="numeroCarta">
    	    </label>
    	    <br><br>
    	    <label>
    	    	CVV: <input type="text" id="cvv" name="cvv">
    	    </label>
    	    
    	    <br><br>
    	    
    	    <label>
    	    	Scadenza (MM/AAAA): 
    	    	<input type="number" name="meseScadenza" id="meseScadenza" min="1" max="12" step="1">
    	    	<input type="number" name="annoScadenza" id="annoScadenza" min="<%= annoAttuale %>" max="9999" step="1">
    	    </label>
    	    
    	    <br><br>
    	    
    	    <button id="controlloDati" name="controlloDati">Iscriviti</button>
	</body>
</html>













