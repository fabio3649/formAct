<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="model.entity.*,  model.dao.* ,  java.sql.Date  , java.util.* "%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

<%
			 if(request.getSession().getAttribute("logError") != null && request.getSession().getAttribute("logError").equals("true")){
				%>
				
				<h3> IL LOGIN NON E' ANDATA A BUON FINE</h3>
				
				<% 
				request.getSession().removeAttribute("logError");
			 }
			%>
	
	<h4> FormAct </h4>

	<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
		<fieldset>
			<legend>ACCEDI</legend>
			<div>
				<label for="email">Email:</label>
				<input type="text" id="email" name="email" required> 
			</div>
			
					
			<div>
				<label for="password")>Password:</label>
				<input type="password" id="password" name="password" required> 
			</div>
			
			<div>
				<input type="submit" value="Accedi">
			</div> 
		</fieldset>
	</form>
	
</body>
</html>

