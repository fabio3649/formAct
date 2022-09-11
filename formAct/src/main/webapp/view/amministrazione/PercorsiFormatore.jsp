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
    
    <title>Percorsi formativi formatore</title>
    
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
  	    Percorsi formativi formatore
  	  </div>
  	  
  	  <br>
  	  
  	  <div class="contenuto">
<%
        if (ruolo == null || !ruolo.equalsIgnoreCase("Amministratore")) {
%>
          Non sei un amministratore
<%
        }
        else {
          int idFormatore = Integer.parseInt(request.getParameter("idFormatore"));
          PercorsoFormativoDao pfDao = new PercorsoFormativoDao();
          ArrayList<PercorsoFormativoEntity> percorsi = pfDao.doRetrieveByIdFormatore(idFormatore);
          if(percorsi != null && percorsi.size() > 0) {
%>
            <table border="1">
              <tr>
                <th>Nome</th>
                <th>Azioni</th>
              </tr>
<%
              for(PercorsoFormativoEntity percorso : percorsi) {
%>
                <tr>
                  <td><%= percorso.getNome() %></td>
                  <td>
           	        <form action="${pageContext.request.contextPath}/GestionePFServlet/VisualizzaPercorsoService" method="post">
           		      <input type="hidden" value="<%=percorso.getId()%>" name="idPercorso"> 
            	      <button class="btn mt-3">Dettagli</button> 
                    </form>
                  </td>
                </tr>
<%
              }
%>
            </table>
<%
          }
          else {
%>
            Nessun percorso formativo trovato.
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









