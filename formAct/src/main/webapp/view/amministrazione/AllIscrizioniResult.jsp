<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="java.util.*"
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
<%
        StudenteEntity studente = (StudenteEntity) request.getSession().getAttribute("studente");
%>
  	    Iscrizioni dello studente <%= studente.getName() + " " + studente.getSurname() %>:
  	  </div>
  	  
  	  <div class="contenuto">
<%
        ArrayList<IscrizioneEntity> iscrizioni = (ArrayList<IscrizioneEntity>) request.getSession().getAttribute("allIscrizioni");
        if(iscrizioni != null && iscrizioni.size() > 0) {
%>
<%
          for(IscrizioneEntity iscrizione : iscrizioni) {
%>

            <%= iscrizione %>
            <br>
<%
          }
        }
        else {
%>
          Nessuna iscrizione trovata
<% 
        }
%>
  	  </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
</html>









