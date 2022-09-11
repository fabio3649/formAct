<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="model.entity.*"
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
    
    <title>Scheda percorso formativo</title>
    
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
  	    Scheda percorso formativo:
  	  </div>
  	  
  	  <div class="contenuto">
<%
        // generico
        PercorsoFormativoEntity percorso = (PercorsoFormativoEntity) request.getSession().getAttribute("percorso");
%>
<%
        // solo formatore
        ArrayList<DisponibilitaEntity> disponibilita = null; 
        if(ruolo.equalsIgnoreCase("Formatore")) {
        	disponibilita = (ArrayList<DisponibilitaEntity>) request.getSession().getAttribute("disponibilita");
		}
%>
		
<% 
		if (percorso != null) {
		  FormatoreEntity formatore = (FormatoreEntity) request.getSession().getAttribute("formatore");
		  CategoriaEntity categoria = (CategoriaEntity) request.getSession().getAttribute("categoria");
%>
          <span style="color: red;">Percorso formativo:</span> <br><br>
          <span style="color: red;">Nome: </span> <%= percorso.getNome() %><br><br>
          <span style="color: red;">Formatore: </span> 
          <% if(formatore!= null) { %> 
            <%= formatore.getSurname() + " " + formatore.getName() %>  
          <% } else { %>
            Assente
          <% } %>
          <br><br>
          <span style="color: red;">Descrizione: </span> <%= percorso.getDescrizione() %><br><br>
          <span style="color: red;">Categoria: </span> 
          <% if(categoria != null) { %> 
            <%= categoria.getNome() %>  
          <% } else { %>
            Assente
          <% } %>
          <br><br>
          <span style="color: red;">Ambito disciplinare: </span> 
          <% if(categoria != null) { %> 
            <%= categoria.getAmbito() %>  
          <% } else { %>
            Assente
          <% } %>
          <br><br>
          <span style="color: red;">Indice contenuti: </span> <%= percorso.getIndice_contenuti() %><br><br>
          <span style="color: red;">Numero di lezioni: </span> <%= percorso.getNum_lezioni() %><br><br>
          <span style="color: red;">Costo: </span> <%= percorso.getCosto() %> &#8364;<br><br>
          
<%
          if (ruolo.equalsIgnoreCase("Formatore")) {
		    if(disponibilita != null && disponibilita.size() > 0) {
%>
              <span style="color: red;">Disponibilità:</span> <br><br>
<%
			  for (DisponibilitaEntity disp : disponibilita) {
%>
                <span style="color: red;">Giorno: </span> <%= disp.getGiornoSettimana() %> 
                <span style="color: red;">Orario: </span> <%= disp.getOrario() %> <br> <br>
<%
			  }
		    }
		    else {
%>
              Nessuna disponibilità trovata.
<%
		    }
          }
%>
<%
		}
		else {
%>
		  Errore, non ho trovato il percorso formativo.
<%
		}
%>

  	  </div>
    </div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
</html>









