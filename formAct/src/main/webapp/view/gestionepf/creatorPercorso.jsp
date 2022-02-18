<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"      import="model.entity.*,  model.dao.* , java.util.* "    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>FormAct - Creazione percorso formativo</title>
</head>
<body>
<%
	ArrayList <CategoriaEntity> categorie = new ArrayList<CategoriaEntity>();
	CategoriaDao dao = new CategoriaDao();
	categorie = dao.doRetrieveAll();

%>



	<form action="${pageContext.request.contextPath}/CreazionePercorsoServlet"  method="post"  id="formPercorso" >
		<label for="nome">Nome</label> <br> <br>
			<input type="text" id="nome" name="nome" required maxlength="16" placeholder="inserisci nome corso">
		<br><br> <label for="descrizione">Descrizione</label> <br> <br>
			<textarea id="descrizione" rows="10" cols="10" name="descrizione"> Dettagli corso </textarea>
		<br><br> <label for="indice">Indice dei contenuti</label> <br> <br>
			<textarea id="indice" rows="50" cols="50" name="indice" > Indice dei contenuti... </textarea>
		<br><br> <label for="cars">Choose a car:</label>
			  <select  id="categoria" name="categoria">
			  <%	
   			 for( int i =0 ; i< categorie.size();i++) {
   				 	CategoriaEntity c = categorie.get(i);
   				 %>	
   				 <option value="<%= c.getIdCategoria()%>"> <%= c.getNome()%> </option>	 
			   
   				 
   			<%	 
   			 }
   			 %>
   				    </select> <br> <br> 
  			
		<br><br> <label for="lezioni">Numero lezioni</label> <br> <br>
			<input type="number" id="lezioni" name="lezioni" required maxlength="16" placeholder="inserisci numero di lezioni">
		<br><br> <label for="costo">Costo</label> <br> <br>
			<input type="text" id="costo" name="costo" required maxlength="16" placeholder="inserisci costo corso"> <br> <br>
		
	
	
     <input type="submit" value="Crea percorso formativo">
	</form>


</body>
</html>