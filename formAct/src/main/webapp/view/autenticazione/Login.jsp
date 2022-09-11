<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
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
    
    <title>Login formAct</title>
    
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
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.0.min.js" type="text/javascript"></script>
  </head>
  
  <body onload="inizializzazioneErrori">
    <!-------------------- Header -------------------->
    <div class="header_section" style="background-color: #CFEDF0;">
      <%@include file="/view/fragments/Header.jsp"%>
    </div>
    
    <div class="wrapper">
<%
	if (!ruolo.equals("Studente") || !ruolo.equals("Formatore")) {
%>
    
        <div class="logo">
          <img src="/formAct/view/immagini/logo.png" alt="Logo" style="width:60%;">
        </div>
        
        <div class="text-center mt-4 name">
          FormAct
        </div>
        
        <form class="p-3 mt-3" id="loginForm" action="${pageContext.request.contextPath}/AutenticazioneServlet/LoginService" method="post">
          <div class="form-field d-flex align-items-center">
            <span class="fa fa-user" aria-hidden="true"></span>
            <input type="text" name="email" id="email" placeholder="email" required="required">
          </div>
          <div class="form-field d-flex align-items-center">
            <span class="fa fa-key" aria-hidden="true"></span>
            <input type="password" name="password" id="password" placeholder="Password" required="required">
          </div>
          
          <p class="text-danger" id="erroreEmail"></p>
          <p class="text-danger" id="errorePassword"></p>
          
          <!-- Condizione di Erroe "logError", visualizzazione errore -->
<%
          if(     request.getSession().getAttribute("logError") != null 
               && request.getSession().getAttribute("logError").equals("true")) {
%>
            <div>

              <p class="text-danger" id="errore"><ins><strong>Attenzione! Email e/o Password errati</strong></ins></p>
            </div>
<%
          }
%>
          <input type="button" class="btn" id="login" onclick="accedi()" value="Login">
        </form>
          
        <div class="text-center fs-6"> 
          <a class="button" href="/formAct/view/registrazione/RegistrazioneUtente.jsp?tipoIscrizione=0">Iscriviti</a>
        </div>
<%
      }
      else {
%>
        <div class="text-center mt-4 name">
          Errore, risulti già loggato
        </div>
<%
      }
%>
    </div>
    <%@include file="/view/fragments/Footer.jsp"%>
    
    
    
    <script src="/formAct/view/js/validazioniForm/Autenticazione.js" type="text/javascript"></script>
    
    
  </body>
</html>









