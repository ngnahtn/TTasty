<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Header -->
<div class="top-header-area">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-sm-12">
                <div class="main-menu-wrap">
                    <!-- Logo -->
                    <div class="site-logo">
                        <a href="${sessionScope.Account.role == 1 ? 'adminHome.jsp' : 'index.jsp'}">
                            <img src="assets/img/TTasty.png" alt="TTasty" style="max-height: 60px;">
                        </a>
                    </div>

                    <!-- Mobile Menu Button -->
                    <div class="mobile-menu">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>

                    <!-- Main Menu -->
                    <nav class="main-menu">
                        <ul>
                            <c:choose>
                                <c:when test="${sessionScope.Account.role == 1}">
                                    <!-- Admin Menu Items -->
                                    <li class="${pageContext.request.servletPath == '/manageProduct.jsp' ? 'current-list-item' : ''}">
                                        <a href="manageProducts">
                                            <i class="fas fa-box-open"></i> Quản lý sản phẩm
                                        </a>
                                    </li>
                                    <li class="${pageContext.request.servletPath == '/order.jsp' ? 'current-list-item' : ''}">
                                        <a href="order">
                                            <i class="fas fa-shopping-cart"></i> Đơn hàng
                                        </a>
                                    </li>
                                    <li class="${pageContext.request.servletPath == '/historyFeedback.jsp' ? 'current-list-item' : ''}">
                                        <a href="historyFeedback">
                                            <i class="fas fa-comments"></i> Phản hồi
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <!-- User Menu Items -->
                                    <li class="${pageContext.request.servletPath == '/index.jsp' ? 'current-list-item' : ''}">
                                        <a href="index.jsp">Home</a>
                                    </li>
                                    <li class="${pageContext.request.servletPath == '/about.jsp' ? 'current-list-item' : ''}">
                                        <a href="about.jsp">About</a>
                                    </li>
                                    <li class="${pageContext.request.servletPath == '/shop.jsp' ? 'current-list-item' : ''}">
                                        <a href="shop.jsp">Shop</a>
                                    </li>
                                    <li class="${pageContext.request.servletPath == '/feedback.jsp' ? 'current-list-item' : ''}">
                                        <a href="feedback.jsp">Feedback</a>
                                    </li>
                                    
                                    <c:if test="${sessionScope.Account != null}">
                                        <li class="history-dropdown">
                                            <a href="#">History <i class="fas fa-chevron-down"></i></a>
                                            <ul class="sub-menu">
                                                <li><a href="historyOrder">Order History</a></li>
                                            </ul>
                                        </li>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                            <!-- User Profile Section -->
                            <c:if test="${sessionScope.Account != null}">
                                <li class="user-menu">
                                    <a href="#" class="user-link">
                                        <i class="fas fa-user-circle"></i>
                                        <span>${sessionScope.user}</span>
                                    </a>
                                </li>
                                
                                <!-- Shopping Cart (Only for regular users) -->
                                <c:if test="${sessionScope.Account.role != 1}">
                                    <li>
                                        <a href="cart.jsp" class="shopping-cart">
                                            <i class="fas fa-shopping-cart"></i>
                                            <span class="badge">${sessionScope.size == null ? 0 : sessionScope.size}</span>
                                        </a>
                                    </li>
                                </c:if>
                                
                                <li>
                                    <a href="logout" class="logout-link">
                                        <i class="fas fa-sign-out-alt"></i> Đăng xuất
                                    </a>
                                </li>
                            </c:if>
                            
                            <c:if test="${sessionScope.Account == null}">
                                <li>
                                    <a href="login.jsp" class="login-link">
                                        <i class="fas fa-sign-in-alt"></i> Đăng nhập
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Additional CSS for header -->
<style>
.top-header-area {
    background: white;
    padding: 15px 0;
    position: fixed;
    width: 100%;
    top: 0;
    z-index: 999;
    box-shadow: 0 2px 15px rgba(0,0,0,0.1);
}

.main-menu-wrap {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.site-logo img {
    transition: all 0.3s ease;
}

.site-logo img:hover {
    transform: scale(1.05);
}

.main-menu ul {
    margin: 0;
    padding: 0;
    list-style: none;
    display: flex;
    align-items: center;
}

.main-menu ul li {
    position: relative;
    margin: 0 15px;
}

.main-menu ul li a {
    color: #333;
    font-weight: 500;
    text-decoration: none;
    padding: 10px 15px;
    display: flex;
    align-items: center;
    gap: 8px;
    border-radius: 8px;
    transition: all 0.3s ease;
}

.main-menu ul li a:hover,
.main-menu ul li.current-list-item a {
    color: #F28123;
    background: #f8f9fa;
}

.main-menu ul li a i {
    font-size: 1.1rem;
}

/* User Menu Styles */
.user-menu .user-link {
    background: #f8f9fa;
    border-radius: 20px;
    padding: 8px 15px;
}

.user-menu .user-link:hover {
    background: #e9ecef;
    color: #F28123;
}

/* Login/Logout Button Styles */
.login-link, .logout-link {
    display: flex;
    align-items: center;
    gap: 5px;
    padding: 8px 15px;
    border-radius: 20px;
    transition: all 0.3s ease;
}

.login-link {
    background: #F28123;
    color: white !important;
}

.login-link:hover {
    background: #e67416;
    transform: translateY(-2px);
}

.logout-link {
    background: #dc3545;
    color: white !important;
}

.logout-link:hover {
    background: #c82333;
    transform: translateY(-2px);
}

/* Shopping Cart Styles */
.shopping-cart {
    position: relative;
    padding: 8px 15px !important;
    border-radius: 20px !important;
}

.shopping-cart .badge {
    position: absolute;
    top: -8px;
    right: -8px;
    background: #F28123;
    color: white;
    border-radius: 50%;
    padding: 3px 6px;
    font-size: 10px;
    min-width: 18px;
    text-align: center;
}

/* Dropdown Menu Styles */
.history-dropdown .sub-menu {
    position: absolute;
    top: 100%;
    left: 0;
    background: white;
    min-width: 200px;
    padding: 10px 0;
    box-shadow: 0 5px 15px rgba(0,0,0,0.1);
    border-radius: 8px;
    opacity: 0;
    visibility: hidden;
    transform: translateY(10px);
    transition: all 0.3s ease;
}

.history-dropdown:hover .sub-menu {
    opacity: 1;
    visibility: visible;
    transform: translateY(0);
}

.history-dropdown .sub-menu li {
    margin: 0;
    padding: 0;
}

.history-dropdown .sub-menu li a {
    padding: 8px 20px;
    display: block;
}

.history-dropdown .sub-menu li a:hover {
    background: #f8f9fa;
}

/* Mobile Menu */
@media (max-width: 991px) {
    .mobile-menu {
        display: block;
    }
    
    .main-menu ul {
        display: none;
        position: absolute;
        top: 100%;
        left: 0;
        right: 0;
        background: white;
        padding: 20px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        flex-direction: column;
    }
    
    .main-menu ul.show {
        display: flex;
    }
    
    .main-menu ul li {
        margin: 10px 0;
        width: 100%;
    }
    
    .main-menu ul li a {
        justify-content: center;
    }
    
    .history-dropdown .sub-menu {
        position: static;
        box-shadow: none;
        opacity: 1;
        visibility: visible;
        transform: none;
        padding-left: 20px;
    }
}
</style>

<!-- JavaScript for Mobile Menu -->
<script>
document.querySelector('.mobile-menu').addEventListener('click', function() {
    this.classList.toggle('open');
    document.querySelector('.main-menu ul').classList.toggle('show');
});
</script> 