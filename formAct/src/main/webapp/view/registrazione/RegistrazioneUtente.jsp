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
    
    <title>Registrazione formAct</title>
    
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
    <script src="/formAct/view/js/Paesi.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.0.min.js" type="text/javascript"></script>
  </head>
  
  <body onload="inizializzazioneErrori">
    <div class="header_section" style="background-color: #CFEDF0;">
      <%@include file="/view/fragments/Header.jsp"%>
    </div>
    
<%
    
    boolean isAlreadyRegistered = false;
    String errore = "";    
    
    if (logError != null) {
    	if (logError.equalsIgnoreCase("false")) {
    		if (ruolo != null) {
    			if (ruolo.equalsIgnoreCase("studente") || ruolo.equalsIgnoreCase("formatore") || ruolo.equalsIgnoreCase("amministratore")) {
    				// loggato come studente, formatore oppure amministratore
    				isAlreadyRegistered = true;
    			}
    			else {
    				// non loggato come studente, formatore oppure amministratore
    				errore = "Errore. Contattare il numero 0811234567 oppure scrivici una mail all'indirizzo esempio@esempio.it";
    			}
    		}
    	}
    }
%>
    <div class="wrapperTable">
      <div class="text-center mt-4 name" style="display: block;">
<%
        if (!errore.equals("")) {
%>
          <%= errore.toString() %>
<%
        }
        else if (isAlreadyRegistered) {  
%>
          Ti sei già iscritto al nostro sito web.
<%
        }
        else if (!isAlreadyRegistered) {
%>
          Iscrizione formAct <br> <br>
<%
		}
%>
      </div>
      
<%
      Integer tipoIscrizione = Integer.parseInt(request.getParameter("tipoIscrizione"));
      if (tipoIscrizione == null || (tipoIscrizione != 1 && tipoIscrizione != 2)) {
%>      
        <div class="text-center mt-4 name" style="display: block;">
            Seleziona in che modo desideri iscriveti alla piattaforma <br> <br>
        </div>
        
		<div style="text-align: center;">
          <a href="/formAct/view/registrazione/RegistrazioneUtente.jsp?tipoIscrizione=1" style="width:20%; display: inline-block; margin:0 auto; border: 3px solid black; border-radius: 10px; background-color: black;">
            <img src="/formAct/view/immagini/studente.png" style="width:50%;">
            <font class="daily_text" style="text-align: center;">Iscriviti come studente</font>
          </a>
          <a href="/formAct/view/registrazione/RegistrazioneUtente.jsp?tipoIscrizione=2" style="width:20%; display: inline-block; margin:0 auto; border: 3px solid black; border-radius: 10px; background-color: black;">
            <img src="/formAct/view/immagini/formatore.png" style="width:50%;">
            <font class="daily_text" style="text-align: center;">Iscriviti come formatore</font>
          </a>
		</div>
<%
      }
      // Registrazione studente o formatore
      else if(tipoIscrizione == 1 || tipoIscrizione == 2) {
        if (tipoIscrizione == 1) {
%>
        	<% request.getSession().setAttribute("register", "studente"); %>
<%
        }
        else {
%>
          <% request.getSession().setAttribute("register", "formatore"); %>
<%        
        }
        // Sia per registrazione studente e formatore
%>
        <div class="datiPersonali">
          <form class="p-3 mt-3" id="registrazioneUtenteForm" action="${pageContext.request.contextPath}/RegisterServlet/RegisterService" method="post">
            <input type="hidden" class="paeseSelezionato" id="paeseSelezionato" name="paeseSelezionato" value="">            
            <input type="hidden" name="tipoIscrizione" class="tipoIscrizione" id="tipoIscrizione" value="<%= tipoIscrizione %>">
            <label for="email"><span style="color: red;">Email:</span></label> 
            <input type="email" id="email" class="email" name="email">
            <!-- Errore email -->
            <p class="text-danger" id="erroreEmail"></p>
            <br> <br>
            
            <label for="password"><span style="color: red;">Password:</span></label>
            <input type="password" id="password" class="password" name="password">
            <!-- Errore password -->
            <p class="text-danger" id="errorePassword"></p>
            
            <label for="confermaPassword"><span style="color: red;">Conferma password:</span></label>
            <input type="password" id="confermaPassword" class="confermaPassword" name="confermaPassword"> <br> <br>
            <!-- Errore confermaPassword -->
            <p class="text-danger" id="erroreConfermaPassword"></p>
            
            <label for="name"><span style="color: red;">Nome:</span></label>
            <input type="text" id="name" class="name" name="name">
            <!-- Errore nome -->
            <p class="text-danger" id="erroreName"></p>
            
            <label for="surname"><span style="color: red;">Cognome:</span></label>
            <input type="text" id="surname" class="surname" name="surname">
            <!-- Errore cognome -->
            <p class="text-danger" id="erroreSurname"></p>
            
            <span style="color: red;">Sesso:</span>
            <input type="radio" id="gender" class="gender" name="gender" value="m"> 
            <label for="maschio">Maschio</label> 
            <input type="radio" id="gender" class="gender" name="gender" value="f"> 
            <label for="femmina">Femmina</label>
            <br>
            <label for="birthdate"><span style="color: red;">Data di nascita:</span></label> 
            <input type="date" id="birthdate" class="birthdate" name="birthdate">
            <!-- Errore dataNascita -->
            <p class="text-danger" id="erroreBirthdate"></p>
            
            <label for="countryDiv"><span style="color: red;">Paese di origine:</span></label> 
            <div id="countryDiv"></div>
            <!-- Errore paeseOrigine -->
            <p class="text-danger" id="erroreCountry"></p>
<%
            // solo per registrazione formatore
            if(tipoIscrizione == 2) {
%>
              <label for="cf"><span style="color: red;">Codice fiscale:</span></label> 
              <input type="text" id="cf" class="cf" name="cf"> <br> <br>
              <!-- Errore codiceFiscale -->
              <p class="text-danger" id="erroreCF"></p>
              
              <label for="numCC"><span style="color: red;">Conto corrente:</span></label> 
              <input type="text" id="numCC" class="numCC" name="numCC"> <br> <br>
              <!-- Errore contoCorrente -->
              <p class="text-danger" id="erroreNumCC"></p>
<%
            }
%>
            <input type="button" class="btn" id="registrazioneButton" onclick="registrazioneUtente()" value="Registrati">
          </form>
        </div>
<%
      }
%>
    </div>
    
    <%@include file="/view/fragments/Footer.jsp"%>
    
    <script>
        function inizializzazioneErrori() {
            $("#erroreEmail").text("");
            $("#errorePassword").text("");
            $("#errore").text("");
        }
    </script>
    
    
    <script src="/formAct/view/js/validazioniForm/Registrazione.js"></script>
  </body>
</html>









