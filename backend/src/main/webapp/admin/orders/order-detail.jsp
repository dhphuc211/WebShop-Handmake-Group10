<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết đơn hàng #DH2547</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/admin-dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/orders-detail.css">
</head>

<body>
    <main class="admin-dashboard-main">
        <div class="admin-dashboard-container">
            <!-- Admin Sidebar -->
            <aside class="admin-sidebar">
                <div class="sidebar-header">
                    <div class="admin-logo">
                        <i class="fa-solid fa-user-shield"></i>
                    </div>
                    <h2>Bảng quản lý Website</h2>
                    <p>Quản trị viên</p>
                </div>

                <nav class="admin-menu">
                    <a href="${pageContext.request.contextPath}/admin/dashboard" class="admin-menu-item">
                        <i class="fa-solid fa-chart-line"></i>
                        <span>Bảng điều khiển</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/products" class="admin-menu-item">
                        <i class="fa-solid fa-box"></i>
                        <span>Quản lý sản phẩm</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/order-list.jsp" class="admin-menu-item active">
                        <i class="fa-solid fa-shopping-cart"></i>
                        <span>Quản lý đơn hàng</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/customers" class="admin-menu-item">
                        <i class="fa-solid fa-users"></i>
                        <span>Quản lý khách hàng</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/reviews" class="admin-menu-item">
                        <i class="fa-solid fa-star"></i>
                        <span>Quản lý đánh giá</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/categories" class="admin-menu-item">
                        <i class="fa-solid fa-folder-tree"></i>
                        <span>Quản lý danh mục</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/contact" class="admin-menu-item">
                        <i class="fa-solid fa-envelope"></i>
                        <span>Quản lý liên hệ</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/blog" class="admin-menu-item">
                        <i class="fa-solid fa-blog"></i>
                        <span>Blog</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/banner" class="admin-menu-item">
                        <i class="fa-solid fa-images"></i>
                        <span>Banner & Slider</span>
                    </a>
                </nav>
            </aside>

            <!-- Main Content -->
            <div class="admin-content">
                <!-- Page Header -->
                <div class="page-header-detail">
                    <div class="header-left">
                        <a href="${pageContext.request.contextPath}/admin/orders" class="back-link">
                            <i class="fa-solid fa-arrow-left"></i>
                            Quay lại danh sách
                        </a>
                        <div class="order-header-info">
                            <h1>Đơn hàng #${order.id}</h1>
                             <span class="status-badge status-${order.order_status != null ? order.order_status.toLowerCase() : 'pending'}">
                                ${order.order_status}
                            </span>
                            </div>
                        <p class="order-date">
                            Đặt lúc: <fmt:formatDate value="${order.created_at}" pattern="dd/MM/yyyy HH:mm"/>
                        </p>
                    </div>
                    <div class="header-actions">
                        <button class="btn-action-header btn-print">
                            <i class="fa-solid fa-print"></i>
                            In hóa đơn
                        </button>
                        <button class="btn-action-header btn-shipping-label">
                            <i class="fa-solid fa-file-invoice"></i>
                            Phiếu giao hàng
                        </button>
                    </div>
                </div>

                <!-- Order Detail Layout -->
                <div class="order-detail-layout">
                    <!-- Main Column -->
                    <div class="order-main-column">

                        <!-- Order Items -->
                        <div class="detail-section">
                            <div class="section-header">
                                <h2>
                                    <i class="fa-solid fa-box-open"></i>
                                    Sản phẩm đã đặt
                                </h2>
                            </div>
                            <div class="section-content">
                                <div class="order-items-table">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>Sản phẩm</th>
                                                <th>Đơn giá</th>
                                                <th>Số lượng</th>
                                                <th>Thành tiền</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%-- 1. VÒNG LẶP SẢN PHẨM --%>
                                                <c:forEach var="item" items="${details}">
                                                <tr>
                                                    <td>
                                                        <div class="product-info">
                                                                <%-- Sử dụng item.product.imageUrl từ hàm getImageUrl() của class Product --%>
                                                            <img src="${item.product.imageUrl}" alt="${item.product.name}" onerror="this.src='https://via.placeholder.com/60'">
                                                            <div class="product-details">
                                                                    <%-- Truy cập trực tiếp vào name của product --%>
                                                                <h4>${item.product.name}</h4>
                                                                    <%-- Truy cập trực tiếp vào id của product --%>
                                                                <p>Mã SP: #${item.product.id}</p>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td><fmt:formatNumber value="${item.product.price}" type="currency" currencySymbol="₫"/></td>
                                                    <td>${item.quantity}</td>
                                                    <td>
                                                        <strong><fmt:formatNumber value="${item.product.price * item.quantity}" type="currency"/></strong>
                                                    </td>
                                                </tr>
                                                </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <c:set var="total" value="${(order.total_amount != null) ? order.total_amount : 0}" />
                                <c:set var="shipping" value="${(order.shipping_fee != null) ? order.shipping_fee : 0}" />
                                <c:set var="subtotal" value="${total - shipping}" />
                                <div class="order-summary">
                                    <div class="summary-row">
                                        <span>Tạm tính:</span>
                                        <span>
                                            <fmt:formatNumber value="${subtotal}" type="currency" currencySymbol="₫"/>
                                        </span>
                                    </div>
                                    <div class="summary-row">
                                        <span>Giảm giá:</span>
                                        <span class="discount">-20.000₫</span>
                                    </div>
                                    <div class="summary-row">
                                        <span>Phí vận chuyển:</span>
                                        <span><fmt:formatNumber value="${shipping}" type="currency" currencySymbol="₫"/></span>
                                    </div>
                                    <div class="summary-row total">
                                        <span>Tổng cộng:</span>
                                        <span><fmt:formatNumber value="${total}" type="currency" currencySymbol="₫"/></span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Customer Note -->
                        <div class="detail-section">
                            <div class="section-header">
                                <h2>
                                    <i class="fa-solid fa-message"></i>
                                    Ghi chú của khách hàng
                                </h2>
                            </div>
                            <div class="section-content">
                                <div class="note-box">
                                    <p>${not empty order.note ? order.note : "Khách hàng không để lại ghi chú."}</p>
                                </div>
                            </div>
                        </div>

                        <!-- Internal Notes -->
                        <div class="detail-section">
                            <div class="section-header">
                                <h2>
                                    <i class="fa-solid fa-note-sticky"></i>
                                    Ghi chú nội bộ
                                </h2>
                            </div>
                            <div class="section-content">
                                <form action="#" method="post" class="add-note-form">
                                    <textarea name="internal_note" rows="3"
                                        placeholder="Thêm ghi chú nội bộ..."></textarea>
                                    <button type="submit" class="btn-add-note">
                                        <i class="fa-solid fa-plus"></i>
                                        Thêm ghi chú
                                    </button>
                                </form>
                                <div class="notes-list">
                                    <div class="note-item">
                                        <div class="note-header">
                                            <div class="note-author">
                                                <i class="fa-solid fa-user"></i>
                                                Admin User
                                            </div>
                                            <div class="note-date">10/11/2025 15:00</div>
                                        </div>
                                        <div class="note-content">
                                            Khách hàng yêu cầu đóng gói cẩn thận
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Sidebar Column -->
                    <div class="order-sidebar-column">

                        <!-- Order Actions -->
                        <div class="detail-section">
                            <div class="section-header">
                                <h2>
                                    <i class="fa-solid fa-sliders"></i>
                                    Thao tác
                                </h2>
                            </div>
                            <div class="section-content">
                                <form action="${pageContext.request.contextPath}/admin/orders" method="post">
                                    <input type="hidden" name="action" value="updateStatus">
                                    <input type="hidden" name="order_id" value="${order.id}">

                                    <div class="form-group">
                                        <label for="order-status">Cập nhật trạng thái</label>
                                        <select id="order-status" name="order_status">
                                            <%-- Dùng JSTL để tự động chọn trạng thái hiện tại --%>
                                            <option value="Pending" ${order.order_status == 'Pending' ? 'selected' : ''}>Chờ xác nhận</option>
                                            <option value="Confirmed" ${order.order_status == 'Confirmed' ? 'selected' : ''}>Đã xác nhận</option>
                                            <option value="Shipping" ${order.order_status == 'Shipping' ? 'selected' : ''}>Đang giao</option>
                                            <option value="Completed" ${order.order_status == 'Completed' ? 'selected' : ''}>Hoàn thành</option>
                                            <option value="Cancelled" ${order.order_status == 'Cancelled' ? 'selected' : ''}>Đã hủy</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn-update-status">
                                        <i class="fa-solid fa-check"></i> Cập nhật trạng thái
                                    </button>
                                </form>
                                <div class="action-buttons">
                                    <button class="btn-action-full btn-email">
                                        <i class="fa-solid fa-envelope"></i>
                                        Gửi Email
                                    </button>
                                </div>
                            </div>
                        </div>

                        <!-- Customer Info -->
                        <div class="detail-section">
                            <div class="section-header">
                                <h2>
                                    <i class="fa-solid fa-user"></i>
                                    Thông tin khách hàng
                                </h2>
                            </div>
                            <div class="section-content">
                                <div class="info-group">
                                    <div class="info-item">
                                        <i class="fa-solid fa-user"></i>
                                        <div class="info-details">
                                            <label>Họ và tên</label>
                                            <p>${order.shipping_name}</p>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <i class="fa-solid fa-envelope"></i>
                                        <div class="info-details">
                                            <label>Email</label>
                                            <p>${order.shipping_email != null ? order.shipping_email : "Chưa cập nhật"}</p>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <i class="fa-solid fa-phone"></i>
                                        <div class="info-details">
                                            <label>Số điện thoại</label>
                                            <p>${order.shipping_phone}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Shipping Info -->
                        <div class="detail-section">
                            <div class="section-header">
                                <h2>
                                    <i class="fa-solid fa-location-dot"></i>
                                    Địa chỉ giao hàng
                                </h2>
                            </div>
                            <div class="section-content">
                                <div class="shipping-address">
                                    <p class="address-name"><strong>${order.shipping_name}</strong></p>
                                    <p class="address-phone">${order.shipping_phone}</p>
                                    <p class="address-text">${order.shipping_address}</p>
                                </div>
                            </div>
                        </div>

                        <!-- Payment Info -->
                        <div class="detail-section">
                            <div class="section-header">
                                <h2>
                                    <i class="fa-solid fa-credit-card"></i>
                                    Thanh toán
                                </h2>
                            </div>
                            <div class="section-content">
                                <div class="info-group">
                                    <div class="info-row">
                                        <label>Phương thức:</label>
                                        <span class="payment-badge cod">COD</span>
                                    </div>
                                    <div class="info-row">
                                        <label>Trạng thái:</label>
                                        <span class="payment-status unpaid">Chưa thanh toán</span>
                                    </div>
                                    <div class="info-row">
                                        <label>Tổng tiền:</label>
                                        <strong class="total-amount"><fmt:formatNumber value="${order.total_amount}" type="currency"/></strong>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>

</html>