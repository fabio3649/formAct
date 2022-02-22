<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form action="action="${pageContext.request.contextPath}/ModificaProfiloServlet" method="post"">
			<fieldset>
				<legend>MODIFICA PROFILO</legend>
					<div>
						<a href="/formAct/view/autenticazione/ModificaPassword.jsp"><button type="button">Modifica Password</button></a>
					</div>
					
					<div>
					<a href="/formAct/view/autenticazione/ModificaInteressi.jsp"><button type="button">Modifica Interessi</button></a>
					</div>
			</fieldset>
		</form>
</body>
</html>