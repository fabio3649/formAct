<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="model.entity.*" import="model.dao.*" import="java.util.*"
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
    
    <title>La tua agenda - FormAct</title>
    
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
      <!-- Non obbligatorio -->
      <div class="immagineProfilo" style="width: 100%;">
        <img src="/formAct/view/immagini/avatar-login.png" alt="immagineProfilo" style="width:5%; display: block; margin:0 auto;">
      </div>
      
      <br>
      
  	  <div class="text-center mt-4 name" style="display: block;">
  	    <!-- Di cosa si occupa -->
  	  </div>
  	  
  	  <div class="contenuto">
  	    <!-- Contenuto pagina -->
  	    
  	    
  	    <%
  		ArrayList<IscrizioneEntity> isc = (ArrayList<IscrizioneEntity>) request.getSession().getAttribute("allSubscribe");
  	    PercorsoFormativoDao pDao = new PercorsoFormativoDao();
  	    ValutazioneDao vDao = new ValutazioneDao();
  	    int idStudente = (Integer) request.getSession().getAttribute("currentId");
  	    %>
  	   
  	    <%
  	    for( IscrizioneEntity i : isc) {
  	      PercorsoFormativoEntity p = (PercorsoFormativoEntity) pDao.doRetrieveByKey(i.getPercorsoFormativo());
  	    %>
  	    	
  	        <h2> <%=p.getNome() %> &nbsp;  <%=i.getGiorno()%> &nbsp <%=i.getOrario() %> </h2> 
  	        <form action="${pageContext.request.contextPath}/GestionePFServlet/VisualizzaPercorsoService" method="post" style="margin: 10px 10px 10px 10px;">                            
           	  <input type="hidden" value="<%=p.getId()%>" name="idPercorso"> 
              <button class="btn mt-3">Dettagli</button> 
            </form>
  	    	<form action="/formAct/view/serviziutente/ValutazioneFormatore.jsp"  style="margin: 10px 10px 10px 10px;">  
  	    	  <input type="hidden"  name="idPercorso" value="<%=p.getId() %>">
  	    	  <input type="submit" class="btn" name="Valuta" value="valuta"
<% 
                ValutazioneEntity valutazione = vDao.doRetrieveByKeys(idStudente, p.getId_formatore(), p.getId());
  	    	    if(! valutazione.equals(new ValutazioneEntity())) {
%>
                  disabled
<% 
  	            }
%>
  	    	    >
  	    	  </form>
  	    	  <form action="${pageContext.request.contextPath}/PercorsoFormativoServlet/DisiscrizionePFService" method="post" style="margin: 10px 10px 10px 10px;">                            
           	    <input type="hidden" value="<%=Integer.toString(p.getId())%>" name="currentPF"> 
                <input type="submit" class="btn" id="disiscrizioneButton" value="Disiscriviti">
              </form>
  	    	
  	    
  	    
  	   <% } %>
  	  </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
    
    
    
    
  </body>
</html>
