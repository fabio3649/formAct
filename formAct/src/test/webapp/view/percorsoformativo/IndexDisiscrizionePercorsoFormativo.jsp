<%@ 
    page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.dao.StudenteDao"
    import="model.entity.StudenteEntity"
%>

<%
	// Studente di prova:
	StudenteDao studenteDao = new StudenteDao();
	StudenteEntity studente = (StudenteEntity) studenteDao.doRetrieveByKey(1);	
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>
    
    <body>
        <form action="${pageContext.request.contextPath}/DisiscrizionePercorsoFormativoServlet" method="post">
    	    <input type="hidden" id="action" name="action" value="visualizzaIscrizioni">
    	    <input type="hidden" id="idStudente" name="idStudente" value="<%= studente.getId() %>">
    	        		
    		<input type="submit" id="VisualizzaIscrizioniButton" name="VisualizzaIscrizioniButton" value="Le tue Iscrizioni">
    	</form>
    </body>
</html>