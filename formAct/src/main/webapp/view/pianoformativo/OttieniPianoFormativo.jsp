<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    
    import="pianoformativo.geneticalgorithm.Stato"
    import="pianoformativo.geneticalgorithm.Soluzione"
    
    import="model.entity.PercorsoFormativoEntity"
    import="model.entity.FormatoreEntity" 
    
    import="java.util.Map"
    import="java.util.HashMap"
    
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    
    <title>Piano formativo personalizzato:</title>
    
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
  	<div class="contenuto">
<%
      Map<Integer, String> formatori = (HashMap<Integer, String>) request.getSession().getAttribute("formatori");
      Soluzione soluzione = (Soluzione) request.getSession().getAttribute("soluzione");
      if (soluzione != null && soluzione.getStati() != null && soluzione.getSize() > 0) {
%>
        <div class="wrapperTable">
  	      <div class="text-center mt-4 name" style="display: block;">
  	        <font class="text-danger">Piano formativo personalizzato consigliato:</font>
  	      </div>
        
  	      <br>
          
          <div class="row">
<%
            int i = 0;
            int j = 0;
			for(i = 0, j = 0; i < soluzione.getSize(); i++, j++) {
			  if (j == 4) {
				  break;
			  }
			  Stato stato = soluzione.getStato(i);
			  PercorsoFormativoEntity percorso = stato.getPercorsoFormativo();
			  String formatore = formatori.get(percorso.getId_formatore());
%>
              <div class="col border border-primary">
                <font class="text-danger" >Formatore:</font>
                <br>
				<%= formatore %>
                <br> <br>
                <font class="text-danger" >Percorso formativo:</font>
				<br>
				<font class="text-danger" >Nome:</font> <%= percorso.getNome() %>
				<br>
				<font class="text-danger" >Descrizione:</font> <%= percorso.getDescrizione() %>
				<br>
 				<%= percorso.getNum_lezioni() %> lezioni
                <br>
				<font class="text-danger" ><%= stato.getGiorno() %> ore <%= stato.getOrario() %></font>
				<br>
				<font class="text-danger" >Costo:</font> <%= percorso.getCosto() %> &#x20AC;
				<br> <br>
				<form action="${pageContext.request.contextPath}/GestionePFServlet/VisualizzaPercorsoService" method="post">
           		  <input type="hidden" value="<%=percorso.getId()%>" name="idPercorso"> 
            	  <button class="btn mt-3">Dettagli</button> 
                </form>
				<form action="/formAct/view/percorsoformativo/Iscrizione.jsp" method="post">
            	  <input type="hidden" value="<%=percorso.getId()%>" name="idPercorso"> 
            	  <button class="btn mt-3">Iscriviti</button>
                </form>
		        <br> <br>
              </div>
<%
            }
	        while (0 < j && j < 4) {
%>
              <div class="col border border-primary"></div>
<%
	          j++;
	        }
%>
          </div>
          
          <br> <br>
          
          <div class="text-center mt-4 name" style="display: block;">
  	        <font class="text-danger">Percorsi formativi consigliati:</font>
  	      </div>
  	      
  	      <br>
  	      
          <div class="row">
<% 
		  for (j = 0; i < soluzione.getSize(); i++, j++) {
            if (j == 3) {
		      j = 0;
%>
              </div>
              <div class="row">
<%
			}
			Stato stato = soluzione.getStato(i);
			PercorsoFormativoEntity percorso = stato.getPercorsoFormativo();
			String formatore = formatori.get(percorso.getId_formatore());
%>
            <div class="col border border-primary">
              <font class="text-danger" >Formatore:</font>
              <br>
			  <%= formatore %>
              <br> <br>
              <font class="text-danger" >Percorso formativo:</font>
			  <br>
			  <font class="text-danger" >Nome:</font> <%= percorso.getNome() %>
			  <br>
 			  <%= percorso.getNum_lezioni() %> lezioni
			  <br>
			  <font class="text-danger" >Costo:</font> <%= percorso.getCosto() %> &#x20AC;
			  <br> <br>
				<form action="${pageContext.request.contextPath}/GestionePFServlet/VisualizzaPercorsoService" method="post">
           		  <input type="hidden" value="<%=percorso.getId()%>" name="idPercorso"> 
            	  <button class="btn mt-3">Dettagli</button> 
                </form>
		      <br> <br>
            </div>
<%
		  }
          while (0 < j && j < 3) {
%>
            <div class="col border border-primary"></div>
<%
            j++;
          }
%>
          </div>
        </div>
                    
        <br> <br>
            
        
            
<%
      }
%>
    </div>    
  </body>
</html>
       
        
   
        
        
          
          
                
                
          
          








