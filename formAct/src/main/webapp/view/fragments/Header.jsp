<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>

<%
	String ruolo ="";
	if(request.getSession().getAttribute("role")!=null) {
    	 ruolo = (String) request.getSession().getAttribute("role");
	}
    String logError = (String) request.getSession().getAttribute("logError");
%>

<!DOCTYPE html>

<html>
  <head>
    <meta charset="UTF-8">
    <title>Header</title>
  </head>
  
  <body>
    
    <nav class="navbar navbar-expand-lg navbar-light ">
      
      <!-- Logo -->
      <div class="header_logo">
        <a href="/formAct/view/index/index.jsp">
          <img src="/formAct/view/immagini/logo.png" alt="Logo" width="40" height="40" >
        </a>
      </div>
      <div class="col-lg-1 col-sm-6">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      </div>
      
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="/formAct/view/index/index.jsp">Home</a>
          </li>
<%
          
          if (ruolo != null) {
            if (ruolo.equalsIgnoreCase("Amministratore")) {
%>
  
              <li class="nav-item">
                <a class="nav-link" href="/formAct/view/amministrazione/Admin.jsp">Gestione formAct</a>
              </li>
<%
            }
            else if (ruolo.equalsIgnoreCase("Studente") || ruolo.equalsIgnoreCase("Formatore")) {
%>
              <li class="nav-item">
                <a class="nav-link" href="/formAct/view/autenticazione/Profilo.jsp">Profilo</a>
              </li>
<%
            }
          }
%>
          <li class="nav-item">

<%
            
            if (logError != null) {
              if (logError.equalsIgnoreCase("true")) {
%>
                <a class="nav-link" href="/formAct/view/autenticazione/Login.jsp">Login</a>
<%
        	  }
        	  else {
%>
                <a class="nav-link" href="${pageContext.request.contextPath}/AutenticazioneServlet/AutenticazioneService/LogoutService">Logout</a>
<%
              }
            }
            else {
%>
              <a class="nav-link" href="/formAct/view/autenticazione/Login.jsp">Login</a>  
<%
            }
%>
          </li>
          <li class="nav-item">
            <form class="d-flex" action="${pageContext.request.contextPath}/PercorsoFormativoServlet/RicercaPFService">
              <input class="" type="search" id="argomento" name="argomento" placeholder="Cerca un percorso formativo..." aria-label="Search">
              <button class="nav-link btn-dark" type="submit"><img src="/formAct/view/immagini/search-icon.png"></button>
            </form>
          </li>
        </ul>
      </div>
      
    </nav>
    
  </body>
</html>