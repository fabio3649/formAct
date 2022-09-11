<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.ArrayList"  import="model.entity.*" import="model.dao.*"
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
    
    <title>Percorsi</title>
    
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
    <div class="header_section" style="background-color: #CFEDF0;">
      <%@include file="/view/fragments/Header.jsp"%>
    </div>
    
    <div class="wrapperTable">
      <div class="immagineProfilo" style="width: 100%;">
        <img src="/formAct/view/immagini/avatar-login.png" alt="immagineProfilo" style="width:5%; display: block; margin:0 auto;">
      </div>
      
      <br>
      
  	  <div class="text-center mt-4 name" style="display: block;">
  	    I tuoi percorsi formativi
  	  </div>
  	  
  	  <div class="contenuto">
<%
  	    CategoriaDao dao = new CategoriaDao();
	    CategoriaEntity cat = new CategoriaEntity();
	    
	    ArrayList<PercorsoFormativoEntity> list = (ArrayList<PercorsoFormativoEntity>) request.getAttribute("percorsiFormativi");
%>
        <table border="1">
          <tr>
            <th>Nome</th>
            <th>Categoria</th>
            <th>Azione</th>
          </tr>
          
<% 
          if (list != null && list.size() > 0) {
            for( int i=0 ; i<list.size(); i++) { 
%>
              <tr>
                <td>
                  <form action="${pageContext.request.contextPath}/GestionePFServlet/VisualizzaPercorsoService" method="post">
                    <input type="hidden" name="idPercorso" value="<%=list.get(i).getId()%>" >
                    <input style="border: none; background-color: transparent;" type="submit" name="id" value="<%=list.get(i).getNome() %>">
                  </form>
                </td>
<% 
                cat = (CategoriaEntity) dao.doRetrieveByKey(list.get(i).getCategoria()); 
%>
                <td> <%=cat.getNome() %> </td>
              
                <td>
                  <form action="${pageContext.request.contextPath}/GestionePFServlet/DeletePercorsoService" method="post">
                    <input type="hidden" name="idPercorso" value="<%=list.get(i).getId()%>">
                    <input style="border: none; background-color: transparent;" type="submit" name="elimina" value="Elimina"> 
                  </form>
                  <form action="/formAct/view/serviziutente/GetValutazioniPercorso.jsp">
                    <input type="hidden" value="<%=list.get(i).getId()%>" name="idPercorso">
                    <input style="border: none; background-color: transparent;" type="submit" name="valutazioni" value="Valutazioni"> 
                  </form>
                </td>
              </tr>
<%
            }
          }
          else {
%>
            Nessun percorso formativo trovato.
<%
          }
%>
        </table>
  	  </div>
    </div>
  </body>

  <!-------------------- Footer -------------------->
  <%@include file="/view/fragments/Footer.jsp"%>
  
  </body>
</html>
 

