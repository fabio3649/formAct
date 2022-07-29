<%@ 
    page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="java.util.Iterator"
    import="model.dao.PercorsoFormativoDao"
    import="model.entity.PercorsoFormativoEntity"
%>

<%
	PercorsoFormativoDao percorsoFormativoDao = new PercorsoFormativoDao();
	ArrayList<PercorsoFormativoEntity> percorsiFormativi = (ArrayList<PercorsoFormativoEntity>) percorsoFormativoDao.doRetrieveAll();
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
        <h1>Risultati ricerca</h1>
        
<%
        if (percorsiFormativi != null && percorsiFormativi.size() > 0) {
%>
            <table border="1">
            	<thead>
                	<tr>
                		<th>ID</th>
                		<th>ID Formatore</th>
                		<th>Nome</th>
                		<th>Categoria</th>
                		<th>Descrizione</th>
                		<th>Indice contenuti</th>
                		<th>Numero lezioni</th>
                		<th>Costo</th>
                		<th>Azioni</th>
                	</tr>
            	</thead>
            	<tbody>
<%
                	for (PercorsoFormativoEntity percorsoFormativo : percorsiFormativi) {
%>
            			<tr>
            		    	<td><%= percorsoFormativo.getId() %></td>
            		    	<td><%= percorsoFormativo.getId_formatore() %></td>
            		    	<td><%= percorsoFormativo.getNome() %></td>
            		    	<td><%= percorsoFormativo.getCategoria() %></td>
            		    	<td><%= percorsoFormativo.getDescrizione() %></td>
            		    	<td><%= percorsoFormativo.getIndice_contenuti() %></td>
            		    	<td><%= percorsoFormativo.getNum_lezioni() %></td>
            		    	<td><%= percorsoFormativo.getCosto() + " €"%></td>
            		    	
            		    	<td>
            		    		<form action="${pageContext.request.contextPath}/IscrizionePercorsoFormativoServlet" method="post">
            		    			<input type="hidden" id="action" name="action" value="getFormIscrizione">
            		    			<input type="hidden" id="idPercorsoFormativo" name="idPercorsoFormativo" value="<%= percorsoFormativo.getId() %>">
    								<button type="submit" name="getFormIscrizionebutton" value="getFormIscrizionebutton" class="getFormIscrizionebutton">Iscriviti</button>
								</form>
            		    	
            		        </td>
            			</tr>
<%
            		}
%>
            	</tbody>
            </table>
<%
        }
        else {
%>
            <p>Nessun percorso formativo presente!!</p>
<%
        }
%>
	</body>
</html>