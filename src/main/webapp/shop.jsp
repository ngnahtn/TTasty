<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ page import="java.util.List" %>
        <%@ page import="model.Product" %>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Bootstrap4 Shop Template">

        <!-- title -->
        <title>TTasty - Shop</title>

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
        <!-- reset-button -->
        <link rel="stylesheet" href="assets/css/reset.css"/>
        <!-- style header -->
        <link rel="stylesheet" href="assets/css/headerIconStyle.css"/>
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
                            <p>Fresh and Organic</p>
                            <h1>Shop</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->


        <!-- products -->
        <div class="product-section mt-150 mb-150">
            <div class="container">
                <div class="row product-lists">
                    <c:forEach var="p" items="${listProduct}">
                        <div class="col-lg-4 col-md-6 text-center">
                            <div class="single-product-item">
                                <!-- FORM ri�ng cho m?i s?n ph?m -->
                                <form action="buy" method="get">
                                    <div class="product-image">
                                        <a href="single-product.jsp?id=${p.product_id}">
                                            <img src="${p.image_url}" alt="${p.name}" style="height: 250px; object-fit: cover;">
                                        </a>
                                    </div>
                                    <h3>${p.name}</h3>
                                    <p class="product-price"><span>Per ${p.unit}</span> ${p.price} $</p>
                                    <p class="product-price">Stock ${p.quantity}</p>

                                    <input type="number" name="num" value="1" style="width: 60px; text-align: center;" />
                                    <input type="hidden" name="id" value="${p.product_id}" />

                                    <a class="cart-btn">
                                        <button type="submit" class="btn-reset cart-btn">
                                            <i class="fas fa-shopping-cart"></i> Add to Cart
                                        </button>
                                    </a>
                                </form>

                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- end products -->


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
