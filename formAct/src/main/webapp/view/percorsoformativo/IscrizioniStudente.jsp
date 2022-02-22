<%@ 
    page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="java.util.Iterator"
    import="model.entity.PercorsoFormativoEntity"
%>

<%
	ArrayList<PercorsoFormativoEntity> percorsiFormativi = (ArrayList<PercorsoFormativoEntity>) session.getAttribute("percorsiFormativi");
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
 	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	    <script src="DisiscrizionePercorsoFormativo.js"></script>
		<title>Insert title here</title>
	</head>
	
	<body>
        <h1>Le tue iscrizioni</h1>
        
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
                		int i = 0;
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
            		    		<form action="${pageContext.request.contextPath}/DisiscrizionePercorsoFormativoServlet" method="post">
            		    		<input type="hidden" id="action" name="action" value="disiscrizionePercorsoFormativo">
            		    		<input type="hidden" id="idPercorsoFormativo" name="idPercorsoFormativo" value="<%= percorsoFormativo.getId() %>">
    							<button type="submit" name="disiscrivitiButton" value="disiscrivitiButton" class="disiscrivitiButton">Disiscriviti</button>
							</form>
            		    	
            		        </td>
            			</tr>
<%
						i++;
            		}
%>
            	</tbody>
            </table>
<%
        }
        else {
%>
            <p>Nessun percorso formativo iscritto!!</p>
<%
        }
%>
	</body>
</html>