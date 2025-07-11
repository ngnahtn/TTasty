<%-- 
    Document   : verifyEmail
    Created on : Jun 17, 2025, 2:36:25 PM
    Author     : tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>

        <!<!-- user -->
        <link rel="stylesheet" href="assets/css/user.css"/>  
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
        <!-- end search arewa -->

        <!-- breadcrumb-section -->
        <div class="breadcrumb-section breadcrumb-bg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2 text-center">
                        <div class="breadcrumb-text"> 
                            <h1>User</h1> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->

        <!-- Form OTP -->
        <div>
            <form action="verify" method="post" class="login-form">
                <h3 style="color: #4CAF50; text-align: center;">🔒 Email Verification</h3>

                <p style="color: #555; text-align: center; font-size: 14px; margin-bottom: 20px;">
                    We have sent you a one-time password (OTP) to verify your email.
                </p>

                <input type="text" name="OTP" placeholder="Enter your OTP" required 
                       style="width: 100%; padding: 10px; margin-bottom: 15px; border-radius: 4px; border: 1px solid #ccc;" />

                <button type="submit"
                        style="width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; font-size: 16px;">
                    Submit
                </button>
                <div style="text-align: center">
                    <a style="color: green"><%= request.getAttribute("successOTP") != null ? request.getAttribute("successOTP") : "" %></a>
                    <a style="color: red"><%= request.getAttribute("errorOTP") != null ? request.getAttribute("errorOTP") : "" %></a>
                </div>
                <button type="button" onclick="resendOTP()" 
                        style="margin-top: 10px; background: none; border: none; color: #007BFF; font-size: 14px; cursor: pointer;">
                    🔄 Resend OTP
                </button>
                
                <!-- noti resend OTP -->
                <%
                    String newOTPMsg = (String) session.getAttribute("newOTP");
                    if (newOTPMsg != null) {
                %>
                    <div style="text-align: center; color: green"><%= newOTPMsg %></div>
                <%
                        session.removeAttribute("newOTP"); // remove để tránh hiện lại
                    }
                %>
                
            </form>
        </div>

        <script>
            function resendOTP() {
                // Gửi yêu cầu AJAX hoặc chuyển hướng đến servlet xử lý gửi lại OTP
                window.location.href = "resendOTP";
            }
        </script>
        <br>

        <!-- footer -->
        <div class="footer-area">
            <div class="container">
                <div class="row justify-content-center text-center">
                    <!-- About us -->
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="footer-box about-widget" >
                            <h2 class="widget-title" >About us</h2>
                            <p style="text-align: left">In order to truly understand, we must discover where all these errors come from — the faults that lead to blame and pain — to open up the whole truth, and to see its very nature.</p>
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