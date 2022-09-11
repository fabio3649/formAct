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
    
    <title>Modifica interessi</title>
    
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
  	    Modifica interessi
  	  </div>
  	  
  	  <br>
  	  
  	  <div class="contenuto">
  	    <form action="${pageContext.request.contextPath}/AutenticazioneServlet/ModificaInteressiService" method="post">
<% 
          if (ruolo == null || !ruolo.equalsIgnoreCase("Studente")) {
%>
            <div class="row">
              <div class="col">
                Non sei uno studente.
              </div>
            </div>
<%        	  
          }
          else {
            int idStudente = (int)request.getSession().getAttribute("currentId");
            InteresseDao iDao = new InteresseDao();
            ArrayList<InteresseEntity> allInteressi = iDao.doRetrieveAll();
            InteresseStudenteDao isDao = new InteresseStudenteDao();
            ArrayList<String> interessiStudente = (ArrayList<String>) isDao.doRetrieveInteressiStudente(idStudente);
            if (allInteressi != null && allInteressi.size() > 0) {
%>
              <div class="row">
<% 
                int cont = 0;
                for (InteresseEntity interesse : allInteressi) {
                  if (cont == 2) {
            	    cont = 0;
%>
                    </div>
                    <div class="row">
<%
                  }
                  String nomeInteresse = interesse.getNome();
                  
        	      int idInteresse = interesse.getIdInteresse();
        	      int posParentesiTonda = nomeInteresse.indexOf("(");
        	      if (posParentesiTonda >= 0) {
        	        nomeInteresse = nomeInteresse.substring(0,posParentesiTonda);
        	      }
%>
                  <div class="col">
                    <div class="interesse">
			          <input type="checkbox" name="<%= interesse.getNome() %>" value="<%= idInteresse %>" 
<%
                        if (interessiStudente.contains(interesse.getNome())) {
%>			      
			              checked
<%
                        }
%>			  
	                  >
			          <label for="<%= idInteresse %>"><%= nomeInteresse %></label>
		            </div>
                  </div>
<%
                  cont++;
        	    }
%>
  	          </div><% 
            }
            else {
%>
              <div class="row">
                <div class="col">
                  Per il momento non è disponibile nessun interesse.
                </div>
              </div>
<%
            }
            
%>    
			<%
            PreferenzaStudenteDao psDao = new PreferenzaStudenteDao();
            ArrayList<PreferenzaStudenteEntity> preferenze = psDao.doRetrieveByStudent(idStudente);
%>

            <div class="row">
              <div class="col">
               <input type="checkbox" name="lunedì" value="lunedì" <% if(psDao.isContent(idStudente, "lunedì")) { %> checked 	<% } %> > Lunedì					
               			 
              </div>
              <div class="col">
                <input type="checkbox" name="martedì" value="martedì" <% if(psDao.isContent(idStudente, "martedì")) { %> checked 	<% } %> > Martedì
              </div>
              <div class="col">
               <input type="checkbox" name="mercoledì" value="mercoledì" <% if(psDao.isContent(idStudente, "mercoledì")) { %> checked 	<% } %> > Mercoledì
              </div>
              <div class="col">
                <input type="checkbox" name="giovedì" value="giovedì" <% if(psDao.isContent(idStudente, "giovedì")) { %> checked 	<% } %> > giovedì
              </div>
              <div class="col">
                <input type="checkbox" name="venerdì" value="venerdì" <% if(psDao.isContent(idStudente, "venerdì")) { %> checked 	<% } %> > venerdì

              </div>
            </div>
            
            <br>
            
            <div style="text-align:center;">
              <button type="submit" class="btn" style="align:right;">Salva modifiche</button>
            </div>
          </form>
            
<%
        }
%>
      </div>
    </div>
    
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
  
</html>









