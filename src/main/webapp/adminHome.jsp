<%-- 
    Document   : adminHome
    Created on : Jun 25, 2025, 10:50:06 AM
    Author     : tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page import="model.User" %> 
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

	<!-- title -->
	<title>Home</title>

	<!-- favicon -->
        <link rel="shortcut icon" type="image/png" href="assets/img/TTasty.png">
	<!-- google font -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
	<!-- fontawesome -->
	<link rel="stylesheet" href="assets/css/all.min.css">
	<!-- bootstrap -->
	<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
	<!-- owl carousel -->
	<link rel="stylesheet" href="assets/css/owl.carousel.css">
	<!-- magnific popup -->
	<link rel="stylesheet" href="assets/css/magnific-popup.css">
	<!-- animate css -->
	<link rel="stylesheet" href="assets/css/animate.css">
	<!-- mean menu css -->
	<link rel="stylesheet" href="assets/css/meanmenu.min.css">
	<!-- main style -->
	<link rel="stylesheet" href="assets/css/main.css">
	<!-- responsive -->
	<link rel="stylesheet" href="assets/css/responsive.css">

</head>
<body>
	
    <!--PreLoader-->
    <div class="loader">
        <div class="loader-inner">
            <div class="circle"></div>
        </div>
    </div>
    <!--PreLoader Ends-->

<!-- Include Header -->
<jsp:include page="/includes/header.jsp" />

<!-- search area -->
	<div class="search-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<span class="close-btn"><i class="fas fa-window-close"></i></span>
					<div class="search-bar">
						<div class="search-bar-tablecell">
							<h3>Search For:</h3>
							<input type="text" placeholder="Keywords">
							<button type="submit">Search <i class="fas fa-search"></i></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end search area -->

	<!-- hero area -->
	<div class="hero-area hero-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 offset-lg-1 text-center">
					<div class="hero-text">
						<div class="hero-text-tablecell">
                   
                                                        <p class="subtitle" style="text-align: left; padding-left: 400px;">Welcome</p>
							<h1><%=session.getAttribute("user")%> </h1>
		
                                      
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end hero area -->

	
  
	<!-- footer -->
        <div class="footer-area">
            <div class="container">
                <div class="row justify-content-center text-center">
                    <!-- About us -->
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="footer-box about-widget" >
                            <h2 class="widget-title" >About us</h2>
                            <p style="text-align: left">In order to truly understand, we must discover where all these errors come from ? the faults that lead to blame and pain ? to open up the whole truth, and to see its very nature.</p>
                        </div>
                    </div>

                    <!-- Get in Touch -->
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="footer-box get-in-touch">
                            <h2 class="widget-title">Get in Touch</h2>
                            <ul class="list-unstyled" style="text-align: left">
                                <li>Ha Noi , Long Bien</li>
                                <li>TTasty@gmail.com</li>
                                <li>+0344497444</li>
                            </ul>
                        </div>
                    </div>

                    <!-- Pages -->
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="footer-box pages">
                            <h2 class="widget-title">Pages</h2>
                            <ul class="list-unstyled" style="text-align: left">
                                <li><a href="index.jsp" class="text-decoration-none ">Home</a></li>
                                <li><a href="about.jsp" class="text-decoration-none ">About</a></li>
                                <li><a href="shop" class="text-decoration-none ">Shop</a></li>
                 
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- end footer -->
	
	<!-- jquery -->
	<script src="assets/js/jquery-1.11.3.min.js"></script>
	<!-- bootstrap -->
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<!-- count down -->
	<script src="assets/js/jquery.countdown.js"></script>
	<!-- isotope -->
	<script src="assets/js/jquery.isotope-3.0.6.min.js"></script>
	<!-- waypoints -->
	<script src="assets/js/waypoints.js"></script>
	<!-- owl carousel -->
	<script src="assets/js/owl.carousel.min.js"></script>
	<!-- magnific popup -->
	<script src="assets/js/jquery.magnific-popup.min.js"></script>
	<!-- mean menu -->
	<script src="assets/js/jquery.meanmenu.min.js"></script>
	<!-- sticker js -->
	<script src="assets/js/sticker.js"></script>
	<!-- main js -->
	<script src="assets/js/main.js"></script>

</body>
</html>
