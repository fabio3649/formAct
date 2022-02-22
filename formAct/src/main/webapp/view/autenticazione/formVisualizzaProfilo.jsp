<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.dao.*,model.entity.*,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PROFILO</title>
</head>
<body>
		<% if(request.getSession().getAttribute("role")!=null){
				if(request.getSession().getAttribute("role").equals("Formatore")){
					FormatoreDao dao= new FormatoreDao();
					int currentId= (int)request.getSession().getAttribute("currentId");
					FormatoreEntity formatore=(FormatoreEntity) dao.doRetrieveByKey(currentId);
					ValutazioneDao valutazioneDao= new ValutazioneDao();
					try{
						ArrayList<ValutazioneEntity> allValutazioni=valutazioneDao.doRetrieveByFormatore(currentId);
					
		%>
					<form>
					<fieldset>
						<legend>PROFILO</legend>
						
						<div>
								<label>Email : <%=formatore.getEmail()  %></label>
								
							</div>
							
							<!-- Password-Password , variabile="password" -->
							<div>
								<label>Password :  <%=formatore.getPassword() %></label>
								
							</div>
							
						</fieldset>
						
						
						<!-- fieldset dedicato ai dati personali -->
						<fieldset>
							<legend>Dati Personali</legend>
							
							<!-- Text-Nome , variabile = "name" -->
							<div>
								<label>Nome : <%=formatore.getName() %> </label>
								
							</div>
							
							<!-- Text-Cognome , variabile = "surname" -->
							<div>
								<label>Cognome : <%=formatore.getSurname() %></label>
								
							</div>
						
							<!-- RadioButtons-Sesso , variabile = "gender(maschio or femmina)" -->
							<div>
								<label>Sesso : <%=formatore.getGender() %></label>

							</div>
							
							<!-- Date-Data di nascita , variabile = "birthdate(aaaa-mm-gg)" -->
							<div>
								<label>Data di nascita : <%=formatore.getBirthDate() %></label>
								
							</div>
							
							<!-- Text-Paese di origine , variabile = "country" -->
							<div>
								<label>Paese di origine : <%=formatore.getCountry() %></label>
								
							</div> 
							
							<!-- Text-Codice Fiscale , variabile = "cf" -->
							<div>
								<label>Codice Fiscale : <%=formatore.getCodiceFiscale() %></label>
								
							</div> 
							
							<!-- Text-Conto Corrente , variabile = "numCC" -->
							<div>
								<label>Conto Corrente : <%=formatore.getContoCorrente() %></label>
								
							</div>
							
							<div>
								<%
								int counter=1;
								for(ValutazioneEntity a: allValutazioni){
										
									%>
								<label>Valutazione n.<%=counter %> </label>
								<p><%=a.getData() %></p>
								
								<p><%=a.getDescrizione() %></p>
								
								<p>N.Stelle: </p>
								<p><%=a.getStelle() %></p>
								<%
									counter++;
								}%>
							</div>
							
					</fieldset>
					</form>
					<%
					}catch(Exception e){
					}%>
					
					
		<%
		}else if(request.getSession().getAttribute("role").equals("Studente")){
				StudenteDao stud= new StudenteDao();
				StudenteEntity studente= new StudenteEntity();
		%>
		        <form>
				<fieldset>
						<legend>PROFILO</legend>
						
						
						<div>
							<label>Email : <%= studente.getEmail() %></label>
							
						</div>
						
						
						<div>
							<label>Password : <%= studente.getPassword() %></label>
							
						</div>
						
					</fieldset>
					
					
					
					<fieldset>
						<legend>Dati Personali</legend>
						
						
						<div>
							<label>Nome : <%= studente.getName() %></label>
							
						</div>
						
						
						<div>
							<label>Cognome : <%= studente.getSurname() %></label>
							
						</div>
					
						<!-- RadioButtons-Sesso , variabile = "gender(maschio or femmina)" -->
						<div>
							<label>Sesso : <%= studente.getGender() %></label>
							
						</div>
						
						
						<div>
							<label>Data di nascita : <%= studente.getBirthDate() %></label>
							
						</div>
						
						
						<div>
							<label>Paese di origine : <%= studente.getCountry() %></label>
							
						</div> 
							
					</fieldset>
		            </form>
					
		<%
		}%>
	<%
	}%>
</body>
</html>