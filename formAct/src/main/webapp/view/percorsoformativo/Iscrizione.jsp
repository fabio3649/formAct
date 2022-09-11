<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="model.entity.*" 
    import="model.dao.*"
    import="java.time.LocalDate"
    import="java.time.ZoneId"
    import="java.util.Date"
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
    
    <title>Iscrizione percorso formativo</title>
    
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
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.0.min.js" type="text/javascript"></script>
  </head>

  <body>
    <!-------------------- Header -------------------->
    <div class="header_section" style="background-color: #CFEDF0;">
      <%@include file="/view/fragments/Header.jsp"%>
    </div>

    <div class="wrapperTable">
<%
	  DisponibilitaDao dao = new DisponibilitaDao();
	  PercorsoFormativoDao pDao = new PercorsoFormativoDao();	
      // prelevo dati percorso
      PercorsoFormativoEntity percorso = (PercorsoFormativoEntity) pDao.doRetrieveByKey(Integer.parseInt(request.getParameter("idPercorso")));
	  ArrayList<DisponibilitaEntity> disps = dao.doRetrieveAllAvailable(percorso.getId());
%>
  	  <div class="text-center mt-4 name" style="display: block;">
  	    Iscrizione: <%=percorso.getNome() %>
  	  </div>
      
	  <div class="contenuto">
		  
	    <form class="p-3 mt-3" id="iscrizionePercorsoForm" action="${pageContext.request.contextPath}/PercorsoFormativoServlet/IscrizionePFService" method="post">
	      
	      <label for="metodoPagamento">Tipo di carta</label>
	      <select id="metodoPagamento">
	        <option value="">Seleziona un tipo di carta</option>
	        <option value="visa">Visa</option>
	        <option value="mastercard">Mastercard</option>
	      </select>
	      <br>
	      <!-- Errore metodo pagamento -->
	      <p class="text-danger" id="erroreMetodoPagamento"></p>
	      <br> <br>
	      <label for="numeroCarta">Numero carta</label> 
	      <input type="text" id="numeroCarta" name="numeroCarta" maxlength="19">
	      <!-- Errore numero carta --> 
	      <p class="text-danger" id="erroreNumeroCarta"></p>
	      <br> <br>
          <label for="cvv">CVV</label> 
          <input type="text" id="cvv" name="cvv" maxlength="3">
          <!-- Errore CVV -->
           <p class="text-danger" id="erroreCVV"></p>
	      <br> <br>
<%
          int annoAttuale = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
          System.out.println(annoAttuale);
%>
	      <label for="dataScadenza">Data scadenza</label> 
	      <input type="number" id="meseScadenza" class="meseScadenza" name="meseScadenza" min="1" max="12" placeholder="Mese"> 
	      / 
	      <input type="number" id="annoScadenza" class="annoScadenza" name="annoScadenza" min="<%= annoAttuale %>" placeholder="Anno">
	      <br>
	      <!-- Errore mese di scadenza -->
	      <p class="text-danger" id="erroreMeseScadenza"></p>
	      <br>
	      <!-- Errore anno di scadenza -->
	      <p class="text-danger" id="erroreAnnoScadenza"></p>
	      <br> <br>
	      Scegli le disponibilità:
	      <br>
<%
          Integer k = 0;
          for(DisponibilitaEntity d : disps) {
%>          
            <input type="checkbox" class="giorni" id="giorno<%=+k%>" name="giorno<%=+k%>" value="<%=d.getIdDisp()%>"> <%=d.getGiornoSettimana() %> <%=d.getOrario() %>
            <br>
<% 
		    k++;
          }
%>
          <br>
          <!-- Errore disponibilità -->
          <p class="text-danger" id="erroreDisponibilita"></p>
          <br>
          
		  <input type="hidden" value="<%=k%>" name="iterator"> 
		  <input type="hidden" value="<%=percorso.getId()%>" name="idPercorso"> 	
		  <input type="button" class="btn" id="iscrizionePercorsoButton" onclick="iscrizionePercorsoFormativo()" value="Conferma iscrizione">
          
        </form>
      </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
    
    
    
    <script src="/formAct/view/js/validazioniForm/PercorsoFormativo.js"></script>
  </body>
</html>









