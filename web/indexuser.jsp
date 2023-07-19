<%@page import="entity.Artiste"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Eventalk - Free Bootstrap 4 Template by Colorlib</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/animate.css">
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/magnific-popup.css">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/aos.css">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/ionicons.min.css">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/jquery.timepicker.css">

    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/flaticon.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/icomoon.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
  </head>
  <body style="background-image: url('<%= request.getContextPath() %>/assets/images/essai.jpg');">
    
	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="<%= request.getContextPath() %>/assets/index.html">Clinique<span>Madagascar</span></a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
                    <li class="nav-item active"><a href="<%= request.getContextPath() %>/indexuser" class="nav-link" style="color: #fff;"><btn class="btn btn-dark py-3 px-5">Home</btn></a></li>
                    <li class="nav-item active"><a href="<%= request.getContextPath() %>/facturage" class="nav-link" style="color: #fff;"><btn class="btn btn-dark py-3 px-5">Facturage</btn></a></li>
	          

	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->
    
   
    
    <section class="ftco-section services-section bg-light" style="margin-top: 30%;">
      <div class="container-fluid">
        <div class="row d-flex">
           
            <a href="<%= request.getContextPath() %>/patient/0" >
                
          <div class="col-md-2 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block">
                <btn class="btn btn-light py-3 px-5">
                    <h3 class="heading mb-3 " >Patient</h3>
               </btn>
            </div>   
          </div>
          </a>
            
          <a href="<%= request.getContextPath() %>/actes/0" >
          <div class="col-md-2 d-flex align-self-stretch ftco-animate">
              <btn class="btn btn-light py-3 px-5">
                <h3 class="heading mb-3">Creer un acte pour un patient</h3>
               </btn>
            </div>      
          </a>
           <a href="<%= request.getContextPath() %>/depenses/0" >
          <div class="col-md-2 d-flex align-self-stretch ftco-animate">
            
                <btn class="btn btn-light py-3 px-5">
                <h3 class="heading mb-3">Depenses</h3>
               </btn>
               
              
            </div>      
         
          </a> 
          <a href="<%= request.getContextPath() %>/depenses/multi" >
          <div class="col-md-2 d-flex align-self-stretch ftco-animate">
            
                <btn class="btn btn-light py-2 px-2">
                <h3 class="heading mb-3">Insertion depenses multiples</h3>
               </btn>
               
              
            </div>      
         
          </a> 
          <a href="<%= request.getContextPath() %>/depenses/csv" >
          <div class="col-md-2 d-flex align-self-stretch ftco-animate">
            
                <btn class="btn btn-light py-2 px-2">
                <h3 class="heading mb-3">CSV depenses</h3>
               </btn>
               
              
            </div>      
         
          </a> 
                    </div>

        </div>
      </div>
    </section>
     
    				

   
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="<%= request.getContextPath() %>/assets//js/jquery.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/jquery-migrate-3.0.1.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/popper.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/bootstrap.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/jquery.easing.1.3.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/jquery.waypoints.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/jquery.stellar.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/owl.carousel.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/jquery.magnific-popup.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/aos.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/jquery.animateNumber.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/bootstrap-datepicker.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/jquery.timepicker.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/scrollax.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets/https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="<%= request.getContextPath() %>/assets//js/google-map.js"></script>
  <script src="<%= request.getContextPath() %>/assets//js/main.js"></script>
    
  </body>
</html>