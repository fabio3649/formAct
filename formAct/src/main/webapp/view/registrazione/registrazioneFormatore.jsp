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
	<script src="RegistrazioneFormatore.js"></script>
	<script src="Paesi.js"></script>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <link href="registrazioneStyle.css" rel="stylesheet">
    <title>Registrazione Formatore</title>
  </head>
  <body>
    <% request.getSession().setAttribute("register", "formatore"); %>
    
	<%@include file="/view/fragments/Header.jsp" %>
	<% request.setAttribute("register", "ciao"); %>
	<!-- Inizio Form -->
	<form action="" method="post">
	
      <!-- Form Container-->
      <div class="mt-5 mb-5 p-4 pb-5 container border-secondary border-2 rounded pricipalContainer" >

        <!-- Dati Autenticazione -->
        <div class="container p-0 m-0 mb-4 mt-2 text-center" >
          <label for="basic-url" class="form-label fw-bold ">Dati Autenticazione</label>
        </div>

        <!-- Spazio vuoto -->
        <div class="mb-2">
        </div>
				

		
        <!-- Text-Email , variabile="email" -->
        <div class="mb-3">
          <label for="formGroupExampleInput2" class="form-label">Email</label>
          <input type="text" class="form-control" id="email" name="email" minlength="6" required>
		  <p class="text-danger" id="erroreEmail"></p>
        </div>

        <!-- Password-Password , variabile="password" -->
        <div class="mb-3">
          <label for="formGroupExampleInput2" class="form-label">Password</label>
          <input type="password" id="password" class="form-control" name="password" minlenght="8" required>
          <p class="text-danger" id="errorePassword1"></p>
        </div>

        <!-- Conferma Password-Password , variabile="password2" -->
        <div >
          <label for="formGroupExampleInput2" class="form-label">Confirm Password</label>
          <input type="password" id="password2" class="form-control" name="password2" minxlenght="8" required>
          <p class="text-danger" id="errorePassword2"></p>
        </div>
      </div>


      <div class="mt-5 mb-5 p-4 container border border-2 rounded pricipalContainer" >

        <!-- Spazio vuoto -->
        <div class="mb-2">
        </div>

        <!-- Dati Personali -->
        <div class="container p-0 m-0 mb-4 mt-2 text-center" >
          <label for="basic-url" class="form-label fw-bold ">Dati Personali</label>
        </div>

        <!-- Text-Nome , variabile = "name" -->
        <div class="mb-3">
          <label for="formGroupExampleInput2" class="form-label">Nome</label>
          <input type="text" class="form-control" id="name" name="name"  minlength="3" maxlength="14" required>
          <p class="text-danger" id="erroreName"></p>
        </div>

        <!-- Text-Cognome , variabile = "surname" -->
        <div class="mb-3">
          <label for="formGroupExampleInput2" class="form-label">Cognome</label>
          <input type="text" class="form-control" id="surname" name="surname" minlength="3" maxlength="14" required>
          <p class="text-danger" id="erroreSurname"></p>
        </div>

        <!-- RadioButtons-Sesso , variabile = "gender(maschio or femmina)" -->
        <div class="mb-4 mt-4">
          <label for="formGroupExampleInput2" class="form-label">Sesso</label><br>
          <div class="form-check form-check-inline">
            <input name="gender" class="form-check-input" type="radio" id="male" value="m">
            <label class="form-check-label" for="inlineCheckbox1"> Maschio </label>
          </div>
          <div class="form-check form-check-inline">
            <input name="gender" class="form-check-input" type="radio" id="female" value="f">
            <label class="form-check-label" for="inlineCheckbox2"> Femmina </label>
          </div>
          <p class="text-danger" id="erroreSesso"></p>
        </div>

        <!-- Date-Data di nascita , variabile = "birthdate(aaaa-mm-gg)" -->
        <div  class="mb-3">
		  <label for="formGroupExampleInput2" class="form-label">Data di nascita : </label>
		  <input type="date" id="birthdate" class="form-control" name="birthdate" required>
		  <p class="text-danger" id="erroreBirthdate"></p>
		</div>

        <!-- Text-Paese di origine , variabile = "country" -->
        <div class="mb-3">
          <label for="formGroupExampleInput2" class="form-label">Paese di origine</label>
          <div id="countryDiv"></div>
          <p class="text-danger" id="errorePaeseOrigine"></p>
        </div>

        <!-- Text-Codice Fiscale , variabile = "cf" -->
        <div class="mb-3">
          <label for="formGroupExampleInput2" class="form-label">Codice Fiscale</label>
          <input type="text" class="form-control" id="cf" name="cf"  minlength="16" maxlength="16" required>
          <p class="text-danger" id="erroreCF"></p>
        </div>
        
        <!-- Text-Conto Corrente , variabile = "numCC" -->
        <div class="mb-3">
          <label for="formGroupExampleInput2" class="form-label">Conto Corrente</label>
          <input type="text" class="form-control" id="numCC" name="numCC" minlength="27" maxlength="27" required>
          <p class="text-danger" id="erroreCC"></p>
        </div>

        <!-- Sumbit Button -->
        <div class="mb-2 mt-5">
          <div class="row align-items-start">
            <div class="col">
            </div>
            <div class="col">
            </div>
            <div class="col">
              <input type="button" id="iscrizioneFormatoreButton" class="btn btn-outline-info" style="align:right;" value="Iscriviti a FormAct">
            </div>
          </div>
        </div>

      </div>
	</form>
	<!-- Fine Form -->
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>