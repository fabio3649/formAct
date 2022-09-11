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
    
    <title>Modifica password</title>
    
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
  
  <body>
    <!-------------------- Header -------------------->
    <div class="header_section" style="background-color: #CFEDF0;">
      <%@include file="/view/fragments/Header.jsp"%>
    </div>
    
    <div class="wrapperTable">
  	  <div class="text-center mt-4 name" style="display: block;">
  	    Modifica password:
  	  </div>
  	  
  	  <div class="contenuto">
  	    <form id="modificaPasswordForm" action="${pageContext.request.contextPath}/AutenticazioneServlet/ModificaPasswordService" method="post">
  	      <label for="passwordAttuale"><span style="color: red;">Password attuale:</span></label>
          <input type="password" id="passwordAttuale" class="passwordAttuale" name="passwordAttuale"> <br> <br>
      	  <!-- Errore password attuale -->
          <p class="text-danger" id="errorePasswordAttuale"></p>
      	  
          <label for="nuovaPassword"><span style="color: red;">Nuova password:</span></label>
          <input type="password" id="nuovaPassword" class="nuovaPassword" name="nuovaPassword"> <br> <br>
          <!-- Errore nuova password -->
          <p class="text-danger" id="erroreNuovaPassword"></p>
      	  
      	  <label for="confermaPassword"><span style="color: red;">Conferma nuova password:</span></label>
          <input type="password" id="confermaPassword" class="confermaPassword" name="confermaPassword"> <br> <br>  
          <!-- Errore conferma password -->
          <p class="text-danger" id="erroreConfermaPassword"></p>
          
      	  <input type="button" class="btn" onclick="modificaPassword()" value="Conferma">
      	</form>
  	  </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
    
    
    
    
    <script src="/formAct/view/js/validazioniForm/Autenticazione.js" type="text/javascript"></script>
  </body>
</html>









