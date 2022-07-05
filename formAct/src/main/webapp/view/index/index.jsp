<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.entity.*,  model.dao.* , java.util.*"%>
    

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Elegant Template By W3 Template</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="style.css"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.min.css"/>
<link rel="stylesheet" type="text/css" href="css/owl.carousel.min.css"/>
<link rel="stylesheet" type="text/css" href="css/owl.theme.default.min.css"/>

<!-- Font Google -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<%@include file="/view/fragments/Header.jsp"%>


 

<!-- About -->

<div class="about-us section-padding" data-scroll-index='1'>
  <div class="container">
    <div class="row">
      <div class="col-md-12 section-title text-center">
        <h3>Noi siamo i migliori nel nostro lavoro</h3>
        <p>Imparare l'informatica può essere un buon mezzo per trovare un nuovo lavoro che è in costante espansione. Scopri come fare con FormAct</p>
        <span class="section-title-line"></span> </div>
      <div class="col-md-6 mb-50">
        <div class="section-info">
          <div class="sub-title-paragraph">
            <h4>Tu puoi imparare il mondo informatico.</h4>
            <h5>Vogliamo cercare di trasmettere la nostra passione a tutti compreso chi la passione nell'informatica già la presenta</h5>
            <p>Per farlo noi creatori di questo sito abbiamo messo a disposizione una grandissima quantità di percorsi formativi gestiti dai nostri migliori formatori d'eccellenza. Percorsi formativi in qualsiasi ambito informatico dal più innovativo al più obsoleto.</p>
          </div>
          <a href="#" class="anchor-btn">Scopri di più! <i class="fas fa-arrow-right pd-lt-10"></i></a> </div>
      </div>
      <div class="col-md-6 mb-50">
        <div class="section-img"> <img src="images/about.jpg" alt="" class="img-responsive"/> </div>
      </div>
    </div>
  </div>
</div>

<!-- End About --> 

<!-- Services -->
<div class="services section-padding bg-grey" data-scroll-index='2'>
  <div class="container">
    <div class="row">
      <div class="col-md-12 section-title text-center">
        <h3>Noi siamo migliori in questo</h3>
        <p>Vestibulum elementum dui tempus dolor gravida, vel mattis erat fermentum.</p>
        <span class="section-title-line"></span> </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 mb-30">
        <div class="service-box bg-white text-center">
          <div class="icon"> <i class="fas fa-chart-line"></i> </div>
          <div class="icon-text">
            <h4 class="title-box">Chart Line</h4>
            <p>Sed malesuada, est eget condimentum iaculis, nisi ex facilisis metus.</p>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 mb-30">
        <div class="service-box bg-white text-center">
          <div class="icon"> <i class="fas fa-bullhorn "></i> </div>
          <div class="icon-text">
            <h4 class="title-box">Quick Anouncement</h4>
            <p>Sed malesuada, est eget condimentum iaculis, nisi ex facilisis metus.</p>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 mb-30">
        <div class="service-box bg-white text-center">
          <div class="icon"> <i class="fas fa-map-marked"></i> </div>
          <div class="icon-text">
            <h4 class="title-box">Mark Location</h4>
            <p>Sed malesuada, est eget condimentum iaculis, nisi ex facilisis metus.</p>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 mb-30">
        <div class="service-box bg-white text-center">
          <div class="icon"> <i class="fas fa-bug"></i> </div>
          <div class="icon-text">
            <h4 class="title-box">Bug Solution</h4>
            <p>Sed malesuada, est eget condimentum iaculis, nisi ex facilisis metus.</p>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 mb-30">
        <div class="service-box bg-white text-center">
          <div class="icon"> <i class="fas fa-comments"></i> </div>
          <div class="icon-text">
            <h4 class="title-box">Fast Communication</h4>
            <p>Sed malesuada, est eget condimentum iaculis, nisi ex facilisis metus.</p>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 mb-30">
        <div class="service-box bg-white text-center">
          <div class="icon"> <i class="fas fa-paint-brush"></i> </div>
          <div class="icon-text">
            <h4 class="title-box">Clean Design</h4>
            <p>Sed malesuada, est eget condimentum iaculis, nisi ex facilisis metus.</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- End Services --> 


<!-- Testimonials -->
<div class="testimonials">
  <div class="testimonials-overlay section-padding">
    <div class="container">
      <div class="row">
        <div class="col-md-10 offset-md-1">
          <div class="owl-carousel owl-theme">
            <div class="testimonial-item text-center">
              <div class="icon"> <i class="fas fa-comments"></i> </div>
              <p class="m-auto">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
              <div class="testimonial-author text-center">
                <h5>Rup Jakhar</h5>
                <h6>Web Desinger</h6>
              </div>
            </div>
            <div class="testimonial-item text-center">
              <div class="icon"> <i class="fas fa-comments"></i> </div>
              <p class="m-auto">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
              <div class="testimonial-author text-center">
                <h5>Yogesh Singh</h5>
                <h6>Web Desinger</h6>
              </div>
            </div>
            <div class="testimonial-item text-center">
              <div class="icon"> <i class="fas fa-comments"></i> </div>
              <p class="m-auto">Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
              <div class="testimonial-author text-center">
                <h5>Vivek Singh</h5>
                <h6>Web Desinger</h6>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- End Testimonials --> 

