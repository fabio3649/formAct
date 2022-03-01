<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
    <link href="registrazioneStyle.css" rel="stylesheet">
    <title>Registrazione Utente</title>
  </head>
  <body>
	<%@include file="/view/fragments/Header.jsp" %>
	
	  <div class="page">
		<div class="container">
 			<div class="row" style="border-style:solid;">
 				
 				<div class="container p-5" >
 					<p style="text-align: center;">Seleziona in che modo desideri iscriveti alla piattaforma</p>
 				</div>
 				
 				<div class="col p-5" style="border-style:solid; text-align: center;">
 					<div class="container ">
						<button type="button" class="btn btn-outline-info"><img alt="" src="/formAct/view/registrazione/studente.png" style="max-width:200px;"></button>
					</div>
					<div class="container">
						<label>Iscriviti come Studente</label>
					</div>
    			</div>
    			
    			<div class="col p-5" style="border-style:solid; text-align: center;">
 					<div class="container">
						<button type="button" class="btn btn-outline-info"><img alt="" src="/formAct/view/registrazione/formatore.png" style="max-width:200px;"></button>
					</div>
					<div class="container">
						<label>Iscriviti come Formatore</label>
					</div>
    			</div>
    			
    		</div>
		</div>
	  </div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>