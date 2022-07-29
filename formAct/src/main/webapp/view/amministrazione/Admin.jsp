<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="admin.css" type="text/css"  >
<title>Admin Panel</title>
</head>
<body>
	
		<div class="vertical-menu">
			  <a href="index.jsp" class="active">Home</a>
			  <a href="Admin.jsp?id=1">Users view</a>
			  <a href="Admin.jsp?id=2">Percorsi Studenti</a>
			  <a href="Admin.jsp?id=3">Valutazioni percorsi/ formatori</a>
        </div>
	
	
	<div class="view">
	<%if(request.getParameter("id")!=null){ %>
		<% if(request.getParameter("id").equals("1")){
		%>	
				<%@ include file="/view/fragments/viewUsers.jsp"%>
			
		<%} %>
		<% if(request.getParameter("id").equals("2")){
		%>	
				<%@ include file="/view/fragments/corsiView.jsp" %>
			
		<%} %>
		<% if(request.getParameter("id").equals("3")){
		%>	
				<%@ include file="/view/fragments/valutazioni.jsp" %>
			
		<%} %>
	<%} %>
	</div>
	


</body>
</html>