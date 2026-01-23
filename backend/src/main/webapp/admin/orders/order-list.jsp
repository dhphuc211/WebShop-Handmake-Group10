<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý đơn hàng</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/admin-dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/orders-list.css">
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
                    <a href="${pageContext.request.contextPath}/admin/dashboard.jsp" class="admin-menu-item">
                        <i class="fa-solid fa-chart-line"></i>
                        <span>Bảng điều khiển</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/products/product-list.jsp" class="admin-menu-item">
                        <i class="fa-solid fa-box"></i>
                        <span>Quản lý sản phẩm</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/orders" class="admin-menu-item active">
                        <i class="fa-solid fa-shopping-cart"></i>
                        <span>Quản lý đơn hàng</span>
                    </a>
                    <a href="../customers/list.html" class="admin-menu-item">
                        <i class="fa-solid fa-users"></i>
                        <span>Quản lý khách hàng</span>
                    </a>
                    <a href="../reviews/list.html" class="admin-menu-item">
                        <i class="fa-solid fa-star"></i>
                        <span>Quản lý đánh giá</span>
                    </a>
                    <a href="../categories/list.html" class="admin-menu-item">
                        <i class="fa-solid fa-folder-tree"></i>
                        <span>Quản lý danh mục</span>
                    </a>
                    <a href="../contact/list.html" class="admin-menu-item">
                        <i class="fa-solid fa-envelope"></i>
                        <span>Quản lý liên hệ</span>
                    </a>
                    <a href="../blog/posts.html" class="admin-menu-item">
                        <i class="fa-solid fa-blog"></i>
                        <span>Blog</span>
                    </a>
                    <a href="../banners/list.html" class="admin-menu-item">
                        <i class="fa-solid fa-images"></i>
                        <span>Banner & Slider</span>
                    </a>
                    <a href="../../index.html" class="admin-menu-item">
                        <i class="fa-solid fa-globe"></i>
                        <span>Website</span>
                    </a>
                    <a href="../../index.html" class="admin-menu-item logout">
                        <i class="fa-solid fa-right-from-bracket"></i>
                        <span>Đăng xuất</span>
                    </a>
                </nav>
            </aside>

            <!-- Main Content -->
            <div class="admin-content">
                <!-- Page Header -->
                <div class="page-header">
                    <div class="header-left">
                        <h1>Quản lý đơn hàng</h1>
                        <p>Tổng cộng <strong>${orders != null ? orders.size() : 0} đơn hàng</strong></p>
                    </div>
                    <div class="header-right">
                        <button class="btn-export-excel">
                            <i class="fa-solid fa-file-excel"></i>
                            Xuất Excel
                        </button>
                        <button class="btn-export-pdf">
                            <i class="fa-solid fa-file-pdf"></i>
                            Xuất PDF
                        </button>
                    </div>
                </div>

                <!-- Status Tabs -->
                <div class="status-tabs">
                    <a href="#" class="tab-item active">
                        <span class="tab-label">Tất cả</span>
                    </a>
                    <a href="#" class="tab-item pending">
                        <span class="tab-label">Chờ xác nhận</span>
                    </a>
                    <a href="#" class="tab-item confirmed">
                        <span class="tab-label">Đã xác nhận</span>
                    </a>
                    <a href="#" class="tab-item shipping">
                        <span class="tab-label">Đang giao</span>
                    </a>
                    <a href="#" class="tab-item completed">
                        <span class="tab-label">Hoàn thành</span>
                    </a>
                    <a href="#" class="tab-item cancelled">
                        <span class="tab-label">Đã hủy</span>
                    </a>
                    <a href="#" class="tab-item returned">
                        <span class="tab-label">Hoàn trả</span>
                    </a>
                </div>

                <!-- Search and Filter Section -->
                <div class="filter-section">
                    <form action="${pageContext.request.contextPath}/admin/orders" method="get" class="filter-form">
                        <!-- Search Bar -->
                        <div class="search-wrapper">
                            <i class="fa-solid fa-search"></i>
                            <input type="text" name="search" value="${param.search}" placeholder="Tìm kiếm theo mã đơn hàng, tên khách hàng, SĐT, email..." class="search-input">
                        </div>

                        <!-- Filter Options -->
                        <div class="filter-options">
                            <!-- Date Range Filter -->
                            <div class="filter-group">
                                <label for="date-from">
                                    <i class="fa-solid fa-calendar"></i>
                                    Từ ngày
                                </label>
                                <input type="date" id="date-from" name="date_from">
                            </div>

                            <div class="filter-group">
                                <label for="date-to">
                                    <i class="fa-solid fa-calendar"></i>
                                    Đến ngày
                                </label>
                                <input type="date" id="date-to" name="date_to">
                            </div>

                            <!-- Order Value Filter -->
                            <div class="filter-group">
                                <label for="order-value">
                                    <i class="fa-solid fa-dollar-sign"></i>
                                    Giá trị đơn hàng
                                </label>
                                <select name="order_value" id="order-value">
                                    <option value="">Tất cả</option>
                                    <option value="0-500000">Dưới 500K</option>
                                    <option value="500000-1000000">500K - 1M</option>
                                    <option value="1000000-2000000">1M - 2M</option>
                                    <option value="2000000-5000000">2M - 5M</option>
                                    <option value="5000000+">Trên 5M</option>
                                </select>
                            </div>

                            <!-- Payment Method Filter -->
                            <div class="filter-group">
                                <label for="payment-method">
                                    <i class="fa-solid fa-credit-card"></i>
                                    Phương thức thanh toán
                                </label>
                                <select name="payment_method" id="payment-method">
                                    <option value="">Tất cả</option>
                                    <option value="cod">Tiền mặt</option>
                                    <option value="momo">Ví MoMo</option>
                                    <option value="vnpay">VNPay</option>
                                </select>
                            </div>

                            <!-- Payment Status Filter -->
                            <div class="filter-group">
                                <label for="payment-status">
                                    <i class="fa-solid fa-money-check"></i>
                                    Trạng thái thanh toán
                                </label>
                                <select name="payment_status" id="payment-status">
                                    <option value="">Tất cả</option>
                                    <option value="paid">Đã thanh toán</option>
                                    <option value="unpaid">Chưa thanh toán</option>
                                    <option value="refunded">Đã hoàn tiền</option>
                                </select>
                            </div>
                        </div>

                        <!-- Filter Actions -->
                        <div class="filter-actions">
                            <button type="submit" class="btn-apply-filter">
                                <i class="fa-solid fa-filter"></i>
                                Áp dụng
                            </button>
                            <button type="reset" class="btn-reset-filter">
                                <i class="fa-solid fa-rotate-right"></i>
                                Đặt lại
                            </button>
                        </div>
                    </form>
                </div>

                <!-- Orders Table -->
                <div class="orders-table-container">
                    <table class="orders-table">
                        <thead>
                            <tr>
                                <th class="col-order-id">Mã đơn hàng</th>
                                <th class="col-customer">Khách hàng</th>
                                <th class="col-contact">Liên hệ</th>
                                <th class="col-total">Tổng tiền</th>
                                <th class="col-payment-method">Thanh toán</th>
                                <th class="col-payment-status">TT Thanh toán</th>
                                <th class="col-date">Ngày đặt</th>
                                <th class="col-status">Trạng thái</th>
                                <th class="col-actions">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%-- BẮT ĐẦU VÒNG LẶP JSTL --%>
                            <c:forEach var="o" items="${orders}">
                                <tr class="order-row">
                                    <td class="col-order-id">
                                        <%-- Link xem chi tiết --%>
                                        <a href="orders?action=detail&id=${o.id}" class="order-id-link">#${o.id}</a>
                                    </td>

                                    <td class="col-customer">
                                        <div class="customer-info">
                                            <div class="customer-avatar"><i class="fa-solid fa-user"></i></div>
                                            <div class="customer-details">
                                                <h4>${o.shipping_name}</h4>
                                                <%-- Nếu có User ID thì hiện, không thì hiện khách vãng lai --%>
                                                <small>${o.user_id != 0 ? "Thành viên" : "Khách vãng lai"}</small>
                                            </div>
                                        </div>
                                    </td>

                                    <td class="col-contact">
                                        <div class="contact-info">
                                            <p><i class="fa-solid fa-phone"></i> ${o.shipping_phone}</p>
                                            <%-- Nếu email null thì ẩn --%>
                                            <c:if test="${not empty o.shipping_email}">
                                                <p><i class="fa-solid fa-envelope"></i> ${o.shipping_email}</p>
                                            </c:if>
                                        </div>
                                    </td>

                                    <td class="col-total">
                                        <span class="total-amount">
                                            <fmt:formatNumber value="${o.total_amount}" type="currency"/>
                                        </span>
                                    </td>

                                    <td class="col-payment-method">
                                        <span class="payment-badge ${o.payment_method != null ? o.payment_method.toLowerCase() : 'cod'}">
                                            ${o.payment_method != null ? o.payment_method : 'COD'}
                                        </span>
                                    </td>

                                    <td class="col-date">
                                        <div class="date-info">
                                            <p><fmt:formatDate value="${o.created_at}" pattern="dd/MM/yyyy"/></p>
                                            <small><fmt:formatDate value="${o.created_at}" pattern="HH:mm"/></small>
                                        </div>
                                    </td>

                                    <td class="col-status">
                                        <%-- Dynamic Status Class: status-pending, status-completed... --%>
                                       <span class="status-badge status-${o.status != null ? o.status.toLowerCase() : 'pending'}">
                                           ${o.status != null ? o.status : 'Pending'}
                                       </span>
                                    </td>

                                    <td class="col-actions">
                                        <div class="action-buttons">
                                            <%-- Link nút xem --%>
                                            <a href="orders?action=detail&id=${o.id}" class="btn-action btn-view" title="Xem chi tiết">
                                                <i class="fa-solid fa-eye"></i>
                                            </a>
                                            <%-- Nút in hóa đơn (Demo) --%>
                                            <button class="btn-action btn-print" title="In hóa đơn" onclick="window.print()">
                                                <i class="fa-solid fa-print"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>

                            <%-- Hiển thị thông báo nếu danh sách đơn hàng trống --%>
                            <c:if test="${empty orders}">
                                <tr>
                                    <td colspan="8" style="text-align: center; padding: 30px;">
                                        <i class="fa-solid fa-box-open" style="font-size: 30px; color: #ccc; margin-bottom: 10px;"></i>
                                        <p>Không tìm thấy đơn hàng nào.</p>
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>

                <!-- Pagination -->
                <div class="pagination-section">
                    <div class="pagination-info">
                        Hiển thị <strong>1-8</strong> trong tổng số <strong>456</strong> đơn hàng
                    </div>
                    <div class="pagination">
                        <a href="#" class="page-link disabled">
                            <i class="fa-solid fa-chevron-left"></i>
                        </a>
                        <a href="#" class="page-link active">1</a>
                        <a href="#" class="page-link">2</a>
                        <a href="#" class="page-link">3</a>
                        <a href="#" class="page-link">4</a>
                        <a href="#" class="page-link">5</a>
                        <span class="page-dots">...</span>
                        <a href="#" class="page-link">58</a>
                        <a href="#" class="page-link">
                            <i class="fa-solid fa-chevron-right"></i>
                        </a>
                    </div>
                    <div class="per-page">
                        <label for="per-page">Hiển thị:</label>
                        <select name="per_page" id="per-page">
                            <option value="8">8</option>
                            <option value="20">20</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
