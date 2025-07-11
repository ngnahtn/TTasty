<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<html lang="en">

    <head>

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

        <!-- title -->
        <title>Check Out</title>

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
        <!-- style button -->
        <link rel="stylesheet" href="assets/css/button.css"/>
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
                            <h1>Check Out Product</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->

        <!-- check out section -->
        <c:if test="${empty sessionScope.cart.items}">
            <div style="text-align: center; padding: 40px 20px;">
                <i class="fas fa-shopping-cart" style="font-size: 50px; margin-bottom: 10px;"></i>
                <div style="color: #ff4d4d; font-weight: bold; font-size: 20px;">
                    Your cart is empty!
                </div>
                <div style="font-size: 16px; margin-top: 5px; color: #555;">
                    Browse our shop and add some delicious products to your cart.
                </div>
                <div style="margin-top: 20px;">
                    <a href="shop" class="btn btn-success" style="padding: 10px 20px; font-size: 16px;">
                        <i class="fas fa-arrow-left"></i> Go to Shop
                    </a>
                </div>
                <br>
                <div style="text-align: center">
                    <c:if test="${not empty sessionScope.SuccessMess}">
                        <p style="color: green;" class="success-message">${sessionScope.SuccessMess}</p>
                        <c:remove var="SuccessMess" scope="session" />
                    </c:if>
                </div>
            </div>

        </c:if>

        <c:if test="${not empty sessionScope.cart.items}">
            <div class="checkout-section mt-150 mb-150">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="checkout-accordion-wrap">
                                <div class="accordion" id="accordionExample">
                                    <div class="card single-accordion">
                                        <div class="card-header" id="headingOne">
                                            <h5 class="mb-0">
                                                <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                    Billing Address
                                                </button>
                                            </h5>
                                        </div>

                                        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <div class="billing-address-form">
                                                    <form action="checkout" method="post">
                                                        <p><input type="text" placeholder="Name" name="name"></p>
                                                        <p><input type="text" placeholder="Address" name="address"></p>
                                                        <p><input type="text" placeholder="Phone" name="phone"></p>      

                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="col-lg-4">
                            <div class="order-details-wrap">
                                <table class="order-details">
                                    <thead>
                                        <tr>
                                            <th>Your order Details</th>
                                            <th></th>
                                            <th>Price</th>
                                        </tr>
                                    </thead>
                                    <tbody class="order-details-body">
                                        <tr>
                                            <td>Product</td>
                                            <td>Amount</td>
                                            <td>Total</td>  
                                        </tr>

                                        <c:set var="o" value="${sessionScope.cart}"/>
                                        <c:forEach items="${o.items}" var="i">
                                            <tr>
                                                <td>${i.product.name}</td>
                                                <td>${i.quantity}</td>
                                                <td>$<fmt:formatNumber 
                                                        value="${i.price * i.quantity}" 
                                                        type="number" 
                                                        minFractionDigits="1" 
                                                        maxFractionDigits="10"/></td>

                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                    <tbody class="checkout-details">
                                        <tr>
                                            <td>Subtotal</td>
                                            <td></td>
                                            <td>$${0 + o.totalMoney}</td>
                                        </tr>
                                        <tr>
                                            <td>Shipping</td>
                                            <td></td>
                                            <c:if test="${o.totalMoney < 75}">
                                                <td>$5.0</td>
                                            </c:if>
                                            <c:if test="${o.totalMoney >= 75}">
                                                <td>$0.0</td>    
                                            </c:if>
                                        </tr>
                                        <tr>
                                            <td>Total</td>
                                            <td></td>

                                            <c:if test="${o.totalMoney < 75}">
                                                <td>$${o.totalMoney + 5}</td> 
                                            </c:if>
                                            <c:if test="${o.totalMoney >= 75}">
                                                <td>$${o.totalMoney}</td>    
                                            </c:if>
                                        </tr>
                                    </tbody>
                                </table><br>

                                <div class="place-order-row">
                                    <button type="submit" class="custom-orange-btn">
                                        <i class="fas fa-shopping-bag" style="margin-right: 8px;"></i> Place Order
                                    </button>

                                    <p class="error-message">
                                        <%= request.getAttribute("ErrorMess") != null ? request.getAttribute("ErrorMess") : "" %>

                                    </p>


                                </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <!-- end check out section -->

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