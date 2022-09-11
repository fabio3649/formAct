<%@ page 
    language="java" 
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>

<!DOCTYPE html>

<html>
  <head>
    <meta charset="UTF-8">
    <title>Banner</title>
  </head>
  
  <body>
    
    <div id="main_slider" class="carousel slide" data-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <div class="banner_section">
            <div class="container">
              <div class="row">
                <div class="col-md-6">
                  <h1 class="banner_taital">Corsi<br><span style="color: #151515;"> informatica</span></h1>
                  <p class="banner_text">Corsi sull'informatica.</p>
                  <div class="btn_main">
                    <div class="more_bt"><a href="#">Contattaci</a></div>
                    <div class="contact_bt"><a href="#">Percorsi formativi</a></div>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="materie"><img src="/formAct/view/immagini/informatica.png"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <div class="banner_section">
            <div class="container">
              <div class="row">
                <div class="col-md-6">
                  <h1 class="banner_taital">Corsi<br><span style="color: #151515;"> matematica</span></h1>
                  <p class="banner_text">Corsi sulla matematica.</p>
                  <div class="btn_main">
                    <div class="more_bt"><a href="#">Contattaci</a></div>
                    <div class="contact_bt"><a href="#">Percorsi formativi</a></div>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="materie"><img src="/formAct/view/immagini/matematica.png"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#main_slider" role="button" data-slide="prev">
        <i class="fa fa-long-arrow-left" style="font-size:24px; padding-top: 4px;"></i>
      </a>
      <a class="carousel-control-next" href="#main_slider" role="button" data-slide="next">
        <i class="fa fa-long-arrow-right" style="font-size:24px; padding-top: 4px;"></i>
      </a>
    </div>
    
  </body>
</html>