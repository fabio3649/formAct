<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="model.dao.*"
    import="model.entity.*"
    import="java.util.ArrayList"
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
    
    <title>Errore</title>
    
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
  	  <div class="text-center mt-4 name" style="display: block;">
  	    Errore.
  	  </div>
  	  
  	  <br>
  	  
  	  <div class="contenuto">
<%
        boolean isErrore = false;
        
        /**** Sottosistema autenticazione ****/
        
        String errore = (String) request.getSession().getAttribute("erroreModificaPassword");
        // Errore modifica password
        if (errore != null && errore.equalsIgnoreCase("true")) {
          request.getSession().setAttribute("erroreModificaProfilo", "false");
%>
          Modifica password fallita.
<%
          isErrore = true;
        }
        // Errore modifica profilo
        if (!isErrore) {
          errore = (String) request.getSession().getAttribute("erroreModificaProfilo");
          if (errore != null && errore.equalsIgnoreCase("true")) {
            request.getSession().setAttribute("erroreModificaProfilo", "false");
%>
            Modifica profilo fallito.
<%
            isErrore = true;
          }
        }
        
        /**** Sottosistema registrazione ****/
        
        // Errore registrazione
        if (!isErrore) {
          errore = (String) request.getSession().getAttribute("erroreRegistrazione");
          if (errore != null && errore.equalsIgnoreCase("true")) {
            request.getSession().setAttribute("erroreRegistrazione", "false");
%>
            Registrazione fallita.
<%
            isErrore = true;
          }
        }
        
        /**** Sottosistema registrazione ****/
        
        // Errore eliminazione utente
        if (!isErrore) {
          errore = (String) request.getSession().getAttribute("erroreEliminazioneUtente");
          if (errore != null && errore.equalsIgnoreCase("true")) {
            request.getSession().setAttribute("erroreEliminazioneUtente", "false");
%>
            Eliminazione utente fallito.
<%
            isErrore = true;
          }
        }
        if (!isErrore) {
          errore = (String) request.getSession().getAttribute("errorePianoFormativo");
          if (errore != null && errore.equalsIgnoreCase("true")) {
            request.getSession().setAttribute("errorePianoFormativo", "false");
%>
            Non sei uno studente
<%
          }
        }
%>
      </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
  
</html>










