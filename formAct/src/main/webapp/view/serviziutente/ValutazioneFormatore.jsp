<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    
    import="java.util.ArrayList"
    
    import="model.entity.FormatoreEntity"
    import="model.entity.StudenteEntity"
    import="model.entity.PercorsoFormativoEntity"
    
    import="model.dao.FormatoreDao"
    import="model.dao.StudenteDao"
    import="model.dao.PercorsoFormativoDao"
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
<%
      if(ruolo != null && ruolo.equalsIgnoreCase("Studente")) {
%>
      
  	    <div class="text-center mt-4 name" style="display: block;">
  	      Valutazione:
  	    </div>
  	    
  	    <div class="contenuto">
<%
          FormatoreDao fDao = new FormatoreDao();
          StudenteDao sDao = new StudenteDao();
          PercorsoFormativoDao pfDao = new PercorsoFormativoDao();
          
          PercorsoFormativoEntity percorso = (PercorsoFormativoEntity) pfDao.doRetrieveByKey((Integer.parseInt(request.getParameter("idPercorso"))));
          FormatoreEntity formatore = fDao.doRetrieveByKey(percorso.getId_formatore());
%>
          <span style="color: red;">Formatore: </span> <%= formatore.getName() + " " + formatore.getSurname() %>
          <br> <br>
  	      <span style="color: red;">Percorso formativo: </span> <%= percorso.getNome() %>
  	      <br> <br>
  	      <form  action="${pageContext.request.contextPath}/ServiziUtenteServlet/ValutazioneService" method="post" >
  	      Votazione (obbligatorio):
  	      <input type="hidden"  name="idPercorso" value="<%=percorso.getId()%>">
  	      <div class="stars" style="display">
  	        <div id="star0" class="star">
  	          <input type="hidden" id="star0IsSelected" name="star0IsSelected" value="false">
              <img id="imgStar0" src="/formAct/view/immagini/starGray.png" alt="star0" style="width:4%;">                                                             
  	        </div>
  	        <div id="star1" class="star">
  	          <input type="hidden" id="star1IsSelected" name="star1IsSelected" value="false">
              <img id="imgStar1" src="/formAct/view/immagini/starGray.png" alt="star1" onMouseOver="onStarOver(1)" onMouseOut="onStarOut(1)" onclick="onStarClick(1,5)" style="width:4%;">
            </div>
  	        <div id="star2" class="star">
  	          <input type="hidden" id="star2IsSelected" name="star2IsSelected" value="false">
              <img id="imgStar2" src="/formAct/view/immagini/starGray.png" alt="star2" onMouseOver="onStarOver(2)" onMouseOut="onStarOut(2)" onclick="onStarClick(2,5)" style="width:4%;">
            </div>
  	        <div id="star3" class="star">
  	          <input type="hidden" id="star3IsSelected" name="star3IsSelected" value="false">
  	          <img id="imgStar3" src="/formAct/view/immagini/starGray.png" alt="star3" onMouseOver="onStarOver(3)" onMouseOut="onStarOut(3)" onclick="onStarClick(3,5)" style="width:4%;">
  	        </div>
  	        <div id="star4" class="star">
  	          <input type="hidden" id="star4IsSelected" name="star4IsSelected" value="false">
  	          <img id="imgStar4" src="/formAct/view/immagini/starGray.png" alt="star4" onMouseOver="onStarOver(4)" onMouseOut="onStarOut(4)" onclick="onStarClick(4,5)" style="width:4%;">
  	        </div>
  	      </div>
  	      <br> <br>
  	      Descrizione (obbligatorio): <br>
  	      <textarea id="descrizione" rows="5" cols="30" name="descrizione" onMouseOver="onStarOver('star0')" onMouseOut="onStarOut('star0')" required="required"></textarea>
  	      <br> <br>
  	      
  	      <button class="btn mt-3">Invia valutazione</button>
  	   </form>
  	    </div>
<%
      }
      else {
        if (ruolo == null) {
%>
          <div class="text-center mt-4 name">
            Errore, non sei loggato. <br>
            <a href="/formAct/view/autenticazione/Login.jsp" class="btn">Login</a> <br> <br>
          </div>
<%
        }
        else {
%>
  	      <div class="text-center mt-4 name" style="display: block;">
  	        Errore, non sei uno studente, non puoi inserire una valutazione
  	      </div>
<%
        }
      }
%>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
    
    
    
    
    
    
    
    
    
    <script>
      selectStar(0);
      
      function onStarOver(numberStar) {
          for(var i = 1; i <= parseInt(numberStar); i++) {
		      var imgStar = document.getElementById('imgStar' + i);
              imgStar.src = "/formAct/view/immagini/starLightBlue.png";
          }
      }
      
      function onStarOut(numberStar) {
          for(var i = 1; i <= parseInt(numberStar); i++) {
              var starIsSelected = document.getElementById('star' + i + 'IsSelected').value;
              var imgStar = document.getElementById('imgStar' + i);
              
              if (starIsSelected === "false") {
        	      imgStar.src = "/formAct/view/immagini/starGray.png";
              }
          }
      }
      
      function onStarClick(numberStarSelected, numberStars) {
          var isSelected = false;
    	  // Stella selezionata
    	  var starIsSelected = document.getElementById('star' + numberStarSelected + 'IsSelected');
    	  var imgStar = document.getElementById('imgStar' + numberStarSelected);
    	  if(starIsSelected.value === "true") {
    		  if (parseInt(numberStarSelected) < parseInt(numberStars - 1)) {
    		      if (   document.getElementById('star' + (numberStarSelected + 1) + 'IsSelected').value === "true") {
    		          selectStar(numberStarSelected);
    		      }
    		      else {
    			      unselectStar(numberStarSelected);
    		      }
    		  }
    		  else {
    			  unselectStar(numberStarSelected);
    		  }
          }
    	  else {
    	      selectStar(numberStarSelected);  
    	  }
    	  
    	  for (var i = 0; i < parseInt(numberStarSelected); i++) {
    		  selectStar(i);  
    	  }
    	  
    	  for (var i = numberStarSelected + 1; i < numberStars; i++) {
    		  unselectStar(i);
    	  }
      }
      
      function selectStar (numberStar) {
    	  var starIsSelected = document.getElementById('star' + numberStar + 'IsSelected');
    	  var imgStar = document.getElementById('imgStar' + numberStar);
    	  starIsSelected.value = "true";
          imgStar.src = "/formAct/view/immagini/starLightBlue.png";  
      }
      
      function unselectStar (numberStar) {
    	  var starIsSelected = document.getElementById('star' + numberStar + 'IsSelected');
    	  var imgStar = document.getElementById('imgStar' + numberStar);
		  starIsSelected.value = "false";
          imgStar.src = "/formAct/view/immagini/starGray.png";
      }
    </script>
  </body>
</html>









