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
  	
  	<link href="autenticazioneStyle.css" rel="stylesheet">
    <title>FormAct Login Page</title>
  </head>
  <body>
	
	<%@include file="/view/fragments/Header.jsp" %>
	
    <!-- Inizio Form  -->
    <form class="" action="${pageContext.request.contextPath}/LoginServlet" method="post">
    
      <!-- Form Container-->
      <div class="mt-5 mb-5 p-4 pb-2 container border-secondary border-2 rounded pricipalContainer" >

        <!-- Label-Inserisci le credenziali di accesso -->
        <div class="container p-0 m-0 mb-4 mt-2 text-center" >
          <label for="basic-url" class="form-label fw-bold ">Inserisci le credenziali di accesso</label>
        </div>

        <!-- Spazio vuoto -->
        <div class="mb-2">
        </div>
		
		
		<!-- Condizione di Erroe "logError", visualizzazione errore -->
		<%
			if(request.getSession().getAttribute("logError")!= null && request.getSession().getAttribute("logError").equals("true")){
		%>
			<div>
				<p class="text-danger" ><ins><strong>Attenzione! Email o Password errati</strong></ins></p>
			</div>
		<%
				request.getSession().removeAttribute("logError");
			}
		%>
		
        <!-- Text-Email , variabile="email" -->
        <div class="mb-3">
          <label for="formGroupExampleInput2" class="form-label">Email</label>
          <input type="text" class="form-control" id="email" name="email" minlength="6" required>
        </div>

        <!-- Password-Password , variabile="password" -->
        <div class="mb-4">
          <label for="formGroupExampleInput2" class="form-label">Password</label>
          <input type="password" id="password" class="form-control" name="password" minlenght="8" required>
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
              <button type="submit" class="btn btn-outline-info" style="align:right;">Accedi</button>
            </div>
          </div>
        </div>
        
	</div>
   </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>