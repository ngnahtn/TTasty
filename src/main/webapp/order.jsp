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

        <!-- List Order -->
        <div class="mt-5 mb-5 d-flex justify-content-center">
            <div class="container shadow p-4 bg-white rounded" style="max-width: 1000px;">
                <h3 class="text-center mb-4">Order Management</h3>
                <table class="table table-bordered align-middle text-center">
                    <thead class="table-dark">
                        <tr>
                            <th>Product</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orders}">
                            <!-- Order Info -->
                            <tr style="background-color: #f0f0f0;">
                                <td colspan="5" class="text-start">
                                    <strong>Order ID:</strong> ${order.orderId} |
                                    <strong>Buyer:</strong> ${order.buyerName} (${order.username}) |
                                    <strong>Phone:</strong> ${order.phone} |
                                    <strong>Address:</strong> ${order.address} |
                                    <strong>Status:</strong> ${order.status} |
                                    <strong>Date:</strong> ${order.orderDate}
                                </td>
                            </tr>

                            <!-- Items -->
                            <c:set var="subTotal" value="0" />
                            <c:forEach var="item" items="${order.items}">
                                <tr>
                                    <td>${item.productName}</td>
                                    <td><img src="${item.imageUrl}" width="60" style="border-radius: 6px;"></td>
                                    <td>$${item.price}</td>
                                    <td>${item.quantity}</td>
                                    <td>
                                        $${item.total}
                                        <c:set var="subTotal" value="${subTotal + item.total}" />
                                    </td>
                                </tr>
                            </c:forEach>

                            <!-- Total & Shipping -->
                            <tr class="fw-bold">
                                <td colspan="4" class="text-end">Subtotal:</td>
                                <td>$${subTotal}</td>
                            </tr>

                            <c:choose>
                                <c:when test="${subTotal < 75}">
                                    <tr class="fw-bold">
                                        <td colspan="4" class="text-end">Shipping Fee (orders < $75):</td>
                                        <td>$5.00</td>
                                    </tr>
                                    <tr class="fw-bold table-success">
                                        <td colspan="4" class="text-end">Total Payment:</td>
                                        <td>$${subTotal + 5}</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr class="fw-bold table-success">
                                        <td colspan="4" class="text-end">Total Payment:</td>
                                        <td>$${subTotal}</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>

                            <tr>
                                <td colspan="5" class="text-end">
                                    <!-- Nút Accept chỉ hiện nếu đang Pending -->
                                    <c:if test="${order.status == 'Pending'}">
                                        <form action="updateOrderStatus" method="post" style="display: inline-block; margin-right: 8px;">
                                            <input type="hidden" name="orderId" value="${order.orderId}" />
                                            <input type="hidden" name="newStatus" value="Accepted" />
                                            <button type="submit" class="btn btn-success btn-sm"
                                                    onclick="return confirm('Are you sure to accept this order?');">
                                                Accept
                                            </button>
                                        </form>
                                    </c:if>

                                    <!-- Nút Reject hiện nếu là Pending hoặc Accepted -->
                                    <c:if test="${order.status == 'Pending' || order.status == 'Accepted'}">
                                        <form action="updateOrderStatus" method="get" style="display: inline-block;">
                                            <input type="hidden" name="orderId" value="${order.orderId}" />
                                            <button type="submit" class="btn btn-danger btn-sm"
                                                    onclick="return confirm('Are you sure to reject (delete) this order?');">
                                                Reject
                                            </button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>

                            <!-- Spacer -->
                            <tr><td colspan="5"><hr/></td></tr>
                                </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- End List Order -->





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

