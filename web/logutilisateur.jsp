<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  
  <title>Skydash Admin</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets2/vendors/feather/feather.css">
  <link rel="stylesheet" href="<%= request.getContextPath()%>/assets2/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="<%= request.getContextPath()%>/assets2/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets2/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" type="<%= request.getContextPath() %>/text/css" href="<%= request.getContextPath() %>/assets2/js/select.dataTables.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets2/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets2/images/favicon.png" />
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
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth px-0">
          <div class="row w-100 mx-0">
            <div class="col-lg-4 mx-auto">
              <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                <div class="brand-logo">
                 <a class="navbar-brand" href="<%= request.getContextPath() %>/">Event<span>Madagascar. Employee Side</span></a>
                </div>
                <h4>Hello! Commencons a planifier vos evenements (Employee)</h4>
                <h6 class="font-weight-light">Se connecter pour continuer</h6>
                <form class="pt-3" action="<%= request.getContextPath() %>/connexutilisateur" method="GET">
                  <div class="form-group">
                    <input type="text" class="form-control form-control-lg" id="exampleInputEmail1" name="username" placeholder="Username">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" name="password" placeholder="Password">
                  </div>
                  <div class="mt-3">
                      <input class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" type="submit" value="SE CONNECTER"> 
                  </div>
                  <div class="mt-3">
                      <a href="<%= request.getContextPath() %>/" ><btn class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">ADMIN  SIDE </btn></a>
                  </div>
                  <div class="text-center mt-4 font-weight-light">
                    
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  <script src="<%= request.getContextPath() %>/assets2/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="<%= request.getContextPath() %>/assets2/vendors/chart.js/Chart.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets2/vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="<%= request.getContextPath() %>/assets2/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <script src="<%= request.getContextPath() %>/assets2/js/dataTables.select.min.js"></script>

  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="<%= request.getContextPath() %>/assets2/js/off-canvas.js"></script>
  <script src="<%= request.getContextPath() %>/assets2/js/hoverable-collapse.js"></script>
  <script src="<%= request.getContextPath() %>/assets2/js/template.js"></script>
  <script src="<%= request.getContextPath() %>/assets2/js/settings.js"></script>
  <script src="<%= request.getContextPath() %>/assets2/js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="<%= request.getContextPath() %>/assets2/js/dashboard.js"></script>
  <script src="<%= request.getContextPath() %>/assets2/js/Chart.roundedBarCharts.js"></script>
  <!-- End custom js for this page-->
</body>

</html>

