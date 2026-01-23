<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bảng điều khiển</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/admin-dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/admin-home.css">
</head>
<body>
<main class="admin-dashboard-main">
    <div class="admin-dashboard-container">
        <jsp:include page="/admin/components/sidebar.jsp">
            <jsp:param name="active" value="dashboard" />
        </jsp:include>

        <div class="admin-content">
            <div class="page-header">
                <div class="header-left">
                    <h1>Tổng quan hệ thống</h1>
                    <p>Theo dõi nhanh tình trạng cửa hàng</p>
                </div>
                <div class="header-right">
                    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">
                        <i class="fa-solid fa-globe"></i> Website
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/products?action=new" class="btn btn-primary">
                        <i class="fa-solid fa-plus"></i> Thêm sản phẩm
                    </a>
                </div>
            </div>

            <section class="stats-grid">
                <div class="stat-card">
                    <div class="stat-header">
                        <div class="stat-icon info"><i class="fa-solid fa-boxes-stacked"></i></div>
                        <span class="badge info">Sản phẩm</span>
                    </div>
                    <div class="stat-value">0</div>
                    <div class="stat-label">Tổng số sản phẩm</div>
                    <div class="stat-meta">Cập nhật gần nhất: hôm nay</div>
                </div>

                <div class="stat-card">
                    <div class="stat-header">
                        <div class="stat-icon"><i class="fa-solid fa-cart-shopping"></i></div>
                        <span class="badge info">Đơn hàng</span>
                    </div>
                    <div class="stat-value">0</div>
                    <div class="stat-label">Đơn hàng hôm nay</div>
                    <div class="stat-meta">Chưa có dữ liệu đồng bộ</div>
                </div>

                <div class="stat-card">
                    <div class="stat-header">
                        <div class="stat-icon warning"><i class="fa-solid fa-clock"></i></div>
                        <span class="badge warning">Chờ xử lý</span>
                    </div>
                    <div class="stat-value">0</div>
                    <div class="stat-label">Đơn chờ xác nhận</div>
                    <div class="stat-meta">Theo dõi trạng thái mới</div>
                </div>

                <div class="stat-card">
                    <div class="stat-header">
                        <div class="stat-icon success"><i class="fa-solid fa-sack-dollar"></i></div>
                        <span class="badge success">Doanh thu</span>
                    </div>
                    <div class="stat-value">0đ</div>
                    <div class="stat-label">Doanh thu tháng</div>
                    <div class="stat-meta">Chưa có báo cáo</div>
                </div>
            </section>

            <section class="dashboard-panels">
                <div class="panel">
                    <div class="panel-header">
                        <h2>Hoạt động gần đây</h2>
                        <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-ghost">
                            Xem sản phẩm
                        </a>
                    </div>
                    <div class="activity-list">
                        <div class="activity-item">
                            <div>
                                <p class="activity-title">Chưa có hoạt động mới</p>
                                <span class="activity-time">Hôm nay</span>
                            </div>
                            <span class="badge muted">Chờ dữ liệu</span>
                        </div>
                        <div class="activity-item">
                            <div>
                                <p class="activity-title">Cập nhật danh mục</p>
                                <span class="activity-time">Ví dụ dữ liệu mẫu</span>
                            </div>
                            <span class="badge info">Nội bộ</span>
                        </div>
                        <div class="activity-item">
                            <div>
                                <p class="activity-title">Kiểm tra tồn kho</p>
                                <span class="activity-time">Ví dụ dữ liệu mẫu</span>
                            </div>
                            <span class="badge warning">Nhắc việc</span>
                        </div>
                    </div>
                </div>

                <div class="panel">
                    <div class="panel-header">
                        <h2>Lối tắt</h2>
                    </div>
                    <div class="action-grid">
                        <a class="action-card" href="${pageContext.request.contextPath}/admin/products?action=new">
                            <i class="fa-solid fa-plus"></i>
                            <strong>Thêm sản phẩm</strong>
                            <span>Tạo sản phẩm mới</span>
                        </a>
                        <a class="action-card" href="${pageContext.request.contextPath}/admin/orders/order-list.jsp">
                            <i class="fa-solid fa-shopping-cart"></i>
                            <strong>Danh sách đơn hàng</strong>
                            <span>Theo dõi giao dịch</span>
                        </a>
                        <a class="action-card" href="${pageContext.request.contextPath}/admin/products">
                            <i class="fa-solid fa-box"></i>
                            <strong>Danh sách sản phẩm</strong>
                            <span>Quản lý hàng hóa</span>
                        </a>
                        <a class="action-card" href="${pageContext.request.contextPath}/admin/customers">
                            <i class="fa-solid fa-users"></i>
                            <strong>Danh sách khách hàng</strong>
                            <span>Quay lại khách hàng</span>
                        </a>
                    </div>

                    <div class="panel-header panel-header-spaced">
                        <h2>Tóm tắt nhanh</h2>
                    </div>
                    <div class="summary-list">
                        <div class="summary-item">
                            <strong>Trạng thái hệ thống</strong>
                            <span>Ổn định</span>
                        </div>
                        <div class="summary-item">
                            <strong>Phiên đăng nhập</strong>
                            <span>Hoạt động</span>
                        </div>
                        <div class="summary-item">
                            <strong>Dữ liệu thống kê</strong>
                            <span>Chưa cập nhật</span>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</main>
</body>
</html>
