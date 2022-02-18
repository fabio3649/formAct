<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"    import="model.entity.*,  model.dao.* , java.util.* " %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Testing view gestione pf</title>
</head>
<body>


<%
		FormatoreDao daoF = new FormatoreDao();
		FormatoreEntity formatore = (FormatoreEntity) daoF.doRetrieveByKey(1);
		PercorsoFormativoDao dao = new PercorsoFormativoDao();	
		ArrayList<PercorsoFormativoEntity> percorsi = dao.doRetrieveAllByFormatore(formatore.getName(), formatore.getSurname());
		CategoriaDao daoCat = new CategoriaDao();
        ArrayList<PercorsoFormativoEntity>  params = dao.doRetrieveIscrizioniStudente(1);
		
%>
		
		
		<%
								for(int i =0; i< params.size() ; i++){
										%>			
									
								<h3>	<%=params.get(i).toString()%>  </h3>
	<%
								}
	%>
		

         <div>
			<a href="percorsoView.jsp?idPercorso=1"> Percorsi formativi creati </a>
    	</div>
				
				<div>
						<h3>I tuoi percorsi formativi</h3>
						<table style="width:100%">
						<th> Nome  </th>
						<th> Ambito  </th>
						<th> Descrizione  </th>
						</tr>
						           <%
								for(int i =0; i< percorsi.size() ; i++){
									 CategoriaEntity c = (CategoriaEntity) daoCat.doRetrieveByKey(percorsi.get(i).getCategoria());
									%>
							                  <tr>
											    <td> <a href="percorsoView.jsp?idPercorso=<%=percorsi.get(i).getId()%>" > <%=percorsi.get(i).getNome()%>  </a> </td>
											    <td> <%=c.getNome() %> </td>
											    <td> <%=percorsi.get(i).getDescrizione()%> </td>
											  </tr>
											  
					
								                                     <% 	
								 										}
									                                  %>
						</table>
		        </div>
		
		
		<h3> Scegli quale percorso eliminare :</h3>  <br> <br>
		<form action="${pageContext.request.contextPath}/EliminaPercorsoServlet" method="get" >
		<select  id="percorsi" name="percorsi">
			<%
				for(int i =0; i< percorsi.size() ; i++){
			%>
					
					  	
		   				 <option value="<%=percorsi.get(i).getId()  %>">  <%=percorsi.get(i).getNome()  %> </option>	 
					   
		   				 
		   			
		   			                              <%	
		   			
		   			                                    }
		
		                                           %> 
		   
		</select> <br> <br> 
		<input type="submit" value="elimina" >
		</form>

</body>
</html>