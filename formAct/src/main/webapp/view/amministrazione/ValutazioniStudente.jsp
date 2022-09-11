<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="model.dao.*"
    import="model.entity.*"
    import="model.utils.Utils"
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
    
    <title>Valutazioni dello studente</title>
    
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
    
    <style>
      table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
	  }
      
      td, th {
	    border: 1px solid #dddddd;
	    text-align: left;
	    padding: 8px;
	  }
      
  	  tr:nth-child(even) {
	    background-color: #dddddd;
	  }
    
    </style>
  </head>
  
  <body>
    <!-------------------- Header -------------------->
    <div class="header_section" style="background-color: #CFEDF0;">
      <%@include file="/view/fragments/Header.jsp"%>
    </div>
    
    <div class="wrapperTable">
  	  <div class="text-center mt-4 name" style="display: block;">
  	    Valutazioni dello studente
  	  </div>
  	  
  	  <br>
  	  
  	  <div class="contenuto">
<%
        if (ruolo == null || (!ruolo.equalsIgnoreCase("Amministratore"))) {
%>        	
          Non sei un amministratore.
<%
        }
        else {
          int idStudente = Integer.parseInt(request.getParameter("idStudente"));
        
          ValutazioneDao vDao = new ValutazioneDao();
          PercorsoFormativoDao pfDao = new PercorsoFormativoDao();
          FormatoreDao fDao = new FormatoreDao();
          ArrayList<ValutazioneEntity> valutazioni = vDao.doRetrieveByStudente(idStudente);
          if(valutazioni != null && valutazioni.size() > 0) {
%>
            <table border="1">
              <tr>
                <th>Percorso formativo</th>
                <th>Studente</th>
                <th>Descrizione</th>
                <th>Stelle</th>
                <th>Data</th>
              </tr>
<%
              for(ValutazioneEntity v : valutazioni) {
                String formatore = ((FormatoreEntity) fDao.doRetrieveByKey(v.getFormatore())).getName()
                	+ "<br>" + ((FormatoreEntity) fDao.doRetrieveByKey(v.getFormatore())).getSurname();
%>
                <tr>
                  <td><%= ((PercorsoFormativoEntity) pfDao.doRetrieveByKey(v.getPercorsoFormativo())).getNome() %></td>
                  <td><%= formatore %></td>
                  <td><%= v.getDescrizione() %></td>
                  <td><%= v.getStelle() %>
                  <td><%= Utils.toStringDate(v.getData()) %></td>
                </tr>
<%
              }
%>
            </table>
<% 
          }
          else {
%>
            Nessuna valutazione trovata.
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









