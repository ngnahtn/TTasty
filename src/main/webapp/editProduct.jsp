<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product</title>

        <!-- user -->
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

        <style>
            .edit-form {
                max-width: 500px;
                margin: 50px auto;
                padding: 20px;
                background: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            
            .edit-form h3 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
            }
            
            .edit-form label {
                display: block;
                margin-bottom: 8px;
                color: #555;
            }
            
            .edit-form input {
                width: 100%;
                padding: 8px;
                margin-bottom: 20px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            
            .edit-form button {
                width: 100%;
                padding: 10px;
                background: #F28123;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }
            
            .edit-form button:hover {
                background: #e67416;
            }
            
            .error-message {
                color: red;
                margin-bottom: 15px;
                text-align: center;
            }
            
            .preview-image {
                max-width: 200px;
                margin: 10px 0;
                border-radius: 4px;
            }
        </style>
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

        <!-- breadcrumb-section -->
        <div class="breadcrumb-section breadcrumb-bg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2 text-center">
                        <div class="breadcrumb-text">
                            <h1>Edit Product</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->

        <!-- Edit Product Form -->
        <div class="container">
            <form action="editAndDeleteProduct" method="post" class="edit-form">
                <h3>Edit Product Information</h3>
                
                <c:if test="${not empty ErrorMess}">
                    <div class="error-message">${ErrorMess}</div>
                </c:if>

                <%
                    // Lấy product_id từ parameter
                    String id = request.getParameter("id");
                    dao.ProductDAO productDAO = new dao.ProductDAO();
                    model.Product product = productDAO.getProductById(Integer.parseInt(id));
                    
                    if (product != null) {
                        request.setAttribute("product", product);
                    }
                %>

                <input type="hidden" name="oldID" value="${product.product_id}" />
                
                <label for="name">Product Name:</label>
                <input type="text" name="name" value="${product.name}" required />
                
                <label for="price">Price:</label>
                <input type="number" name="price" value="${product.price}" step="0.01" required />
                
                <label for="unit">Unit:</label>
                <input type="text" name="unit" value="${product.unit}" required />
                
                <label for="quantity">Quantity:</label>
                <input type="number" name="quantity" value="${product.quantity}" required />
                
                <label for="image_url">Image URL:</label>
                <input type="text" name="image_url" value="${product.image_url}" required />
                
                <img src="${product.image_url}" alt="Product Preview" class="preview-image" />
                
                <button type="submit">Update Product</button>
            </form>
        </div>

        <!-- footer -->
        <div class="footer-area">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-6">
                        <div class="footer-box about-widget">
                            <h2 class="widget-title">About us</h2>
                            <p>We are a team passionate about delivering fresh and quality fruits to your doorstep.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="footer-box get-in-touch">
                            <h2 class="widget-title">Get in Touch</h2>
                            <ul>
                                <li>Ha Noi, Long Bien</li>
                                <li>TTasty@gmail.com</li>
                                <li>+00 111 222 3333</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="footer-box pages">
                            <h2 class="widget-title">Pages</h2>
                            <ul>
                                <li><a href="index.jsp">Home</a></li>
                                <li><a href="about.jsp">About</a></li>
                                <li><a href="shop.jsp">Shop</a></li>
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