<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="model.entity.*,  model.dao.* ,  java.sql.Date  , java.util.* "%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Testing JSP</title>
</head>
<body>
<%
	
	
		
		
		CategoriaDao model = new CategoriaDao();
		
		
		ArrayList<CategoriaEntity> c = new ArrayList<CategoriaEntity>();
		c =  (ArrayList<CategoriaEntity>)  model.doRetrieveAll();
		%>
	<h3> REgistrazione avvenuta  </h3>
			
			<%
				for ( int i =0; i< c.size() ; i++) {
					
					%>
				<h3> <%	 System.out.println(c.get(i).toString());    %> </h3>
				<h3> <%= c.get(i).toString() %> </h3>
					
					<%
				}
			
			%>
			
	
			

</body>
</html>