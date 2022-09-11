<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
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
    
    <title>Ricerca</title>
    
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
  	    Ricerca:
  	  </div>
  	  
  	  <div class="contenuto">
  	    <form action="${pageContext.request.contextPath}/PercorsoFormativoServlet/RicercaPFService" method="post">
  	    
  	      <label for="argomento">Argomento (Opzionale):</label> <br>
    	  <input type="text" id="argomento" name="argomento" placeholder="esempio: Java"> <br> <br>
    	  
    	  Costo percorso formativo:<br>
    	  <label for="costoMin">Costo min (Opzionale):</label> 
    	  <input type="number" id="costoMin" class="rangeCosto" name="costoMin"><br>
    	  <label for="costoMax">Costo max (Opzionale):</label> 
    	  <input type="number" id="costoMax" class="rangeCosto" name="costoMax"><br> <br>
    	  
    	  <label for="costoMin">Disponibilità (Opzionale):</label> <br>
    	  <select name="disponibilita" id="disponibilita">
    	    <option value="">Seleziona una diponibilità</option>
    	    <option value="lunedi">Lunedì</option>
    	    <option value="martedi">Martedì</option>
    	    <option value="mercoledi">Mercoledì</option>
    	    <option value="giovedi">Giovedì</option>
    	    <option value="venerdi">Venerdì</option>
    	  </select> <br> <br>
    	  <button class="btn mt-3">Ricerca</button>
  	    </form>
  	  </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
</html>









