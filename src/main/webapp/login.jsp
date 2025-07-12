<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login - TTasty</title>
        <!-- favicon -->
        <link rel="shortcut icon" type="image/png" href="assets/img/TTasty.png">
        <!-- google font -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
        <!-- fontawesome -->
        <link rel="stylesheet" href="assets/css/all.min.css">
        <!-- bootstrap -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
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
        <!-- style header -->
        <link rel="stylesheet" href="assets/css/headerIconStyle.css" />

        <style>
            :root {
                --primary-color: #FF6B6B;
                --secondary-color: #4ECDC4;
                --dark-color: #2C3E50;
                --light-color: #F7F9FC;
            }

            body {
                min-height: 100vh;
                background: var(--light-color);
                font-family: 'SF Pro Display', -apple-system, BlinkMacSystemFont, sans-serif;
            }

            .login-container {
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 2rem;
                background: url('assets/img/products/strawberry.jpg') no-repeat center center;
                background-size: cover;
                position: relative;
            }

            .login-container::before {
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background: rgba(0, 0, 0, 0.5);
                backdrop-filter: blur(8px);
            }

            .login-card {
                width: 100%;
                max-width: 400px;
                background: rgba(255, 255, 255, 0.95);
                backdrop-filter: blur(20px);
                border-radius: 24px;
                padding: 2rem;
                position: relative;
                box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
                transform: translateY(0);
                transition: all 0.3s ease;
                overflow: hidden;
            }

            .login-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 30px 60px rgba(0, 0, 0, 0.12);
            }

            .login-header {
                text-align: center;
                margin-bottom: 2rem;
            }

            .login-header img {
                width: 60px;
                height: 60px;
                margin-bottom: 1rem;
                animation: float 6s ease-in-out infinite;
            }

            @keyframes float {
                0% {
                    transform: translateY(0px);
                }

                50% {
                    transform: translateY(-10px);
                }

                100% {
                    transform: translateY(0px);
                }
            }

            .login-header h3 {
                color: var(--dark-color);
                font-weight: 700;
                font-size: 1.5rem;
                margin-bottom: 0.5rem;
            }

            .login-header p {
                color: #6c757d;
                font-size: 0.9rem;
            }

            .form-group {
                margin-bottom: 1rem;
                position: relative;
                width: 100%;
            }

            .form-control {
                height: 45px;
                padding: 0.75rem 1rem;
                padding-right: 2.5rem;
                font-size: 0.9rem;
                border: 2px solid #eee;
                border-radius: 12px;
                transition: all 0.3s ease;
                background-color: white;
                width: 100%;
                box-sizing: border-box;
            }

            .form-control:focus {
                border-color: var(--primary-color);
                box-shadow: 0 0 0 4px rgba(255, 107, 107, 0.1);
            }

            .form-control::placeholder {
                color: #adb5bd;
                font-size: 0.9rem;
            }

            .password-toggle {
                position: absolute;
                right: 12px;
                top: 50%;
                transform: translateY(-50%);
                cursor: pointer;
                color: #6c757d;
                transition: color 0.3s ease;
                z-index: 2;
                background: transparent;
                border: none;
                padding: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                width: 24px;
                height: 24px;
            }

            .password-toggle:hover {
                color: var(--primary-color);
            }

            .form-options {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 1.5rem;
                font-size: 0.9rem;
            }

            .form-check {
                display: flex;
                align-items: center;
                gap: 0.5rem;
            }

            .form-check-input {
                width: 1rem;
                height: 1rem;
                border-radius: 6px;
                border: 2px solid #ddd;
            }

            .form-check-input:checked {
                background-color: var(--primary-color);
                border-color: var(--primary-color);
            }

            .forgot-password {
                color: var(--primary-color);
                text-decoration: none;
                font-weight: 500;
                transition: all 0.3s ease;
                font-size: 0.9rem;
            }

            .forgot-password:hover {
                color: #ff4f4f;
                text-decoration: underline;
            }

            .btn-submit {
                width: 100%;
                padding: 0.75rem;
                background: var(--primary-color);
                border: none;
                border-radius: 12px;
                color: white;
                font-weight: 600;
                font-size: 0.9rem;
                transition: all 0.3s ease;
                position: relative;
                overflow: hidden;
                height: 45px;
            }

            .btn-submit::before {
                content: '';
                position: absolute;
                top: 0;
                left: -100%;
                width: 100%;
                height: 100%;
                background: linear-gradient(120deg, transparent, rgba(255, 255, 255, 0.3), transparent);
                transition: all 0.6s;
            }

            .btn-submit:hover::before {
                left: 100%;
            }

            .btn-submit:hover {
                background: #ff4f4f;
                transform: translateY(-2px);
                box-shadow: 0 10px 20px rgba(255, 107, 107, 0.2);
            }

            .error-message {
                background-color: #FFE5E5;
                color: #D63301;
                padding: 12px;
                border-radius: 8px;
                margin-bottom: 20px;
                font-size: 0.9rem;
                display: flex;
                align-items: center;
                border-left: 4px solid #D63301;
            }

            .error-message i {
                margin-right: 8px;
                font-size: 1.1rem;
            }

            .tab-container {
                display: flex;
                margin-bottom: 2rem;
                border-bottom: 2px solid #eee;
            }

            .tab-item {
                flex: 1;
                text-align: center;
                padding: 1rem;
                color: #6c757d;
                font-weight: 600;
                cursor: pointer;
                transition: all 0.3s ease;
                position: relative;
            }

            .tab-item.active {
                color: var(--primary-color);
            }

            .tab-item::after {
                content: '';
                position: absolute;
                bottom: -2px;
                left: 0;
                width: 100%;
                height: 2px;
                background-color: var(--primary-color);
                transform: scaleX(0);
                transition: transform 0.3s ease;
            }

            .tab-item.active::after {
                transform: scaleX(1);
            }

            .form-container {
                position: relative;
                width: 100%;
                height: 100%;
            }

            .login-form,
            .register-form {
                transition: all 0.3s ease;
            }

            .register-form {
                display: none;
            }

            .error-message {
                color: #dc3545;
                font-size: 0.8rem;
                margin-top: 0.25rem;
                display: none;
            }

            .btn-loading {
                position: relative;
                pointer-events: none;
            }

            .btn-loading::after {
                content: '';
                width: 20px;
                height: 20px;
                border: 2px solid #fff;
                border-top-color: transparent;
                border-radius: 50%;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                animation: button-loading-spinner 1s linear infinite;
            }

            @keyframes button-loading-spinner {
                from {
                    transform: translate(-50%, -50%) rotate(0turn);
                }

                to {
                    transform: translate(-50%, -50%) rotate(1turn);
                }
            }

            @media (max-width: 576px) {
                .login-card {
                    padding: 1.5rem;
                }

                .login-header h3 {
                    font-size: 1.25rem;
                }
            }
            html, body {
                padding: 0;
                margin: 0;
                width: 100%;
                height: 100%;
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

        <!-- existing login form content -->
        <div class="login-container">
            <div class="login-card">
                <div class="login-header">
                    <img src="assets/img/TTasty.png" alt="TTasty Logo" class="rounded-circle">
                    <h3>Welcome Back!</h3>
                    <p>Please login to your account</p>
                </div>
                
                <%
                    String loginError = (String)request.getAttribute("Login");
                    if(loginError != null) {
                %>
                <div class="error-message">
                    <i class="fas fa-exclamation-circle"></i>
                    <%= loginError %>
                </div>
                <%
                    }
                %>

                <div class="tab-container">
                    <div class="tab-item active" data-form="login">Sign In</div>
                    <div class="tab-item" data-form="register">Sign Up</div>
                </div>

                <div class="form-container">
                    <!-- Login Form -->
                    <form action="login" method="post" id="loginForm" class="login-form">
                        <div class="form-group">
                            <input type="text" class="form-control" id="username" name="user" placeholder="Username"
                                   >
                            <div class="error-message">Please enter a valid username</div>
                        </div>

                        <div class="form-group">
                            <input type="password" class="form-control" id="password" name="pass" placeholder="Password"
                                   >
                            <button type="button" class="password-toggle">
                                <i class="fas fa-eye"></i>
                            </button>
                            <div class="error-message">Please enter your password</div>
                        </div>

                        <div class="form-options">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="remember" name="rem">
                                <label class="form-check-label" for="remember">Remember me</label>
                            </div>
                            <a href="forgotPassword.jsp" class="forgot-password">Forgot Password?</a>
                        </div>

                        <button type="submit" class="btn btn-submit">
                            <span>Sign In</span>
                        </button>
                    </form>

                    <!-- Register Form -->
                    <form action="register" method="post"  class="register-form">
                        <div class="form-group">
                            <input type="text" class="form-control" id="reg-username" name="user" placeholder="Username"
                                   >
                            <div class="error-message">Please enter a valid username</div>
                        </div>

                        <div class="form-group">
                            <input type="email" class="form-control" id="reg-email" name="email" placeholder="Email"
                                   >
                            <div class="error-message">Please enter a valid email</div>
                        </div>

                        <div class="form-group">
                            <input type="password" class="form-control" id="reg-password" name="pass"
                                   placeholder="Password" >
                            <button type="button" class="password-toggle">
                                <i class="fas fa-eye"></i>
                            </button>
                            <div class="error-message">Password must be at least 6 characters</div>
                        </div>

                        <div class="form-group">
                            <input type="password" class="form-control" id="reg-confirm-password" name="repassword"
                                   placeholder="Confirm Password" >
                            <button type="button" class="password-toggle">
                                <i class="fas fa-eye"></i>
                            </button>
                            <div class="error-message">Passwords do not match</div>
                        </div>

                        <button type="submit" class="btn btn-submit">
                            <span>Create Account</span>
                        </button>
                        <p style="color: red"><%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %></p>

                    </form>
                </div>
            </div>
        </div>

        <!-- jquery -->
        <script src="assets/js/jquery-1.11.3.min.js"></script>
        <!-- bootstrap -->
        <script src="assets/js/bootstrap.min.js"></script>
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

        <script>
            $(document).ready(function () {
                // Chuyển tab giữa login và register
                $('.tab-item').click(function () {
                    $('.tab-item').removeClass('active');
                    $(this).addClass('active');
                    
                    // lấy thuộc tính data-form (giá trị: "login" hoặc "register")
                    const formType = $(this).data('form');
                    if (formType === 'login') {
                        // Ẩn form đăng ký và hiện form đăng nhập
                        $('.register-form').fadeOut(300, function () {
                            $('.login-form').fadeIn(300);
                        });
                    } else {
                        // Ẩn form đăng nhập và hiện form đăng ký
                        $('.login-form').fadeOut(300, function () {
                            $('.register-form').fadeIn(300);
                        });
                    }
                });
            });
            // khi servlet gui lai trang co loi thi trang tu dong chuyen qua tab register
            <% if (request.getAttribute("errorMessage") != null) { %>
            $('.tab-item').removeClass('active');
            $('.tab-item[data-form="register"]').addClass('active');
            $('.login-form').hide(); // an form dang nhap
            $('.register-form').show(); // hien form dang ki
            <% } %>

        </script>

    </body>

</html>