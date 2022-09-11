<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
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
    
    <title>Home page</title>
    
    <!-- bootstrap css -->
    <link rel="stylesheet" href="/formAct/view/css/bootstrap.min.css">
    <!-- style css -->
    <link rel="stylesheet" href="/formAct/view/css/unicoStile.css">
    <!-- Responsive-->
    <link rel="stylesheet" href="/formAct/view/css/responsive.css">
    
    <!-- favicon -->
    <link rel="icon" href="/formAct/view/immagini/favicon.png" type="image/gif" /> 
    <!-- Scrollbar Custom CSS -->
    <link rel="stylesheet" href="/formAct/view/css/jquery.mCustomScrollbar.min.css">
    <!-- Tweaks for older IEs-->
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
    <!-- owl stylesheets -->
    <link rel="stylesheet" href="/formAct/view/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/formAct/view/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
   
  </head>
  
  <body>
    <div class="header_section" style="background-color: #CFEDF0;">
      <%@include file="/view/fragments/Header.jsp"%>
      <%@include file="/view/fragments/Banner.jsp"%>
    </div>
    
    <div class="health_section layout_padding">
      <div class="container">
        <h1 class="health_taital">I migliori esperti del settore</h1>
        <p class="health_text">Fatti guidare dai migliori esperti del settore per apprendere la tecnologia o la materia che vorresti imparare</p>
        <div class="health_section layout_padding">
          <div class="row">
            <div class="col-sm-6">
              <div class="image_main">
                <div class="main">
                  <p align="center"><font size="4" ><span style="color:orange;"><strong>Tecnologie informatiche più richieste</strong></span></font></p>
                  <img src="/formAct/view/immagini/tecnologie.png" alt="tecnologie" class="image" style="width:90%">
                </div>
              </div>
            </div>
            <div class="col-sm-6">
              <div class="image_main_1">
                <div class="main">
                  <p align="center"><font size="4" ><span style="color:orange;"><strong>Lavori informatici più richiesti</strong></span></font></p>
                  <img src="/formAct/view/immagini/aziende.jpg" alt="aziende" class="image" style="width:79%">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div class="news_section layout_padding">
      <div class="container">
        <h1 class="health_taital">Servizi offerti da questo sito web</h1>
        <p class="health_text">Scegli il servizio che più fa per te</p>
        <div class="news_section_2 layout_padding">
          <div class="row">
            <div class="col-lg-4 col-sm-6">
              <div class="box_main" style="min-width: 100%; min-height: 100%; max-width: 100%; max-height: 100%;">
                <div class="icon_1">
                  <a href="/formAct/view/percorsoformativo/RicercaPercorsoFormativo.jsp"><img src="/formAct/view/immagini/libro.png"></a>
                </div>
                <h4 class="daily_text">Ricerca percorso formativo</h4>
              </div>
            </div>
            <div class="col-lg-4 col-sm-6">
              <div class="box_main" style="min-width: 100%; min-height: 100%; max-width: 100%; max-height: 100%;">
                <div class="icon_1"><a href="${pageContext.request.contextPath}/PercorsoFormativoServlet/RicercaPFService"><img src="/formAct/view/immagini/libri.png"></a></div>
                <h4 class="daily_text_1">Visualizza percorsi formativi</h4>                                                                                                
              </div>
            </div>
            <div class="col-lg-4 col-sm-6">
              <div class="box_main" style="min-width: 100%; min-height: 100%; max-width: 100%; max-height: 100%;">
                <div class="icon_1"><a href="${pageContext.request.contextPath}/PianoFormativoServlet/PianoService"><img src="/formAct/view/immagini/formatori.png"></a></div>
                <h4 class="daily_text_1">Piano formativo personalizzato</h4>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <%@include file="/view/fragments/Footer.jsp"%>
    
    
    
    
    
    
    
    
    
    
    <script src="/formAct/view/js/jquery.min.js"></script>
    <script src="/formAct/view/js/popper.min.js"></script>
    <script src="/formAct/view/js/bootstrap.bundle.min.js"></script>
    <script src="/formAct/view/js/jquery-3.0.0.min.js"></script>
    <script src="/formAct/view/js/plugin.js"></script>
    <!-- sidebar -->
    <script src="/formAct/view/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="/formAct/view/js/custom.js"></script>
    <!-- javascript -->
    <script src="/formAct/view/js/owl.carousel.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
  </body>
</html>









