<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

        <!-- title -->
        <title>Cart</title>

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
                            <h1>Cart</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->


        <!-- cart -->

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
            </div>
        </c:if>


        <div class="cart-section mt-150 mb-150">
            <div class="container">
                <div class="row">
                    <c:set var="o" value="${sessionScope.cart}" />
                    <c:set var="tt" value="0" />
                    <div class="col-lg-8 col-md-12">
                        <div class="cart-table-wrap">
                            <table class="cart-table">
                                <thead class="cart-table-head">
                                    <tr class="table-head-row">
                                        <th>No</th>
                                        <th class="product-image">Product Image</th>
                                        <th class="product-name">Name</th>
                                        <th class="product-price">Price</th>
                                        <th class="product-quantity">Quantity</th>
                                        <th>Total</th>
                                        <th>Remove</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${o.items}" var="i">
                                        <c:set var="tt" value="${tt+1}" />
                                        <tr class="table-body-row">
                                            <td>${tt}</td>
                                            <td>
                                                <img src="${i.product.image_url}" alt="${i.product.name}" width="100" height="100"/>
                                            </td>
                                            <td class="product-name">${i.product.name}</td>
                                            <td class="product-price">
                                                $<fmt:formatNumber value="${i.product.price}" type="number" minFractionDigits="1" maxFractionDigits="2"/>
                                            </td>
                                            <td class="product-quantity">
                                                <button><a href="process?num=-1&id=${i.product.product_id}">-</a></button>
                                                ${i.quantity}
                                                <button><a href="process?num=1&id=${i.product.product_id}">+</a></button>
                                            </td>
                                            <td>
                                                $<fmt:formatNumber value="${i.price * i.quantity}" type="number" minFractionDigits="1" maxFractionDigits="2"/>
                                            </td>
                                            <td style="text-align: center">
                                                <form action="process" method="post">
                                                    <input type="hidden" name="id" value="${i.product.product_id}">
                                                    <input type="submit" value="Return item"/>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="col-lg-4">
                        <div class="total-section">
                            <table class="total-table">
                                <thead class="total-table-head">
                                    <tr class="table-total-row">
                                        <th>Total</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                <c:if test="${not empty o.items}">
                                    <tbody>
                                        <tr class="total-data">
                                            <td><strong>Subtotal: </strong></td>
                                            <td>$${o.totalMoney}</td>
                                        </tr>
                                        <tr class="total-data">
                                            <td><strong>Shipping: </strong></td>
                                            <c:if test="${o.totalMoney < 75}">
                                                <td>$5.0</td>
                                            </c:if>
                                            <c:if test="${o.totalMoney >= 75}">
                                                <td>$0.0</td>    
                                            </c:if>
                                        </tr>
                                        <tr class="total-data">
                                            <td><strong>Total: </strong></td>
                                            <c:if test="${o.totalMoney < 75}">
                                                <td>$${o.totalMoney + 5}</td> 
                                            </c:if>
                                            <c:if test="${o.totalMoney >= 75}">
                                                <td>$${o.totalMoney}</td>    
                                            </c:if>

                                        </tr>
                                    </tbody>
                                </c:if>
                            </table>
                            <c:if test="${not empty o.items}">
                                <div class="cart-buttons mt-3">
                                    <a href="checkout.jsp" class="boxed-btn black">Check Out</a>
                                    <div style="margin-top: 10px; color: red; text-align: center;">
                                        <%= request.getAttribute("ErrorMess") != null ? request.getAttribute("ErrorMess") : "" %>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>

                </div>
            </div>
        </div>


        <!-- end cart -->



        <!-- footer -->
        <div class="footer-area">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-box about-widget">
                            <h2 class="widget-title">About us</h2>
                            <p>Ut enim ad minim veniam perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae.</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-box get-in-touch">
                            <h2 class="widget-title">Get in Touch</h2>
                            <ul>
                                <li>34/8, East Hukupara, Gifirtok, Sadan.</li>
                                <li>support@fruitkha.com</li>
                                <li>+00 111 222 3333</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-box pages">
                            <h2 class="widget-title">Pages</h2>
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li><a href="about.html">About</a></li>
                                <li><a href="services.html">Shop</a></li>
                                <li><a href="news.html">News</a></li>
                                <li><a href="contact.html">Contact</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-box subscribe">
                            <h2 class="widget-title">Subscribe</h2>
                            <p>Subscribe to our mailing list to get the latest updates.</p>
                            <form action="index.html">
                                <input type="email" placeholder="Email">
                                <button type="submit"><i class="fas fa-paper-plane"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end footer -->

        <!-- copyright -->
        <div class="copyright">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-12">
                        <p>Copyrights &copy; 2019 - <a href="https://imransdesign.com/">Imran Hossain</a>,  All Rights Reserved.<br>
                            Distributed By - <a href="https://themewagon.com/">Themewagon</a>
                        </p>
                    </div>
                    <div class="col-lg-6 text-right col-md-12">
                        <div class="social-icons">
                            <ul>
                                <li><a href="#" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
                                <li><a href="#" target="_blank"><i class="fab fa-twitter"></i></a></li>
                                <li><a href="#" target="_blank"><i class="fab fa-instagram"></i></a></li>
                                <li><a href="#" target="_blank"><i class="fab fa-linkedin"></i></a></li>
                                <li><a href="#" target="_blank"><i class="fab fa-dribbble"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end copyright -->

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
