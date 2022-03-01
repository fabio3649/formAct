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
						AgendaCorsi ag = new AgendaCorsi();
						ArrayList<Calendario> agenda = new ArrayList<Calendario>();
						
						
						
	%>
				
				<div>
						<h3>I tuoi percorsi formativi</h3>
						<table style="width:100%">
						
						
						<th> Lunedì  </th>
						<th> Martedì </th>
						<th> Mercoledì  </th>
						<th> Giovedì  </th>
						<th> Venerdì  </th>
						
						           <%
						          	    agenda = ag.getLunedi(1);
										for(int i =0; i< agenda.size() ; i++){
								   
									%>
							                  <tr>
											    
											    <td> <%=agenda.get(i).getNome()%>  <br> <%=agenda.get(i).getTime()%> </td>  </tr>
											                        <% 	
								 									}
																		//System.out.println(agenda);	
																		//System.out.println("\n");
									                                  %>
											    
											    
						                      	  
								  		                <%
								  		              agenda = ag.getMartedi(1);
								  		              for(int i =0; i< agenda.size() ; i++){
								  		                
								  		                %>                                        
								  		          
											    
											  		<td>  <%=agenda.get(i).getNome()%>  <br> <%=agenda.get(i).getTime()%>     </td>
											  						 <% 	
								 									}
								  		           						// System.out.println(agenda);						
									                                 %>
											  		
											  		
								  		                <%
								  		              agenda = ag.getMercoledi(1);
								  		              for(int i =0; i< agenda.size() ; i++){
								  		                
								  		                %>                                        
								  		          
											    
											  		<td>  <%=agenda.get(i).getNome()%>  <br> <%=agenda.get(i).getTime()%>     </td>
											  						 <% 	
								 									}
								  		           						 //System.out.println(agenda);						
									                                 %>
									                                 
									                                 <%
								  		              agenda = ag.getGiovedi(1);
								  		              for(int i =0; i< agenda.size() ; i++){
								  		                
								  		                %>                                        
								  		          
											    
											  		<td>  <%=agenda.get(i).getNome()%>  <br> <%=agenda.get(i).getTime()%>     </td>
											  						 <% 	
								 									}
								  		           						// System.out.println(agenda);						
									                                 %>
									                                 
									                                 
									                                 <%
								  		              agenda = ag.getVenerdi(1);
								  		              for(int i =0; i< agenda.size() ; i++){
								  		                
								  		                %>                                        
								  		          
											    
											  		<td>  <%=agenda.get(i).getNome()%>  <br> <%=agenda.get(i).getTime()%>     </td>
											  						 <% 	
								 									}
								  		           						// System.out.println(agenda);						
									                                %>
												                                              		
								  	             </tr>
											  
											  
					
								                                    
									                                  
									                                  
								  	                            
									                                  
									                                  	
						</table>
		        </div>
		

</body>
</html>