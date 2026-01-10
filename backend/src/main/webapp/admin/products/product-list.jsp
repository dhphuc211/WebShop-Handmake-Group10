<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý sản phẩm</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/admin-dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/products-list.css">
</head>

<body>
<main class="admin-dashboard-main">
    <div class="admin-dashboard-container">
        <aside class="admin-sidebar">
            <div class="sidebar-header">
                <div class="admin-logo">
                    <i class="fa-solid fa-user-shield"></i>
                </div>
                <h2>Bảng quản lý</h2>
                <p>Quản trị viên</p>
            </div>
            <nav class="admin-menu">
                <a href="${pageContext.request.contextPath}/admin/dashboard" class="admin-menu-item">
                    <i class="fa-solid fa-chart-line"></i> <span>Bảng điều khiển</span>
                </a>
                <a href="${pageContext.request.contextPath}/admin/products" class="admin-menu-item active">
                    <i class="fa-solid fa-box"></i> <span>Quản lý sản phẩm</span>
                </a>
                <a href="${pageContext.request.contextPath}/index.jsp" class="admin-menu-item logout">
                    <i class="fa-solid fa-right-from-bracket"></i> <span>Về Website</span>
                </a>
            </nav>
        </aside>

        <div class="admin-content">
            <div class="page-header">
                <div class="header-left">
                    <h1>Quản lý sản phẩm</h1>
                    <p>Danh sách sản phẩm hiện có trong hệ thống</p>
                </div>
                <div class="header-right">
                    <a href="${pageContext.request.contextPath}/admin/products?action=new" class="btn-add-product">
                        <i class="fa-solid fa-plus"></i> Thêm sản phẩm mới
                    </a>
                </div>
            </div>

<%--            <div class="filter-section">--%>
<%--                <form action="${pageContext.request.contextPath}/admin/products" method="get" class="filter-form">--%>
<%--                    <div class="search-wrapper">--%>
<%--                        <i class="fa-solid fa-search"></i>--%>
<%--                        <input type="text" name="search" placeholder="Tìm kiếm sản phẩm..." class="search-input"--%>
<%--                               value="${param.search}">--%>
<%--                    </div>--%>
<%--                    <div class="filter-actions">--%>
<%--                        <button type="submit" class="btn-apply-filter"><i class="fa-solid fa-filter"></i> Tìm kiếm--%>
<%--                        </button>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </div>--%>

            <c:if test="${not empty param.message}">
                <div style="padding: 10px; margin-bottom: 15px; background: #d4edda; color: #155724; border-radius: 5px;">
                    <c:choose>
                        <c:when test="${param.message == 'inserted'}">Thêm mới thành công!</c:when>
                        <c:when test="${param.message == 'updated'}">Cập nhật thành công!</c:when>
                        <c:when test="${param.message == 'deleted'}">Đã xóa sản phẩm!</c:when>
                    </c:choose>
                </div>
            </c:if>

            <div class="products-table-container">
                <table id="productTable" class="products-table">
                    <thead>
                    <tr>
                        <th class="col-checkbox"><input type="checkbox" class="checkbox-header"></th>
                        <th class="col-image">Ảnh</th>
                        <th class="col-name">Tên sản phẩm</th>
                        <th class="col-category">Danh mục (ID)</th>
                        <th class="col-price">Giá</th>
                        <th class="col-stock">Tồn kho</th>
                        <th class="col-status">Trạng thái</th>
                        <th class="col-actions">Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>

                    <%-- BẮT ĐẦU VÒNG LẶP JSTL --%>
                    <c:forEach items="${listProducts}" var="p">
                        <tr class="product-row">
                            <td class="col-checkbox">
                                <input type="checkbox" name="product_ids[]" value="${p.id}">
                            </td>
                            <td class="col-image">
                                <div class="product-thumbnail">
                                        <%-- Xử lý hiển thị ảnh: Nếu có link thì hiện, không thì hiện ảnh lỗi --%>
                                    <img src="${p.imageUrl}" alt="${p.name}"
                                         onerror="this.src='https://via.placeholder.com/50'">

                                        <%-- Nếu là sản phẩm nổi bật thì hiện ngôi sao --%>
                                    <c:if test="${p.featured}">
                                        <span class="featured-badge"><i class="fa-solid fa-star"></i></span>
                                    </c:if>
                                </div>
                            </td>
                            <td class="col-name">
                                <div class="product-name-wrapper">
                                    <h4>${p.name}</h4>
                                    <p class="product-desc">ID: ${p.id}</p>
                                </div>
                            </td>
                            <td class="col-category">
                                <span class="category-badge">${p.categoryId}</span>
                            </td>
                            <td class="col-price">
                                <div class="price-wrapper">
                                    <span class="price">
                                        <fmt:setLocale value="vi_VN"/>
                                        <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="đ"/>
                                    </span>
                                </div>
                            </td>
                            <td class="col-stock">
                                <div class="stock-wrapper">
                                        <%-- Logic màu sắc tồn kho --%>
                                    <span class="stock-number ${p.stock < 10 ? 'stock-critical' : 'stock-normal'}">
                                            ${p.stock}
                                    </span>
                                </div>
                            </td>
                            <td class="col-status">
                                    <%-- Logic hiển thị trạng thái --%>
                                <c:choose>
                                    <c:when test="${p.status == 'active'}">
                                        <span class="status-badge status-active">Đang bán</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status-badge status-inactive">Tạm ngưng</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="col-actions">
                                <div class="action-buttons">
                                    <a href="${pageContext.request.contextPath}/admin/products?action=edit&id=${p.id}"
                                       class="btn-action btn-edit" title="Chỉnh sửa">
                                        <i class="fa-solid fa-pen"></i>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/admin/products?action=delete&id=${p.id}"
                                       class="btn-action btn-delete"
                                       title="Xóa"
                                       onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm ID: ${p.id} không?');">
                                        <i class="fa-solid fa-trash"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <%-- KẾT THÚC VÒNG LẶP --%>

                    </tbody>
                </table>
            </div>

        </div>
    </div>
</main>
<script>
    $(document).ready(function() {
        $('#productTable').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.13.7/i18n/vi.json" // Việt hóa giao diện
            },
            "columnDefs": [
                { "orderable": false, "targets": [0, 1, 7] } // Vô hiệu hóa sắp xếp cột Checkbox, Ảnh, Thao tác
            ],
            "pageLength": 10, // Số dòng hiển thị mặc định
            "order": [[2, 'asc']] // Sắp xếp mặc định theo tên sản phẩm (cột thứ 3)
        });
    });
</script>
</body>
</html>