<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
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
    
    <title>Titolo</title>
    
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
    
    <div class="wrapperTable">
<%
      if(ruolo != null && (ruolo.equalsIgnoreCase("Formatore") || ruolo.equalsIgnoreCase("Studente"))) {
%>
        <div class="immagineProfilo" style="width: 100%;">
          <img src="/formAct/view/immagini/avatar-login.png" alt="immagineProfilo" style="width:5%; display: block; margin:0 auto;">
        </div>
        
        <br>
        
  	    <div class="text-center mt-4 name" style="display: block;">
  	      Disiscrizione da FormAct
  	    </div>
  	    
  	    <div class="contenuto">
  	      <!-- Contenuto pagina -->
  	      <br><br>
  	      <form class="p-3 mt-3" action="${pageContext.request.contextPath}/AutenticazioneServlet/DisiscrizionePiattaformaService" method="post">
            <!-- 
            <label for="motivo">
            -->
<%
            String nomeUtente = (String) request.getSession().getAttribute("nomeUtente");
%>          
            Ciao <%= nomeUtente %>, Siamo davvero dispiaciuti che vuoi andartene da FormAct.
            <!-- 
            Se proprio vuoi continuare dicci almeno 
            <br>(se vuoi) la motivazione:<br><br>
            -->
            <!-- 
            </label>
            <textarea id="motivo" name="motivo" rows="4" cols="50">
            </textarea>
            -->          
            <br><br>
            <button class="btn mt-3">Disiscriviti da FormAct</button>
  	      </form>
  	    </div>
<%
      }
      else if (ruolo == null) {
%>
  	    <div class="text-center mt-4 name" style="display: block;">
  	      Errore, non sei loggato. <br>
  	      <a href="/formAct/view/autenticazione/Login.jsp" class="btn">Login</a>
  	    </div>
<%
      }
      else if (ruolo != null && ruolo.equalsIgnoreCase("Amministratore")) {
%>
  	    <div class="text-center mt-4 name" style="display: block;">
  	      Errore, sei loggato come amministratore, non puoi disiscriverti dalla piattaforma. <br>
  	    </div>
<%
      }
%>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
</html>
