<%@ 
    page language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Insert title here</title>
    </head>
    
    <body>
        <form action="${pageContext.request.contextPath}/VisualizzaPercorsiFormativiServlet" method="post">
    	    <input type="hidden" id="action" name="action" value="visualizzaPercorsiFormativi">
    	        		
    		<input type="submit" id="visualizzaButton" name="visualizzaButton" value="Visualizza percorsi formativi">
    	</form>
    </body>
</html>