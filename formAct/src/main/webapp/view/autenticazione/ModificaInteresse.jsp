<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.entity.*,  model.dao.* , java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%
		InteresseDao dao = new InteresseDao();
		ArrayList<InteresseEntity> allInteressi = dao.doRetrieveAll();
		InteresseStudenteDao dao1= new InteresseStudenteDao();
		InteresseStudenteEntity interesseStudente= new InteresseStudenteEntity();
		int idStudente=(int)request.getSession().getAttribute("currentId");
	%>



	<p>Seleziona i tuoi interessi: </p>
	<form action="${pageContext.request.contextPath}/ModificaInteressiServlet" method="post">
	
	<%
		for(InteresseEntity interesse : allInteressi ){	
		
			interesseStudente.setInteresse(interesse.getIdInteresse());
			interesseStudente.setStudente(interesse.getIdInteresse());
		 if(dao1.isContent(interesse.getIdInteresse(),idStudente) == true){
			 %>
			 
			 <div>
				<input type="checkbox" name="<%=interesse.getNome() %>" value="<%=interesse.getIdInteresse() %>" checked></input>
				<label for="Matematica"><%=interesse.getNome() %></label>
			</div>
			 
			 
			 <%
			 }else{
		%>
			<div>
				<input type="checkbox" name="<%=interesse.getNome() %>" value="<%=interesse.getIdInteresse() %>"></input>
				<label for="Matematica"><%=interesse.getNome() %></label>
			</div>
	<%
			}
		}
	%>
		
		<input type="submit" value="Modifica Interessi">
	</form> 
	
</body>
</html>