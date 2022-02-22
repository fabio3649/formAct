<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%
			 if(request.getSession().getAttribute("updateError") != null && request.getSession().getAttribute("updateError").equals("true")){
				%>
				
				<h3> LA MODOFICA DELLA PASSWORD NON E' ANDATA A BUON FINE</h3>
				
				<% 
				request.getSession().removeAttribute("updateError");
			 }
	%>
	
	<form action="${pageContext.request.contextPath}/ModificaPasswordServlet" method="post">
		<fieldset>
			<legend>Modifica Password</legend>
			
			<div>
				<label for="password">Vecchia Password: </label>
				<input type="password" id="password" name="password" required></input>
			</div>
			
			<div>
				<label for="nuovaPassword">Nuova Password: </label>
				<input type="password" id="nuovaPassword" name="nuovaPassword" required></input>
			</div>
			
			<div>
				<label for="confermaPassword">Conferma Password: </label>
				<input type="password" id="confermaPassword" name="confermaPassword" required></input>
			</div>
			
			<div>
				<input type="submit" value="Conferma">
			</div> 
		</fieldset>
	</form>
</body>
</html>