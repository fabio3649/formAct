<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="java.util.Iterator"
    import="model.dao.FormatoreDao"
    import="model.entity.PercorsoFormativoEntity"
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
    
    <title>Percorsi formativi</title>
    
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
	  ArrayList<PercorsoFormativoEntity> percorsi = (ArrayList<PercorsoFormativoEntity>) session.getAttribute("percorsiFormativi");
	  if(percorsi != null && percorsi.size() > 0) {
%>
      
  	    <div class="text-center mt-4 name" style="display: block;">
  	      Percorsi formativi trovati:
  	    </div> <br> <br>
  	  
  	    <div class="contenuto">
<%
          FormatoreDao fDao = new FormatoreDao();
          for(PercorsoFormativoEntity percorso : percorsi) {
        	  FormatoreEntity formatore = fDao.doRetrieveByKey(percorso.getId_formatore());
            
%>
            <%= percorso.getNome() %> <br> Formatore: <%= formatore.getName() %> <%= formatore.getSurname() %> 
           	<form action="${pageContext.request.contextPath}/GestionePFServlet/VisualizzaPercorsoService" method="post">
           		<input type="hidden" value="<%=percorso.getId()%>" name="idPercorso"> 
            	<button class="btn mt-3">Dettagli</button> 
            </form>
            <% if(request.getSession().getAttribute("validation")!=null && ruolo.equals("Studente")) {
            	%>
            
            <form action="/formAct/view/percorsoformativo/Iscrizione.jsp" method="post">
            	<input type="hidden" value="<%=percorso.getId()%>" name="idPercorso"> 
            	<button class="btn mt-3">Iscriviti</button>
            
            </form>
            <% } %>
<%	
          }
%>
  	    </div>
<%
       
	  }
	  else {
%>
 	    <div class="text-center mt-4 name" style="display: block;">
  	      Nessun percorso formativo trovato
  	    </div> <br> <br>
<%
	  }
%>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
</html>









