
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"   import="model.entity.*,  model.dao.* , java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	/* FormatoreDao daof= new FormatoreDao();
		ArrayList<FormatoreEntity> formatori = daof.doRetrieveAll();
	for(int i=0; i<formatori.size(); i++) {
		System.out.println(formatori.get(i));
	} */
	
	StudenteDao daos = new StudenteDao();
	ArrayList<StudenteEntity> studenti = daos.doRetrieveAll();
	for(int i=0; i<studenti.size(); i++) {
		System.out.println(studenti.get(i));
	}

%>

</body>
</html>