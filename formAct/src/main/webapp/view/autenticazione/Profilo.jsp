<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="model.entity.StudenteEntity"
    import="model.entity.FormatoreEntity"
%>

<!DOCTYPE html>

<html>
  <head>
     <!-- basic -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <!-- site metas -->
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>Profilo</title>
    
    <!-- bootstrap css -->
    <link rel="stylesheet" href="/formAct/view/css/bootstrap.min.css">
    <!-- style css -->
    <link rel="stylesheet" href="/formAct/view/css/unicoStile.css">
    <!-- Responsive-->
    <link rel="stylesheet" href="/formAct/view/css/responsive.css">
    <!-- favicon -->
    <link rel="icon" href="/formAct/view/immagini/favicon.png" type="image/gif" />
    <!-- Tweaks for older IEs-->
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">

  </head>
  
  <body>
    <!-------------------- Header -------------------->
    <div class="header_section" style="background-color: #CFEDF0;">
      <%@include file="/view/fragments/Header.jsp"%>
    </div>
    
    <div class="wrapper">
<%
      if (ruolo == null) {
%>
        <div class="text-center mt-4 name">
         Errore, non sei loggato. <br>
         <a href="/formAct/view/autenticazione/Login.jsp" class="btn">Login</a> <br> <br>
        </div>
<%
      }
      else if (!ruolo.equalsIgnoreCase("Studente") && !ruolo.equalsIgnoreCase("Formatore")) {
%>
        <div class="text-center mt-4 name">
          Errore, non sei loggato come studente oppure come formatore. <br>
        </div>
<%
      }
      else if (ruolo != null && (ruolo.equalsIgnoreCase("Studente") || ruolo.equalsIgnoreCase("Formatore"))) {
%>
  	    <div class="immagineProfilo" style="width: 100%;">
          <img src="/formAct/view/immagini/avatar-login.png" alt="immagineProfilo" style="width:20%; display: block; margin:0 auto;">
        </div>
        
        <div class="text-center mt-4 name">
<%
          String nomeUtente = (String) request.getSession().getAttribute("nomeUtente");
          String saluto = "Ciao " + nomeUtente;
%>
          <%= saluto %>
        </div>
        
        <div class="p-3 mt-3">
          <div class="row justify-content-md-center">
            <!-- Sia studente che formatore -->
            <div class="col lg">
   				<a href="/formAct/view/autenticazione/ModificaProfilo.jsp" class="btn">Modifica profilo</a> <br> <br>
              		
             	<a href="/formAct/view/autenticazione/ModificaPassword.jsp" class="btn">Modifica password</a> <br> <br>

                          
            </div><br>

            <br><br><br><br><br><br><br><br><br><br>
            <!-- Solo studente -->
<%
            if (ruolo.equalsIgnoreCase("Studente")) {
%>
			<div class="col lg">
				<a href="${pageContext.request.contextPath}/ServiziUtenteServlet/GetAgendaService" class="btn">Le tua agenda corsi</a> <br> <br>
				<a href="/formAct/view/autenticazione/ModificaInteressi.jsp" class="btn">Modifica interessi</a> <br> <br>
              	<a href="/formAct/view/autenticazione/DisiscrizionePiattaforma.jsp" class="btn">Disiscriviti da FormAct</a> <br> <br> 	
             	         
            </div><br>
<%
            }
%>
                        
            <!-- Solo formatore -->
<%
            if (ruolo.equalsIgnoreCase("formatore")) {
%>
			<div class="col lg">
              	<a href="/formAct/view/gestionepf/CreaPercorsoFormativo.jsp" class="btn">Crea un percorso formativo</a> <br> <br> 
              	<a href="${pageContext.request.contextPath}/GestionePFServlet/PercorsiFormatoreService" class="btn">Visualizza percorsi</a> <br> <br> 
              	<a href="/formAct/view/autenticazione/DisiscrizionePiattaforma.jsp" class="btn">Disiscriviti da FormAct</a> <br> <br>	
             	         
            </div><br>
<%
            }
%>
            

          </div>
        </div>
<%
      }
%>
    </div>
    
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
</html>









