<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="model.dao.*"
    import="model.entity.*"
    import="model.utils.Utils"
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
  	  <div class="text-center mt-4 name" style="display: block;">
  	    Dati anagrafici:
  	  </div>
  	  
  	  <br>
  	  
  	  <div class="contenuto">
 <%
        int idUtente = Integer.parseInt(request.getParameter("idUtente"));
        String ruoloUtente = request.getParameter("ruoloUtente");
        String email = null, nome = null, cognome = null, sesso = null, dataNascita = null, paeseOrigine = null;
        String codiceFiscale = null, contoCorrente = null;
        if (ruoloUtente != null && ruoloUtente.equalsIgnoreCase("Studente")) {
          StudenteDao sDao = new StudenteDao();
          StudenteEntity studente = sDao.doRetrieveByKey(idUtente);
          email = studente.getEmail();
          nome = studente.getName();
          cognome = studente.getSurname();
          sesso = studente.getGender();
          dataNascita = Utils.toStringDate(studente.getBirthDate());
          paeseOrigine = studente.getCountry();
        }
        else if (ruoloUtente != null && ruoloUtente.equalsIgnoreCase("Formatore")) {
          FormatoreDao fDao = new FormatoreDao();
          FormatoreEntity formatore = null;
          formatore = fDao.doRetrieveByKey(idUtente);
          email = formatore.getEmail();
          nome = formatore.getName();
          cognome = formatore.getSurname();
          sesso = formatore.getGender();
          dataNascita = Utils.toStringDate(formatore.getBirthDate());
          paeseOrigine = formatore.getCountry();
          codiceFiscale = formatore.getCodiceFiscale();
          contoCorrente = formatore.getContoCorrente();
        }
 %>
        <span style="color: red;">Email: </span><%= email %>
        <br> <br>
        <span style="color: red;">Nome: </span><%= nome %>
        <br> <br>
        <span style="color: red;">Cognome: </span><%= cognome %>
        <br> <br>
        <span style="color: red;">Sesso: </span><%= sesso %>
        <br> <br>
        <span style="color: red;">Data di nascita: </span><%= dataNascita %>
        <br> <br>
        <span style="color: red;">Paese di origine: </span><%= paeseOrigine %>
<%
        if (codiceFiscale != null) {
%>
          <span style="color: red;">Codice fiscale: </span><%= codiceFiscale %>
          <br> <br>
<%
        }
        if (contoCorrente != null) {
%>
          <span style="color: red;">Conto corrente: </span><%= contoCorrente %>
          <br> <br>
<%
        }
%> 
      </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
  
</html>









