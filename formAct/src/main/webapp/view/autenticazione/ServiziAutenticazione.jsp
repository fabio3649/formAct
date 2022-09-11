<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="model.entity.FormatoreEntity"
    import="model.dao.StudenteDao"
    import="model.entity.StudenteEntity"
    import="model.utils.Utils"
    import="model.entity.PreferenzaStudenteEntity"
    import="java.util.ArrayList"
    import="model.dao.IscrizioneDao"
    import="model.entity.IscrizioneEntity"
    import="java.sql.Date"
    import="model.dao.InteresseStudenteDao"
    import="model.dao.PreferenzaStudenteDao"
    import="model.dao.PercorsoFormativoDao"
    import="model.entity.PercorsoFormativoEntity"
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
    if (servizio >= 1 && servizio <= 4) {
      if (servizio == 1) {
%>
        <title>I tuoi Dati personali</title>
<%
      }
      else if (servizio == 2) {
%>
        <title>Le tue iscrizioni</title>
<%
      }
      else if (servizio == 3) {
%>
        <title>I tuoi interessi</title>
<%
      }
      else if (servizio == 4) {
%>
        <title>Le tue disponibilità</title>
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
<%  
      if (servizio >= 1 && servizio <= 4) {
%>
        <div class="immagineProfilo" style="width: 100%;">
          <img src="/formAct/view/immagini/avatar-login.png" alt="immagineProfilo" style="width:5%; display: block; margin:0 auto;">
        </div>
<%
      }
%>
      
      <br>
      
  	  <div class="text-center mt-4 name" style="display: block;">
<%  
        if (servizio >= 1 && servizio <= 4) {
        	if(servizio == 1) {
%>
              Dati personali:
<%
            }
        	else if(servizio == 2) {
%>
              Le tue iscrizioni:
<%        		
        	}
        	else if(servizio == 3) {
%> 
              I tuoi interessi:
<%
			}
        	else if(servizio == 4) {
%>
              le tue disponibilità:
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
          /******************** Dati personali ********************/
          if(servizio == 1) {
        	FormatoreEntity formatore = null;
        	StudenteEntity studente = null;
            if (ruolo.equalsIgnoreCase("Studente")) {
        	  studente = (StudenteEntity) request.getSession().getAttribute("utente");
%>
              <span style="color: red;">Email:</span> <%= studente.getEmail() %> 
              <button class="btn btn-outline-info" onclick="modificaEmail()">Modifica email</button><br> <br>
              <div id="modificaEmail"></div>
              <span style="color: red;">Password:</span> **** 
              <button class="btn btn-outline-info" onclick="modificaPassword()">Modifica password</button><br> <br>
              <div id="modificaPassword"></div>
              <span style="color: red;">Nome:</span> <%= studente.getName() %> <br> <br>
              <span style="color: red;">Cognome:</span> <%= studente.getSurname() %> <br> <br>
              <span style="color: red;">Sesso:</span> <%= studente.getGender() %> <br> <br>
              <span style="color: red;">Data di nascita:</span> <%= Utils.toStringDate(studente.getBirthDate()) %> <br> <br>
              <span style="color: red;">Paese di origine:</span> <%= studente.getCountry() %> <br> <br>
<%
        	}
        	else if (ruolo.equalsIgnoreCase("Formatore")) {
        	  formatore = (FormatoreEntity) request.getSession().getAttribute("utente");
        	  String email = formatore.getEmail();
        	  String password = formatore.getPassword();
%>
              <span style="color: red;">Email:</span> <%= email %> 
              <a href="/formAct/view/autenticazione/ModificaEmail.jsp" class="btn btn-outline-info">Modifica email</a> <br> <br>
              <span style="color: red;">Password:</span> **** 
              <a href="/formAct/view/autenticazione/ModificaPassword.jsp" class="btn btn-outline-info">Modifica password</a> <br> <br>
              <span style="color: red;">Nome:</span> <%= formatore.getName() %> <br> <br>
              <span style="color: red;">Cognome:</span> <%= formatore.getSurname() %> <br> <br>
              <span style="color: red;">Sesso:</span> <%= formatore.getGender() %> <br> <br>
              <span style="color: red;">Data di nascita:</span> <%= Utils.toStringDate(formatore.getBirthDate()) %> <br> <br>
              <span style="color: red;">Paese di origine:</span> <%= formatore.getCountry() %> <br> <br>
              <span style="color: red;">Codice fiscale:</span> <%= formatore.getCodiceFiscale() %> <br> <br>
              <span style="color: red;">Conto corrente:</span> <%= formatore.getContoCorrente() %> <br> <br>
<%
        	}
          }
          /******************** Iscrizioni ********************/
          else if (servizio == 2) {
            Integer id = (Integer) request.getSession().getAttribute("currentId");
          	IscrizioneDao iDao = new IscrizioneDao();
          	ArrayList<IscrizioneEntity> iscrizioni = (ArrayList<IscrizioneEntity>) iDao.doRetrieveByStudent(id);
            PercorsoFormativoDao pfDao = new PercorsoFormativoDao();
          	if (iscrizioni != null && iscrizioni.size() > 0) {
            for (IscrizioneEntity iscrizione : iscrizioni) {
            	PercorsoFormativoEntity percorsoFormativo = (PercorsoFormativoEntity) pfDao.doRetrieveByKey(iscrizione.getPercorsoFormativo());
%>
                <%= percorsoFormativo.getNome() %> 
                <a href="#" class="btn btn-outline-info">Dettagli</a> <br> <br>
<%
              }
            }
          }
          /******************** Interessi ********************/
          else if (servizio == 3) {
            Integer id = (Integer) request.getSession().getAttribute("currentId");
            InteresseStudenteDao isDao = new InteresseStudenteDao();
            ArrayList<String> interessi = (ArrayList<String>) isDao.doRetrieveInteressiStudente(id);
            if (interessi != null && interessi.size() > 0) {
              for (String interesse : interessi) {
%>
                <%= interesse.toString() %> <br> <br>
<%
              }
            }
          }
          else if (servizio == 4) {
            Integer id = (Integer) request.getSession().getAttribute("currentId");
            PreferenzaStudenteDao psDao = new PreferenzaStudenteDao();
            ArrayList<PreferenzaStudenteEntity> disponibilita
                    = (ArrayList<PreferenzaStudenteEntity>) psDao.doRetrieveAllByStudent(id);
            if (disponibilita != null && disponibilita.size() > 0) {
              for (PreferenzaStudenteEntity disp : disponibilita) {
%>
                <%= disp.getDisponibilita() %> <br> <br>
<%
              }
            }
          }
        }
%>
  	  </div>
    </div>
    <%= request.getParameter("emailError") %>
    <%@include file="/view/fragments/Footer.jsp"%>
    
    
    
    
    <script>
      function modificaEmail() {
        if (document.getElementById("modificaEmail").innerHTML === "") {
          
          var form = "<form class= \'p-3 mt-3\' action=\'${pageContext.request.contextPath}/AutenticazioneServlet/ModificaEmailService\' method=\'post\'> \n";     
          var emailAttuale = "<input type=\'hidden\' id=\'emailAttuale\' class=\'emailAttuale\' name=\'emailAttuale\' name=\'emailAttuale\' value=\'"+emailAttuale+"\' \n";
          var nuovaEmail = "<label for=\'nuovaEmail\'><span style=\'color: red;\'>Nuova email:</span></label> \n";
          nuovaEmail += "<input type=\'email\' id=\'nuovaEmail\' class=\'nuovaEmail\' name=\'nuovaEmail\'> <br> <br> \n";
    	  
    	  var confermaEmail = "<label for=\'confermaEmail\'><span style=\'color: red;\'>Conferma nuova email:</span></label> \n";
    	  confermaEmail += "<input type=\'email\' id=\'confermaEmail\' class=\'confermaEmail\' name=\'confermaEmail\'> <br> <br> \n";
          
    	  var confermaButton = "<button class=\'btn btn-outline-info\'>Conferma</button><br><br> \n";
    	  
    	  form += emailAttuale + nuovaEmail + confermaEmail + confermaButton + "</form> \n";
    	  
          document.getElementById("modificaEmail").innerHTML = form;
        }
        else {
          document.getElementById("modificaEmail").innerHTML = "";
        }
        document.getElementById("modificaPassword").innerHTML = "";
      }
      
      function modificaPassword() {
        if (document.getElementById("modificaPassword").innerHTML === "") {
          var password = "<label for=\'password\'><span style=\'color: red;\'>Nuova password:</span></label> ";
          password += "<input type=\'password\' id=\'password\' class=\'password\' name=\'password\'> <br> <br>";
      	  
      	  var confermaPassword = "<label for=\'confermaPassword\'><span style=\'color: red;\'>Conferma nuova password:</span></label> ";
      	  confermaPassword += "<input type=\'password\' id=\'confermaPassword\' class=\'confermaPassword\' name=\'confermaPassword\'> <br> <br>";  
          
      	var conferma = "<button class=\'btn btn-outline-info\'>Conferma</button><br><br>";
      	  
          document.getElementById("modificaPassword").innerHTML = password + confermaPassword + conferma;
        }
        else {
          document.getElementById("modificaPassword").innerHTML = "";
        } 
        document.getElementById("modificaEmail").innerHTML = "";
      }
    </script>
  </body>
  
</html>






