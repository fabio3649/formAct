<%@ 
    page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<title>Insert title here</title>
	</head>
	
	<body>
	    <h1>Ricerche:</h1>
	    
	    <h2>Ricerca CON filtri:</h2>
	    
		<form action="${pageContext.request.contextPath}/RicercaPercorsoFormativoServlet" method="post">
		    <input type="hidden" id="action" name="action" value="ricercaPercorsoFormativoFiltri">
    		
    		<label>
    		    Argomento:<br>
    	        <input type="text" id="argomento" name="argomento" placeholder="esempio: Java"><br>
    		</label>
    	    
    	    Costo percorso formativo:<br>
    	    <label>
    	        Costo min: <input type="number" id="costoMin" class="rangeCosto" name="costoMin"><br>
    	    </label>
    	    <label>
    	        Costo max: <input type="number" id="costoMax" class="rangeCosto" name="costoMax"><br>
    	    </label>
    	    
    	    <label>
    	        Disponibilità:<br>
    	        <select name="disponibilita" id="disponibilita">
    	        	<option value="">Seleziona una diponibilità</option>
    	        	<option value="lunedi">Lunedì</option>
    	        	<option value="martedi">Martedì</option>
    	        	<option value="mercoledi">Mercoledì</option>
    	        	<option value="giovedi">Giovedì</option>
    	        	<option value="venerdi">Venerdì</option>
    	        	<option value="sabato">Sabato</option>
    	        	<option value="domentica">Domenica</option>
    	        </select>
    	    </label>
    	    <br>
    	    
    	    <input type="submit" id="ricercaButton" name="ricercaButton" value="Ricerca avanzata">
    	</form>
    	
    	<br> <br>
    	
    	<h2>Ricerca SENZA filtri:</h2>
    	
    	<form action="${pageContext.request.contextPath}/RicercaPercorsoFormativoServlet" method="post">
    	    <input type="hidden" id="action" name="action" value="ricercaPercorsoFormativo">
    	    
    	    <label>
    		    Argomento:<br>
    	        <input type="text" id="argomento" name="argomento" placeholder="esempio: Java"><br>
    		</label>
    		
    		<input type="submit" id="ricercaButton" name="ricercaButton" value="Ricerca">
    	</form>
	</body>
</html>