<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.entity.*,  model.dao.* , java.util.*"%>
    
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	<link href="autenticazioneStyle.css" rel="stylesheet">
    <title>Modifica Interessi</title>
  </head>
  <body>
	
	<%@include file="/view/fragments/Header.jsp"%>
	
	<%
		InteresseDao dao = new InteresseDao();
		ArrayList<InteresseEntity> allInteressi = dao.doRetrieveAll();
		InteresseStudenteDao dao1= new InteresseStudenteDao();
		InteresseStudenteEntity interesseStudente= new InteresseStudenteEntity();
		int idStudente=(int)request.getSession().getAttribute("currentId");
		int cont=1;
	%>
	
	<div class="page">
		
		<div class="container p-4 display-6 text-center" >
			<p> Seleziona gli Interessi che preferisci</p>
		</div>
		
		<form action="${pageContext.request.contextPath}/ModificaInteressiServlet" method="post">
		<div class="container mt-5 mb-5">
			
		  <!-- for che cicla tante volte quante sono le righe -->	
		  <%for(int j=1; j<10; j++){ %>	
		  <div class="row">
		  	
		  	<!-- for che cicla tante volte quante sono le colonne -->
		  	<%for(int i=1; i<4; i++) {
		  		
		  		InteresseEntity interesse = allInteressi.get(cont);
		  	%>
		    <div class="col p-2" style="text-align:center;">
		     	
		     	<% 
		     	interesseStudente.setInteresse(interesse.getIdInteresse());
				interesseStudente.setStudente(interesse.getIdInteresse());
			 	if(dao1.isContent(interesse.getIdInteresse(),idStudente) == true){
			 	%>
				 <div >
					<input type="checkbox" name="<%=interesse.getNome() %>" value="<%=interesse.getIdInteresse() %>" checked></input>
					<label for="Matematica"><%=interesse.getNome()%></label>
				</div>
				 
				<%
				 }else{
				%>
				
				<div>
					<input type="checkbox" name="<%=interesse.getNome() %>" value="<%=interesse.getIdInteresse() %>"></input>
					<label for="Matematica"><%=interesse.getNome() %></label>
				</div>
				
			  <% } %> 
		    </div>
		    <% 
		    	cont++;
		  	} %>
		  </div>
		  <% } %>
		  
		  <!-- Submit button -->
		  <div  class="mt-5"style="text-align:center;">
		  	<button type="submit" class="btn btn-outline-info" style="align:right;">Salva Modifiche</button>	
		  </div>
		</div>
		</form>
		
	</div>
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  </body>
</html>
