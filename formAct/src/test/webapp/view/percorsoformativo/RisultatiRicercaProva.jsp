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
            <p>Nessun percorso formativo trovato!!</p>
<%
        }
%>
    </body>
</html>