<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
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
      
      <br>
      
  	  <div class="text-center mt-4 name" style="display: block;">
  	    Formatori trovati:
  	  </div>
  	  
  	  <div class="contenuto">
<%
        ArrayList<FormatoreEntity> formatori = (ArrayList<FormatoreEntity>) request.getSession().getAttribute("allTrainer");
        if (formatori != null && formatori.size() > 0) {
          for (FormatoreEntity formatore : formatori) {
%>
		    <%= formatore.getName() + " " + formatore.getSurname() + ", votazione media: ..."%> 
            <form class="p-3 mt-3" action="/formAct/view/amministrazione/DatiAnagraficiUtente.jsp" method="post" style="margin: 10px 10px 10px 10px;">
              <input type="hidden" name="idUtente" value="<%= formatore.getId() %>">
              <input type="hidden" name="ruoloUtente" value="Formatore">
              <input type="submit" class="btn" value="Dati anagrafici">
            </form>
            <form action="/formAct/view/amministrazione/ValutazioniFormatore.jsp" style="margin: 10px 10px 10px 10px;">
              <input type="hidden" value="<%= formatore.getId() %>" name="idFormatore">
              <input type="submit" class="btn" name="valutazioni" value="Valutazioni ricevute"> 
            </form>
            <form action="/formAct/view/amministrazione/PercorsiFormatore.jsp" style="margin: 10px 10px 10px 10px;">
              <input type="hidden" value="<%= formatore.getId() %>" name="idFormatore">
              <input type="submit" class="btn" name="percorsi" value="Percorsi formativi"> 
            </form>
		    
		    <form action="${pageContext.request.contextPath}/AdminServlet/DeleteUserService">
		      <input type="hidden" id="namePage" name="namePage" value="AllTrainersResult.jsp">
		      <input type="hidden" id="idUser" name="idUser" value="<%= formatore.getId() %>">
		      <input type="hidden" id="role" name="role" value="fr">
		      <input type="submit" class="btn" value="Elimina Utente">
	        </form>
<%
          }
        }
        else {
%>
          Nessun formatore trovato
<%
        }
%>
  	  </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
</html>



















