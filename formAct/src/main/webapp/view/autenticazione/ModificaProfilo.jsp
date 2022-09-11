<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="model.utils.Utils"
    import="model.entity.*"
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
    
    <title>Modifica profilo</title>
    
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
  	    Modifica profilo
  	  </div>

  	  <div class="contenuto">
  	    <form id="modificaProfiloForm" action="${pageContext.request.contextPath}/AutenticazioneServlet/ModificaProfiloService" method="post">
<%
          FormatoreEntity formatore = null;
          StudenteEntity studente = null;
          
          if (ruolo == null || (!ruolo.equalsIgnoreCase("Studente") && !ruolo.equalsIgnoreCase("Formatore"))) {
%>
           Errore, non risulti uno studente e neanche un formatore.
<%          
          }
          else {
            String email = "";
            String nome = "";
            String cognome = "";
            String sesso = "";
            String dataNascita = "";
            String country = "";
            String codiceFiscale = "";
            String contoCorrente = "";
            
            if (ruolo.equalsIgnoreCase("Studente")) {
              studente = (StudenteEntity) request.getSession().getAttribute("utente");
              email = studente.getEmail();
              nome = studente.getName();
              cognome = studente.getSurname();
              sesso = studente.getGender();
              dataNascita = Utils.toStringDate(studente.getBirthDate());
              country = studente.getCountry();
            }
            else if (ruolo.equalsIgnoreCase("Formatore")) {
              formatore = (FormatoreEntity) request.getSession().getAttribute("utente");
              email = formatore.getEmail();
              nome = formatore.getName();
              cognome = formatore.getSurname();
              sesso = formatore.getGender();
              dataNascita = Utils.toStringDate(formatore.getBirthDate());
              country = formatore.getCountry();
              codiceFiscale = formatore.getCodiceFiscale();
              contoCorrente = formatore.getContoCorrente();
            }
%>        	
            <input type="hidden" class="paeseSelezionato" id="paeseSelezionato" name="paeseSelezionato" value="<%= country %>">
            <input type="hidden" class="ruolo" id="ruolo" name="ruolo" value="<%= ruolo %>">
            <span style="color: red;"><label for="email">Email:</label></span>
            <input type="text" id="email" class="email" name="email" value="<%= email %>" placeholder="<%= email %>">  
            <br>
            <!-- Errore email -->
            <p class="text-danger" id="erroreEmail"></p>
            <br> <br>  
            <span style="color: red;">Nome:</span> <%= nome %> 
            <br> <br>
            <span style="color: red;">Cognome:</span> <%= cognome %> 
            <br> <br>
            <span style="color: red;">Sesso:</span> <%= sesso %> 
            <br> <br>
            <span style="color: red;">Data di nascita:</span> <%= dataNascita %> 
            <br> <br>
            <span style="color: red;"> Paese di origine:</span> <%= country %>
            <br>
<%
            if (formatore != null) {
%>            
              <br> <br>
              <span style="color: red;">Codice fiscale:</span> <%= codiceFiscale %>
              <br> <br>               
              <span style="color: red;"><label for="cc">Conto corrente:</label></span> 
              <input type="text" id="cc" class="cc" name="cc" value="<%= contoCorrente %>" placeholder="<%= contoCorrente %>"> 
              <br>
              <!-- Errore conto corrente -->
              <p class="text-danger" id="erroreCC"></p>
<%
            }
%>          
            <br> <br>
            <input type="button" class="btn" id="modificaProfiloButton" onclick="modificaProfilo()" value="Modifica profilo">
<%
          }
%>      	  
        </form>
      </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
    
    
    
    
    <script src="/formAct/view/js/Paesi.js" type="text/javascript"></script>
    <script src="/formAct/view/js/validazioniForm/Autenticazione.js" type="text/javascript"></script>
  </body>
</html>









