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
  	  <div class="text-center mt-4 name" style="display: block;">
  	    Ottieni piano formativo personalizzato:
  	  </div>

  	  <br>

  	  <div class="contenuto">
<%
        if(ruolo == null || !ruolo.equalsIgnoreCase("Studente")) {
%>
        	Non sei uno studente.
<%
        }
        else {
%>
  	      <span style="color: red;">Interessi inseriti nel profilo:</span> <br>
<%
          Integer id = (Integer) request.getSession().getAttribute("currentId");
          
          ArrayList<String> interessi = (ArrayList<String>) request.getSession().getAttribute("interessi");
          
          if (interessi != null && interessi.size() > 0) {
%>
            <div class="row">
<%
              int j = 0;
              for (int i = 0; i < interessi.size(); i++) {
                if (j == 3) { 
                  j = 0;
%>
                  </div>
                  <div class="row">
<%
                }
        	    String interesse1 = interessi.get(i);
                int posParentesiTonda = interesse1.indexOf("(");
                if (posParentesiTonda >= 0) {
                  interesse1 = interesse1.substring(0, posParentesiTonda);
                }
%>
                <div class="col"> <%= interesse1 %> </div>
<% 
                j++;
              }  
%>
            </div>
<%
          }
          else {
%>
            <div class="row">
              <div class="col">
                Nessun interesse trovato. 
              </div>
            </div>
<%
          }
%>
          <br> <br>
<%
          ArrayList<PreferenzaStudenteEntity> giorniLiberi = 
		          (ArrayList<PreferenzaStudenteEntity>) request.getSession().getAttribute("giorniLiberi");
%>
          <span style="color: red;">Giorni liberi inseriti nel profilo:</span> <br>
<%
          if (giorniLiberi != null && giorniLiberi.size() > 0) {
%>
            <div class="row">
<% 
              for (PreferenzaStudenteEntity giornoLibero : giorniLiberi) {
%>        
                <div class="col">
                  <%= giornoLibero.getDisponibilita() %>
                </div>
<%
              }
%>
            </div>
<%
          }
          else {
%>
            <div class="row">
              <div class="col">
                Nessun giorno libero trovato.
              </div>
            </div>
<%
          }
%>
          <br> <br>
          
          <!-- 
            Premendo sul bottone "Ottieni un piano formativo personalizzato" visualizziamo un piano formativo personalizzato
           -->
          <form action="${pageContext.request.contextPath}/PianoFormativoServlet/OttieniPianoService" method="post">
            <button class="btn mt-3">Ottieni un piano formativo personalizzato</button> 
            Tempo massimo impiegato: circa 5 secondi.
          </form>
<%
        }
%>
      </div>
  	</div>
    
    <!-------------------- Footer -------------------->
    <%@include file="/view/fragments/Footer.jsp"%>    
  </body>
</html>



        
        
        

        
        
        

          




   


        

        



  

    










