<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FormAct Registrazione</title>
</head>
<body>
	<!-- Divisione dedicata al form di registrazione di un utente che ha intenzione di iscriversi come studente -->
	<div>
		<form action="${pageContext.request.contextPath}/CheckRegister" method="post">
			
			<!-- fieldset dedicato alle credenziali di accesso -->
			<fieldset>
				<legend>Credenziali di accesso</legend>
				
				<!-- Text-Email , variabile ="email" -->
				<div>
					<label>Email : </label>
					<input type="text" id="email" name="email" minlength="6" required>
				</div>
				
				<!-- Text-Password , variabile="password" -->
				<div>
					<label>Password : </label>
					<input type="text" id="password" name="password" minlength="6" required>
				</div>
				
			</fieldset>
			
			
			<!-- fieldset dedicato ai dati personali -->
			<fieldset>
				<legend>Dati Personali</legend>
				
				<!-- Text-Nome , variabile = "name" -->
				<div>
					<label>Nome : </label>
					<input type="text" id="name" name="name" minlength="3" maxlength="14" required >
				</div>
				
				<!-- Text-Cognome , variabile = "surname" -->
				<div>
					<label>Cognome : </label>
					<input type="text" id="surname" name="surname" minlength="3" maxlength="16" required >
				</div>
			
				<!-- RadioButtons-Sesso , variabile = "gender(maschio or femmina)" -->
				<div>
					<label>Sesso : </label>
					<input type="radio" id="male" name="gender" value="maschio" required>
					<label> Maschio </label>
					<input type="radio" id="female" name="gender" value="femmina" required>
					<label> Femmina </label>
				</div>
				
				<!-- Date-Data di nascita , variabile = "birthdate(aaaa-mm-gg)" -->
				<div>
					<label>Data di nascita : </label>
					<input type="date" id="birthdate" name="birthdate" required>
				</div>
				
				<!-- Text-Paese di origine , variabile = "country" -->
				<div>
					<label>Paese di origine : </label>
					<input type="text" id="country" name="country" required>
				</div> 
				
				<!-- SubmitButton , bottone per effettuare la sottomissione del form -->
				<div>
					<input type="submit" value="Isciviti">
				</div> 
				
				
				
			</fieldset>
		</form>
	</div>
	
	
</body>
</html>