<%-- 
    Document   : manageProduct
    Created on : Jun 22, 2025, 8:53:44 PM
    Author     : tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                            <h1>Manage Products</h1> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->



        <div class="row">

            <!-- Form mamageProducts -->
            <div class="col-lg-6">
                <form action="manageProducts" method="post" class="login-form">
                    <h3>Add Products</h3>

                    <label for="nameProduct">Name of the product</label>
                    <input type="text" name="nameProduct"  />

                    <label for="price">Price</label>
                    <input type="number" name="price" step="0.01"  />

                    <label for="unit">Unit of product</label>
                    <input type="text"  name="unit" placeholder="kg, g, ..."  />

                    <label for="quantity">Quantity</label>
                    <input type="number" name="quantity" placeholder="Stock of the product" />

                    <label for="image_url">image url</label>
                    <input type="text" name="image_url" placeholder="https://..." />

                    <button type="submit">Add</button>
                    <div>
                        <a style="color: red"><%=request.getAttribute("ErrorMessage") != null ? request.getAttribute("ErrorMessage") : "" %></a>
                        <a style="color: green"><%=request.getAttribute("SuccessMessage") != null ? request.getAttribute("SuccessMessage") : "" %></a>
                    </div>
                </form>
            </div>

            <!-- Form List Products -->
            <div class="col-lg-6">
                <form action="manageProducts" method="get" class="login-form">
                    <h3>List of the Products</h3>
                    <table border="1" cellpadding="5" cellspacing="0">
                        <tr>
                
                            <th>Name</th>
                            <th>Price</th>
                            <th>Unit</th>
                            <th>Quantity</th>
                            <th>Image</th>
                            <th>Actions</th> <!-- Thêm cột hành động -->
                        </tr>

                        <c:forEach var="p" items="${dataProducts}">
                            <tr>
                             
                                <td>${p.name}</td>
                                <td>${p.price}</td>
                                <td>${p.unit}</td>
                                <td>${p.quantity}</td>
                                <td><img src="${p.image_url}" alt="image" width="100"/></td>
                                <td>
                                    <a href="editProduct.jsp?id=${p.product_id}">Edit</a> |
                                    <a href="editAndDeleteProduct?id=${p.product_id}" onclick="return confirm('Are you sure to delete this product?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty dataProducts}">
                            <tr>
                                <td colspan="7" style="color: red; text-align: center;">
                                    No products available or dataProducts not found!
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </form>
            </div>

        </div>
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

