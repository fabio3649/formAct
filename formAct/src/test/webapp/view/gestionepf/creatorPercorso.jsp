<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"      import="model.entity.*,  model.dao.* , java.util.* "    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="validation.js"> </script>
<title>FormAct - Creazione percorso formativo</title>
</head>
<body>
<%
ArrayList <CategoriaEntity> categorie = new ArrayList<CategoriaEntity>();
	CategoriaDao dao = new CategoriaDao();
	categorie = dao.doRetrieveAll();
	DisponibilitaDao daoDisp = new DisponibilitaDao();
	ArrayList<DisponibilitaEntity> disps = daoDisp.doRetrieveAll();
%>



	<form action="${pageContext.request.contextPath}/CreazionePercorsoServlet"  method="post"  id="formPercorso" >
		<label for="nome">Nome</label> <br> <br>
			<input type="text" id="nome" name="nome"  placeholder="inserisci nome corso">
		<br><br> <label for="descrizione">Descrizione</label> <br> <br>
			<textarea id="descrizione" rows="10" cols="10" name="descrizione"> Dettagli corso </textarea>
		<br><br> <label for="indice">Indice dei contenuti</label> <br> <br>
			<textarea id="indice" rows="50" cols="50" name="indice" > Indice dei contenuti... </textarea>
		<br><br> <label for="cars">Scegli ambito:</label>
			  <select  id="categoria" name="categoria">
			  <%
			  for( int i =0 ; i< categorie.size();i++) {
			     				 	CategoriaEntity c = categorie.get(i);
			  %>	
   				 <option value="<%=c.getIdCategoria()%>"> <%=c.getNome()%> </option>	 
			   
   				 
   			<%
	 			      				    			}
	 			      				    			%>
   				    </select> <br> <br> 
  			
		<br><br> <label for="lezioni">Numero lezioni</label> <br> <br>
			<input type="number" id="lezioni" name="lezioni" required maxlength="16" placeholder="inserisci numero di lezioni">
			
			<br><br> <label for="disp">Scegli una disponibilità:</label> 
			<div id="disponibilità">
			  <select  id="giorno" name="giorno">
			  
   				 <option value="lunedì"> Lunedì </option>	
   				 <option value="martedì"> Martedì </option>
   				 <option value="mercoledì"> Mercoledì </option>
   				 <option value="giovedì">Giovedì </option>
   				 <option value="venerdì"> Venerdì </option> 
			   
   				 
   			
   				    </select> <br> <br> 
   				    <select  id="orario" name="orario">
			  <%
			  // LocalTime considera 1 ora indietro;
			  %>
   				 <option value="10:00"> 09:00 </option>	
   				 <option value="11:00"> 10:00 </option>
   				 <option value="12:00"> 11:00 </option>
   				 <option value="16:00"> 15:00 </option>
   				 <option value="16:30"> 15:30 </option>
   				 <option value="17:00"> 16:00 </option>
   				 <option value="17:30"> 16:30 </option>
   				 <option value="19:00"> 18:00 </option>
   				
			   
   				 
   			
   				    </select>
   			</div> <input type="button" id="button1" value="aggiungi altri giorni" >
   			
   			<div id ="altroGiorno" style="display: none" >
   				 <select  id="giorno" name="giorno">
			  
   				 <option value="lunedì"> Lunedì </option>	
   				 <option value="martedì"> Martedì </option>
   				 <option value="mercoledì"> Mercoledì </option>
   				 <option value="giovedì">Giovedì </option>
   				 <option value="venerdì"> Venerdì </option> 
			   
   				 
   			
   				    </select> <br> <br> 
   				    <select  id="orario" name="orario">
			  
   				 <option value="10:00"> 09:00 </option>	
   				 <option value="11:00"> 10:00 </option>
   				 <option value="12:00"> 11:00 </option>
   				 <option value="16:00"> 15:00 </option>
   				 <option value="16:30"> 15:30 </option>
   				 <option value="17:00"> 16:00 </option>
   				 <option value="17:30"> 16:30 </option>
   				 <option value="19:00"> 18:00 </option>
   			
   				    </select>
   			
   			
   			</div>
   			
			   			<script >
			   					document.getElementById("button1").addEventListener("click", 
			   					function () {
			   					 		document.getElementById("altroGiorno").style.display='inline';
			   					}, false );
			   			
			   			
			   			</script>
			   				    
   				     <br> <br>  
		<br><br> <label for="costo">Costo</label> <br> <br>
			<input type="number" step="0.01" id="costo" name="costo" required placeholder="inserisci costo corso"> <br> <br>
		
	
	                     <%
				                     ArrayList<DisponibilitaEntity> disponibilità = new ArrayList<DisponibilitaEntity>();
				                     				 DisponibilitaEntity d = new DisponibilitaEntity();
				                     %>
     <input type="submit" value="Crea percorso formativo">
	</form>


</body>
</html>