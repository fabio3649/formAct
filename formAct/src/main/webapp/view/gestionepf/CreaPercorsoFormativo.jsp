<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.ArrayList"
    import="model.dao.CategoriaDao"
    import="model.dao.DisponibilitaDao"
    import="model.entity.CategoriaEntity"
    import="model.entity.DisponibilitaEntity"
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
    
    <title>Creazione percorso formativo</title>
    
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
  	  <div class="text-center mt-4 name" style="display: block;">
        Creazione percorso formativo:
  	  </div>

  	  <div class="contenuto">
<%
        if (ruolo == null) {
%>      
          Non sei loggato.
<%
        }
        else if (!ruolo.equalsIgnoreCase("Formatore")) {
%>
          Non sei un formatore.
<%
        }
        else {
%>
          <form class="p-3 mt-3" id="creaPercorsoFormativoForm" action="${pageContext.request.contextPath}/GestionePFServlet/CreatorService" method="post">
            <label for="nome">Nome percorso formativo:</label> 
            <br>
		    <textarea id="nome" rows="5" cols="30" name="nome"></textarea> 
		    <br>
		    <!-- Errore nome -->
		    <p class="text-danger" id="erroreNome"></p>
		    <br> <br>
		    
		    <label for="descrizione">Descrizione:</label> <br> <br>
		    <textarea id="descrizione" rows="5" cols="30" name="descrizione"></textarea>
		    <br>
		    <!-- Errore descrizione -->
		    <p class="text-danger" id="erroreDescrizione"></p>
		    <br><br>
		    
		    <label for="indice">Indice dei contenuti:</label> <br> <br>
	        <textarea id="indice" rows="5" cols="30" name="indice" ></textarea>
	        <br>
		    <!-- Errore indice dei contenuti -->
		    <p class="text-danger" id="erroreIndiceContenuti"></p>
	        <br><br>
	        
	        <label for="categoria">Scegli la categoria:</label>
	        <select  id="categoria" name="categoria">
<%
              CategoriaDao cDao = new CategoriaDao();
              ArrayList <CategoriaEntity> categorie = cDao.doRetrieveAll();
              DisponibilitaDao dDao = new DisponibilitaDao();
              ArrayList<DisponibilitaEntity> disps = dDao.doRetrieveAll();
%>
              <option value=""> Scegli una categoria </option>	 
<%
              for (int i = 0; i < categorie.size(); i++) {
                CategoriaEntity c = categorie.get(i);
%>
                <option value="<%=c.getIdCategoria()%>"> <%=c.getNome()%> </option>	 	 
<%
              }
%>
              </select> <br> <br> 
              <!-- Errore categoria -->
		      <p class="text-danger" id="erroreCategoria"></p>
		      <br>
              
              <label for="lezioni">Numero di lezioni</label> 
		      <input type="number" id="lezioni" name="lezioni" required maxlength="16" required="required" value="0"><br><br>
		      <br>
		      <!-- Errore numero lezioni -->
		      <p class="text-danger" id="erroreNumLezioni"></p>
		      <br>
		      <label for="disponibilita">Inserisci le disponibilità del percorso formativo:</label>
		      <br>
		      
		      <!-- Aggiungi disponibilità -->
		      <div id="add" class="addButton mt-3" onclick="addDisp()" style="display:inline-block;">+</div> 
		      <!-- Disponibilità -->
		      <div id="disponibilita" style="display:inline-block;"></div> 
		      <!-- Sottrai disponibilità -->
		      <div id="sub" class="subButton mt-3" onclick="subDisp()" style="display:inline-block;">-</div>
		      <br><br>
		      <!-- Errore giorno -->
		      <p class="text-danger" id="erroreGiorno"></p>
		      <!-- Errore orario -->
		      <p class="text-danger" id="erroreOrario"></p>
              
              
              <label for="costo">Costo:</label> 
              <input type="number" min="0.01" step="0.01" id="costo" name="costo" required="required" value="0"> &#8364; 
              <!-- Errore costo -->
		      <p class="text-danger" id="erroreCosto"></p>
              <br> <br>
              
              <input type="button" class="btn" id="creaPercorsoFormativoButton" onclick="creaPercorsoFormativo()" value="Crea percorso formativo">
          </form>
<%
        }
%>
      </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
    
    <script src="/formAct/view/js/validazioniForm/Disponibilita.js" type="text/javascript"></script>
    <script src="/formAct/view/js/validazioniForm/GestionePF.js" type="text/javascript"></script>
  </body>
</html>









