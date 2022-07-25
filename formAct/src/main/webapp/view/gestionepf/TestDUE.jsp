<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% request.setAttribute("register","ciao");
	%>

	<form action="${pageContext.request.contextPath}/CheckRegisterUserServlet" >
	
	<input type="submit" >  CIAO  </input>
	
	</form>


</body>
</html>