<!-- Contact -->
<div class="contact section-padding" data-scroll-index='4'>
  <div class="container">
    <div class="row">
      <div class="col-md-12 section-title text-center">
        <h3>Contact Us For More</h3>
        <p>Vestibulum elementum dui tempus dolor gravida, vel mattis erat fermentum.</p>
        <span class="section-title-line"></span> </div>
      <div class="col-lg-5 col-md-4">
        <div class="part-info">
          <div class="info-box">
            <div class="icon"> <i class="fas fa-phone"></i> </div>
            <div class="content">
              <h4>Phone :</h4>
              <p>0123456789</p>
            </div>
          </div>
          <div class="info-box">
            <div class="icon"> <i class="fas fa-map-marker-alt"></i> </div>
            <div class="content">
              <h4>Address :</h4>
              <p>New Delhi, India</p>
            </div>
          </div>
          <div class="info-box">
            <div class="icon"> <i class="fas fa-envelope"></i> </div>
            <div class="content">
              <h4>Mail :</h4>
              <p><a href="#">info@123.com</a></p>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-7 col-md-8">
        <div class="contact-form">
          <form class='form' id='contact-form' method='post' data-toggle='validator'>
            <input type='hidden' name='form-name' value='contact-form' />
            <div class="messages"></div>
            <div class="controls">
              <div class="row">
                <div class="col-lg-6">
                  <div class="form-group">
                    <input id="form_name" type="text" name="name" placeholder="Name *" required data-error="name is required.">
                    <div class="help-block with-errors"></div>
                  </div>
                </div>
                <div class="col-lg-6">
                  <div class="form-group">
                    <input id="form_email" type="email" name="email" placeholder="Email *" required data-error="Valid email is required.">
                    <div class="help-block with-errors"></div>
                  </div>
                </div>
                <div class="col-lg-12">
                  <div class="form-group">
                    <input id="form_subject" type="text" name="subject" placeholder="Subject">
                  </div>
                </div>
                <div class="col-lg-12 form-group">
                  <textarea id="form_message" name="message" class="form-control" placeholder=" Type Your Message " rows="4" required data-error="Please,leave us a message."></textarea>
                  <div class="help-block with-errors"></div>
                </div>
                <div class="col-lg-12 text-center">
                  <button class="bttn">Send Message</button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- End Contact -->
<footer class="footer-copy">
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-12">
        <p>2018 &copy; Elegant. Website Designed by <a href="http://w3Template.com" target="_blank" rel="dofollow">W3 Template</a></p>
      </div>
    </div>
  </div>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script> 
<!-- owl carousel js --> 
<script src="js/owl.carousel.min.js"></script> 
<!-- magnific-popup --> 
<script src="js/jquery.fancybox.min.js"></script> 

<!-- scrollIt js --> 
<script src="js/scrollIt.min.js"></script> 

<!-- Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- isotope.pkgd.min js --> 
<script src="js/isotope.pkgd.min.js"></script> 
<script>
  $(window).on("scroll",function () {

      var bodyScroll = $(window).scrollTop(),
          navbar = $(".navbar");

      if(bodyScroll > 130){

          navbar.addClass("nav-scroll");
          $('.navbar-logo img').attr('src','images/logo-black.png');


      }else{

          navbar.removeClass("nav-scroll");
          $('.navbar-logo img').attr('src','images/logo-white.png');

      }

  });

  $(window).on("load",function (){



var bodyScroll = $(window).scrollTop(),
navbar = $(".navbar");

if(bodyScroll > 130){

navbar.addClass("nav-scroll");
$('.navbar-logo img').attr('src','images/logo-black.png');


}else{

navbar.removeClass("nav-scroll");
$('.navbar-logo img').attr('src','images/logo-white.png');

}

/* smooth scroll
  -------------------------------------------------------*/
  $.scrollIt({

easing: 'swing',      // the easing function for animation
scrollTime: 900,       // how long (in ms) the animation takes
activeClass: 'active', // class given to the active nav element
onPageChange: null,    // function(pageIndex) that is called when page is changed
topOffset: -63
});

/* isotope
-------------------------------------------------------*/
var $gallery = $('.gallery').isotope({});
$('.gallery').isotope({

    // options
    itemSelector: '.item-img',
    transitionDuration: '0.5s',

});


$(".gallery .single-image").fancybox({
  'transitionIn'  : 'elastic',
  'transitionOut' : 'elastic',
  'speedIn'   : 600,
  'speedOut'    : 200,
  'overlayShow' : false
});


/* filter items on button click
-------------------------------------------------------*/
$('.filtering').on( 'click', 'button', function() {

    var filterValue = $(this).attr('data-filter');

    $gallery.isotope({ filter: filterValue });

    });

$('.filtering').on( 'click', 'button', function() {

    $(this).addClass('active').siblings().removeClass('active');

});

})

$(function () {
$( ".cover-bg" ).each(function() {
    var attr = $(this).attr('data-image-src');

    if (typeof attr !== typeof undefined && attr !== false) {
      $(this).css('background-image', 'url('+attr+')');
    }

  });

  /* sections background color from data background
  -------------------------------------------------------*/
  $("[data-background-color]").each(function() {
      $(this).css("background-color", $(this).attr("data-background-color")  );
  });


/* Owl Caroursel testimonial
  -------------------------------------------------------*/
  
  $('.testimonials .owl-carousel').owlCarousel({
    loop:true,
    autoplay:true,
    items:1,
    margin:30,
    dots: true,
    nav: false,
    
  });

});

</script>
</body>
</html>
