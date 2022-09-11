<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="model.entity.StudenteEntity"
    import="model.entity.FormatoreEntity"
    import="model.dao.StudenteDao"
    import="model.dao.FormatoreDao"
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
    
    <title>Gestione formAct</title>
    
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
    
    <div class="wrapper">
      <div class="text-center mt-4 name">
        Gestione formAct
      </div>
      
      <div class="p-3 mt-3">
        <div class="row justify-content-md-center" style="width:80%;">
          <form action="${pageContext.request.contextPath}/AdminServlet/GetAllStudentsService" method="post">
            <button class="btn">Tutti gli Studenti</button>
	      </form>
	      <br><br><br>
	      <form action="${pageContext.request.contextPath}/AdminServlet/GetAllTrainersService">
	        <button class="btn">Tutti i Formatori</button>
	      </form>
	      <br><br><br>
	      <form action="${pageContext.request.contextPath}/AdminServlet/GetAllIscrizioniFromStudentService">
	        <button class="btn">Iscrizioni studente</button>
		    <input type="text" name="idStudente" placeholder="id Studente">
	      </form>
	      <br>
	      <form action="${pageContext.request.contextPath}/AdminServlet/GetAllFBFromTrainerService">
		    <button class="btn">Valutazioni formatore</button>
		    <input type="text" id="idFormatore" name="idFormatore" placeholder="id Formatore">
	      </form>
	      <br>

          
          
          <div class="col lg">
          <br>
            <a href="/formAct/view/amministrazione/CreaCategoria.jsp" class="btn btn-outline-info">Categorie</a> <br> <br>
          </div>
        </div>
      </div>
    </div>
    
    <%@include file="/view/fragments/Footer.jsp"%>
  </body>
</html>

















