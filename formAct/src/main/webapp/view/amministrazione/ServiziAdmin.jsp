<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="model.dao.StudenteDao"
    import="model.dao.FormatoreDao"
    import="model.dao.PercorsoFormativoDao"
    import="model.dao.CategoriaDao"
    import="model.entity.StudenteEntity"
    import="model.entity.FormatoreEntity"
    import="model.entity.PercorsoFormativoEntity"
    import="model.entity.CategoriaEntity"
%>

<%
  Integer servizio = 0;
  String errore = "";
  if (request.getParameter("servizio") == null) {
    errore = "Errore, Servizio non trovato.";
  }
  else {
    servizio = Integer.parseInt(request.getParameter("servizio"));
  }
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
    
<%  
    if (servizio >= 1 && servizio <= 3) {
      if (servizio == 1) {
%>
        <title>Studenti</title>
<%
      }
      else if (servizio == 2) {
%>
        <title>Formatori</title>
<%
      }
      else if (servizio == 3) {
%>
        <title>Percorsi formativi</title>
<%
      }
    }
    else {
%>
      <title>Errore</title>
<%
    }
%>
    
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
<%  
        if (servizio >= 1 && servizio <= 3) {
          if(servizio == 1) {
%>
            Studenti:
<%
          }
          else if(servizio == 2) {
%>
            Formatori:
<%        		
          }
          else if(servizio == 3) {
%> 
            Percorsi formativi:
<%
	      }
        }
        else {
%>  
          <%= errore %>
<%
        }
%>
  	  </div>
  	  
  	  <div class="contenuto">
<%  
        if (servizio >= 1 && servizio <= 4) {
          /******************** Studenti ********************/
          if(servizio == 1) {
            StudenteDao sDao = new StudenteDao();
            ArrayList<StudenteEntity> studenti = (ArrayList<StudenteEntity>) sDao.doRetrieveAll();
            if (studenti != null && studenti.size() > 0) {
              for (StudenteEntity studente : studenti) {
%>
                <%= studente.getName() + " " + studente.getSurname() %> 
                <a href="#" class="btn btn-outline-info">Dati anagrafici</a> 
                <a href="#" class="btn btn-outline-info">Valutazioni inserite</a> 
                <a href="#" class="btn btn-outline-info">Iscrizioni</a> <br> <br>
<%
              }
            }
            else {
%>
              Nessun studente trovato
<%
            }
          }
          /******************** Formatori ********************/
          else if (servizio == 2) {
            FormatoreDao fDao = new FormatoreDao();
            ArrayList<FormatoreEntity> formatori = fDao.doRetrieveAll();
              if (formatori != null && formatori.size() > 0) {
                for (FormatoreEntity formatore : formatori) {
%>
                  <%= formatore.getName() + " " + formatore.getSurname() + ", votazione media: ..."%> 
                  <a href="#" class="btn btn-outline-info">Dati anagrafici</a> 
                  <a href="#" class="btn btn-outline-info">Valutazioni ricevute</a> 
                  <a href="#" class="btn btn-outline-info">Percorsi formativi</a> <br> <br>
<%
                }
              }
              else {
%>
                Nessun formatore trovato
<%
              }
            }
          /******************** Percorsi formativi ********************/
          else if (servizio == 3) {
            PercorsoFormativoDao pfDao = new PercorsoFormativoDao();
            ArrayList<PercorsoFormativoEntity> percorsi = pfDao.doRetrieveAll();
            if (percorsi != null && percorsi.size() > 0) {
              for (PercorsoFormativoEntity percorso: percorsi) {
%>
                <%= percorso.getNome() + " gestito da: ..., votazione: ..."%> 
                <a href="#" class="btn btn-outline-info">Dettagli</a> 
                <a href="#" class="btn btn-outline-info">Partecipanti</a> <br> <br>
<%
              }
            }
            else {
%>
              Nessun percorso formativo trovato
<%
            }
          }
          /******************** Categorie ********************/
          else if (servizio == 4) {
            CategoriaDao cDao = new CategoriaDao();
            ArrayList<CategoriaEntity> categorie = cDao.doRetrieveAll();
            if (categorie != null && categorie.size() > 0) {
              for (CategoriaEntity categoria: categorie) {
%>
                <%= categoria.getNome()%> 
                <a href="#" class="btn btn-outline-info">Modifica</a> 
                <a href="#" class="btn btn-outline-info">Rimuovi</a> <br> <br>
<%
              }
            }
            else {
%>
              Nessuna categoria trovata
<%
            }
%>
            <a href="#" class="btn btn-outline-info">Aggiungi categoria</a> <br> <br>
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







