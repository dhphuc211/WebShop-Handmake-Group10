<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <title>Tài Khoản - Bảng điều khiển</title>
</head>
<body>
<!-- Header -->
<jsp:include page="/compenents/header.jsp" />

<main class="dashboard-main">
    <div class="dashboard-container">
        <!-- Sidebar Menu -->
        <aside class="dashboard-sidebar">
            <div class="sidebar-header">
                <div class="user-avatar">
                    <i class="fa-solid fa-user"></i>
                </div>
                <h3>${sessionScope.user.fullName}</h3>
                <p>${sessionScope.user.email}</p>
            </div>

            <nav class="sidebar-menu">
                <a href="${pageContext.request.contextPath}/account/dashboard.jsp" class="menu-item active">
                    <i class="fa-solid fa-gauge"></i>
                    <span>Bảng điều khiển</span>
                </a>
                <a href="${pageContext.request.contextPath}/account/order.jsp" class="menu-item">
                    <i class="fa-solid fa-box"></i>
                    <span>Đơn hàng</span>
                </a>
                <a href="${pageContext.request.contextPath}/account/account-profile.jsp" class="menu-item">
                    <i class="fa-solid fa-user-circle"></i>
                    <span>Thông tin</span>
                </a>
                <a href="${pageContext.request.contextPath}/change-password" class="menu-item">
                    <i class="fa-solid fa-key"></i>
                    <span>Đổi mật khẩu</span>
                </a>
                <a href="${pageContext.request.contextPath}/logout" class="menu-item logout">
                    <i class="fa-solid fa-right-from-bracket"></i>
                    <span>Đăng xuất</span>
                </a>
            </nav>

            <div class="sidebar-decoration">
                <div class="pattern-circle"></div>
                <div class="pattern-circle"></div>
                <div class="pattern-circle"></div>
            </div>
        </aside>


        <!-- Main Content -->
        <div class="dashboard-content">
            <!-- Welcome Section -->
            <div class="welcome-section">
                <div class="welcome-text">
                    <h1>Xin chào, ${sessionScope.user.fullName}!</h1>
                    <p>Chào mừng bạn trở lại với cửa hàng đồ thủ công mỹ nghệ</p>
                </div>
                <div class="welcome-image">
                    <i class="fa-solid fa-hand-holding-heart"></i>
                </div>
            </div>

            <!-- Statistics Cards -->
            <div class="stats-grid">
                <div class="stat-card card-orders">
                    <div class="stat-icon">
                        <i class="fa-solid fa-shopping-bag"></i>
                    </div>
                    <div class="stat-info">
                        <h3>${not empty totalOrders ? totalOrders : 0}</h3>
                        <p>Đơn hàng</p>
                    </div>
                    <div class="stat-decoration">
                        <i class="fa-solid fa-certificate"></i>
                    </div>
                </div>

                <div class="stat-card card-wishlist">
                    <div class="stat-icon">
                        <i class="fa-solid fa-heart"></i>
                    </div>
                    <div class="stat-info">
                        <h3>${not empty totalWishlist ? totalWishlist : 0}</h3>
                        <p>Yêu thích</p>
                    </div>
                    <div class="stat-decoration">
                        <i class="fa-solid fa-feather"></i>
                    </div>
                </div>
            </div>

            <!-- Recent Orders -->
            <div class="section-container">
                <div class="section-header">
                    <h2>
                        <i class="fa-solid fa-clock-rotate-left"></i>
                        Đơn hàng gần đây
                    </h2>
                    <a href="${pageContext.request.contextPath}/account/order.jsp" class="view-all">Xem tất cả <i class="fa-solid fa-arrow-right"></i></a>
                </div>
                <div class="orders-list">
                    <c:choose>
                        <c:when test="${not empty recentOrders}">
                            <c:forEach var="order" items="${recentOrders}">
                                <div class="order-item">
                                    <div class="order-image">
                                        <img src="${order.productImage}" alt="Sản phẩm">
                                    </div>
                                    <div class="order-info">
                                        <h4>${order.productName}</h4>
                                        <p class="order-date">
                                            <i class="fa-regular fa-calendar"></i>
                                            Ngày đặt: <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy"/>
                                        </p>
                                        <p class="order-id">Mã đơn: #${order.orderId}</p>
                                    </div>
                                    <div class="order-status">
                                        <span class="status-badge status-${order.status}">${order.statusText}</span>
                                        <p class="order-price"><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="" maxFractionDigits="0"/>đ</p>
                                    </div>
                                    <div class="order-actions">
                                        <a href="${pageContext.request.contextPath}/account/order-detail?id=${order.orderId}" class="btn-detail">Chi tiết</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="empty-state">
                                <i class="fa-solid fa-box-open"></i>
                                <p>Bạn chưa có đơn hàng nào</p>
                                <a href="${pageContext.request.contextPath}/products.jsp" class="btn-shop">Mua sắm ngay</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>


            <!-- Favorite Products -->
            <div class="section-container">
                <div class="section-header">
                    <h2>
                        <i class="fa-solid fa-heart"></i>
                        Sản phẩm yêu thích
                    </h2>
                    <a href="${pageContext.request.contextPath}/wishlist.jsp" class="view-all">Xem tất cả <i class="fa-solid fa-arrow-right"></i></a>
                </div>
                <div class="wishlist-grid">
                    <c:choose>
                        <c:when test="${not empty wishlistItems}">
                            <c:forEach var="item" items="${wishlistItems}" end="3">
                                <div class="wishlist-item">
                                    <div class="wishlist-image">
                                        <img src="${item.productImage}" alt="${item.productName}">
                                        <button class="btn-remove-wishlist" data-product-id="${item.productId}">
                                            <i class="fa-solid fa-heart"></i>
                                        </button>
                                    </div>
                                    <div class="wishlist-info">
                                        <h4>${item.productName}</h4>
                                        <p class="wishlist-price"><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="" maxFractionDigits="0"/>đ</p>
                                        <a href="${pageContext.request.contextPath}/cart/add?productId=${item.productId}" class="btn-add-cart">
                                            <i class="fa-solid fa-cart-plus"></i>
                                            Thêm vào giỏ
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="empty-state">
                                <i class="fa-regular fa-heart"></i>
                                <p>Chưa có sản phẩm yêu thích</p>
                                <a href="${pageContext.request.contextPath}/products.jsp" class="btn-shop">Khám phá ngay</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <!-- Account Info -->
            <div class="section-container">
                <div class="section-header">
                    <h2>
                        <i class="fa-solid fa-user-circle"></i>
                        Thông tin cá nhân
                    </h2>
                    <a href="${pageContext.request.contextPath}/account/account-profile.jsp" class="view-all">Chỉnh sửa <i class="fa-solid fa-pen"></i></a>
                </div>
                <div class="account-info-grid">
                    <div class="info-item">
                        <div class="info-icon">
                            <i class="fa-solid fa-user"></i>
                        </div>
                        <div class="info-details">
                            <p class="info-label">Họ và tên</p>
                            <p class="info-value">${sessionScope.user.fullName}</p>
                        </div>
                    </div>

                    <div class="info-item">
                        <div class="info-icon">
                            <i class="fa-solid fa-envelope"></i>
                        </div>
                        <div class="info-details">
                            <p class="info-label">Email</p>
                            <p class="info-value">${sessionScope.user.email}</p>
                        </div>
                    </div>

                    <div class="info-item">
                        <div class="info-icon">
                            <i class="fa-solid fa-phone"></i>
                        </div>
                        <div class="info-details">
                            <p class="info-label">Số điện thoại</p>
                            <p class="info-value">${not empty sessionScope.user.phone ? sessionScope.user.phone : 'Chưa cập nhật'}</p>
                        </div>
                    </div>

                    <div class="info-item">
                        <div class="info-icon">
                            <i class="fa-solid fa-location-dot"></i>
                        </div>
                        <div class="info-details">
                            <p class="info-label">Địa chỉ</p>
                            <p class="info-value">${not empty sessionScope.user.address ? sessionScope.user.address : 'Chưa cập nhật'}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="/compenents/footer.jsp" />
</body>
</html>
