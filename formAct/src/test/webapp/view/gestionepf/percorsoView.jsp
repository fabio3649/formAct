<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"    import="model.entity.*,  model.dao.* , java.util.* " %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Scheda percorso formativo</title>
</head>
<body>
<%

	int id = Integer.parseInt(request.getParameter("idPercorso"));
	PercorsoFormativoDao dao = new PercorsoFormativoDao();
	PercorsoFormativoEntity p = (PercorsoFormativoEntity) dao.doRetrieveByKey(id);
	FormatoreDao daoF = new FormatoreDao();
	FormatoreEntity formatore = (FormatoreEntity) daoF.doRetrieveByKey(p.getId_formatore());

%>
			<h6> Percorso formativo <%=p.getNome() %></h6>
			
			<h3> <%=formatore.getName() %>  <%=formatore.getSurname() %></h3> <br> <br>
			<h3> <%=p.getDescrizione() %> </h3> <br> <br>
			<h3> <%=p.getCategoria() %></h3>
		    <h3> <%=p.getIndice_contenuti() %></h3> <br> <br>
		    <h3> <%=p.getNum_lezioni() %></h3>
		    <h3> <%=p.getCosto() %></h3>
		    

</body>
</html>