<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="model.* ,  java.sql.Date  , java.util.* "%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Testing JSP</title>
</head>
<body>
<%
		UserBean user = new UserBean();
			
		user.setId(2);
		user.setEmail("fabio@peppo.it");
		user.setPassword("123sdas");
		user.setName("fabio2");
		user.setSurname("peppo");
		user.setGender("M");
		user.setBirthDate(new Date(1995,02,10));
		user.setCountry("Italia"); 
		
		UserDS model = new UserDS();
		
		ArrayList<UserBean> users = new ArrayList<UserBean>();
		users = model.doRetrieveAll();
	    
		


%>
	<h3> REgistrazione avvenuta 
			
			<%
				for ( int i =0; i< users.size() ; i++) {
					System.out.println(users.get(i).toString());
				}
			%>
	
	</h3>

</body>
</html>