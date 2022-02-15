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
PercorsoFormativoEntity corso = new PercorsoFormativoEntity();
	
		corso.setId(1);
		corso.setId_formatore(1);
		corso.setNome("matematica");
		corso.setDescrizione("matamtica con domenico uni, yeaaaaaaah");
		corso.setCategoria(1);
		corso.setIndice_contenuti("1 -- analisi 2 ---- bhooo ");
		corso.setNum_lezioni(50);
		corso.setCosto(20);
		
		PercorsoFormativoDao model = new PercorsoFormativoDao();
		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
		corsi = model.doRetrieveAll();
%>
	<h3> REgistrazione avvenuta 
			
			<%
				for ( int i =0; i< corsi.size() ; i++) {
					System.out.println(corsi.get(i).toString());
				}
			%>
	
	</h3>

</body>
</html>