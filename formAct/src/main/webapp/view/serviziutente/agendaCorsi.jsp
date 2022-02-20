<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="model.entity.*,  model.dao.* , java.util.*   , serviziutente.service.* "%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FormAct - I tuoi corsi</title>
</head>
<body>
	<%
	PercorsoFormativoDao dao = new PercorsoFormativoDao();	
	
	IscrizioneDao daoIsc = new IscrizioneDao();
	
	ArrayList<IscrizioneEntity> iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveByStudent(1);
	
	//ArrayList<PercorsoFormativoEntity> giorno = dao.doRetrieveByDisponibilità("lunedì");
	
	%>
				
				<div>
						<h3>I tuoi percorsi formativi</h3>
						<table style="width:100%">
						
						
						<th> Lunedì  </th>
						<th> Martedì </th>
						<th> Mercoledì  </th>
						<th> Giovedì  </th>
						<th> Venerdì  </th>
						</tr>
						           <%
						           AgendaCorsi ag = new AgendaCorsi();
						           ArrayList<PercorsoFormativoEntity> lunedi = ag.getLunedi();
								for(int i =0; i< lunedi.size() ; i++){
								
									%>
							                  <tr>
											    
											    <td> <%=lunedi.get(i).getNome()%>  </td>
											    <td>  </td>
											  </tr>
											  
					
								                                     <% 	
								 										}
									                                  %>
									                                  
									                                  	
						</table>
		        </div>
		

</body>
</html>