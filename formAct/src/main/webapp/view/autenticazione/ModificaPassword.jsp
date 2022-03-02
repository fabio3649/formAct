<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Libreria JQuery Ajax -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- javaScript -->
	<script src="ModificaPassword.js"></script>

    <!-- Bootstrap CSS -->
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  	
  	<link href="autenticazioneStyle.css" rel="stylesheet">
    <title>FormAct Login Page</title>
  </head>
  <body>
	
	<%@include file="/view/fragments/Header.jsp" %>
	
      <!-- Form Container-->
      <div class="mt-5 mb-5 p-4 pb-2 container border-secondary border-2 rounded pricipalContainer" >

        <!-- Label-Riempi le seguenti caselle di testo per modificare la password -->
        <div class="container p-0 m-0 mb-4 mt-2 text-center" >
          <label for="basic-url" class="form-label fw-bold ">Riempi le seguenti caselle di testo per modificare la password</label>
        </div>

        <!-- Spazio vuoto -->
        <div class="mb-2">
        </div>
		
        <!-- Password-Password , variabile="password" -->
        <div class="mb-4">
          <label for="formGroupExampleInput2" class="form-label">Password attuale</label>
          <input type="password" id="password" class="form-control" name="password" minlenght="8" required>
          <p class="text-danger" id="errorePassword1"></p>
        </div>
        
        <!-- Spazio vuoto -->
        <div class="p-2">
        </div>
        
        <!-- Password-Password , variabile="nuovaPassword" -->
        <div class="mb-4">
          <label for="formGroupExampleInput2" class="form-label">Nuova password</label>
          <input type="password" id="password2" class="form-control" name="nuovaPassword" minlenght="8" required>
          <p class="text-danger" id="errorePassword2"></p>
        </div>
        
        <!-- Password-Password , variabile="confermaPassword" -->
        <div class="mb-4">
          <label for="formGroupExampleInput2" class="form-label">Confera nuova password</label>
          <input type="password" id="confermaPassword" class="form-control" name="confermaPassword" minlenght="8" required>
          <p class="text-danger" id="errorePassword3"></p>
        </div>
        
        <!-- Sumbit Button -->
        <div class="mb-2 mt-3">
          <div class="row align-items-start">
            <div class="col">
            </div>
            <div class="col">
            </div>
            <div class="col">
            </div>
            <div class="col">
            </div>
            <div class="col">
              <input type="button" id="modificaPasswordButton" class="btn btn-outline-info" style="align:right;" value="Conferma">
            </div>
          </div>
        </div>
        
	</div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>