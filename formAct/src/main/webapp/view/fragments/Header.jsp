<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%
		if(request.getSession().getAttribute("role")==null){
	
	%>
	
	
	<!-- Navbar Utente -->
	
		<nav class="navbar navbar-expand-lg navbar-light"">
	      <div class="container-fluid header">
	      
			<!-- Logo -->      
	        <a class="navbar-brand" href="/formAct/view/index/index.jsp"><img src="/formAct/view/fragments/logo.png" alt="" width="40" height="40"></a>
	        
	        <!-- Media Queries -->
	        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
	          <span class="navbar-toggler-icon"></span>
	        </button>
	        <div class="collapse navbar-collapse" id="navbarScroll">
	          <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
	            
	            <!-- Primo Link -->
	            <li class="nav-item">
	              <a class="nav-link active" aria-current="page" href="#">Home</a>
	            </li>
	            
	            <!-- Secondo Link -->
	            <li class="nav-item">
	              <a class="nav-link" href="#">Link</a>
	            </li>
	            
	            <!-- Profilo -->
	            <li class="nav-item dropdown">
	              <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	                Profilo
	              </a>
	              <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
	                <li><a class="dropdown-item" href="#">Action</a></li>
	                <li><a class="dropdown-item" href="#">Another action</a></li>
	                <li><hr class="dropdown-divider"></li>
	                <li><a class="dropdown-item" href="#">Something else here</a></li>
	              </ul>
	            </li>
	            <li class="nav-item">
	              <a class="nav-link disabled">Link</a>
	            </li>
	          </ul>
	          <form class="d-flex">
	            <input class="form-control me-2" type="search" placeholder="Cerca un Percorso Formativo..." aria-label="Search">
	            <button class="btn btn-primary" type="button">Cerca</button>
	          </form>
	        </div>
	      </div>
	    </nav>
	<% } %>    
	    
	<!-- Navbar Studente -->
	
	<% if(request.getSession().getAttribute("role")==null || request.getSession().getAttribute("role").equals("Studente")){ %>
	
	
	<% } %>
	<!-- Navbar Formatore -->
	
	 <% if(request.getSession().getAttribute("role")==null || request.getSession().getAttribute("role").equals("Formatore")){ %>
	
	
	<% } %>
    
	
</body>
</html>