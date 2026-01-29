<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quản lý danh mục - Admin</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/admin-dashboard.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/categories-list.css">
</head>
<body>
<main class="admin-dashboard-main">
  <div class="admin-dashboard-container">

    <%-- Import Sidebar --%>
    <jsp:include page="/admin/components/sidebar.jsp">
      <jsp:param name="activePage" value="categories" />
    </jsp:include>

    <div class="admin-content">
      <div class="page-header">
        <div class="header-left">
          <h1>Quản lý danh mục</h1>
          <p>Tổng cộng <strong>${categoriesCount != null ? categoriesCount : 24} danh mục</strong></p>
        </div>
        <div class="header-right">
          <a href="#add-category-form" class="btn-add-category">
            <i class="fa-solid fa-plus"></i> Thêm danh mục
          </a>
        </div>
      </div>

      <div class="category-form-section" id="add-category-form">
        <div class="form-card">
          <div class="form-header">
            <h3><i class="fa-solid fa-plus-circle"></i> Thêm danh mục mới</h3>
            <a href="#" class="btn-close-form"><i class="fa-solid fa-times"></i></a>
          </div>

          <form action="${pageContext.request.contextPath}/admin/categories" method="post" class="category-form" enctype="multipart/form-data">
            <div class="form-section">
              <h4 class="section-title"><i class="fa-solid fa-circle-info"></i> Thông tin cơ bản</h4>
              <div class="form-grid">
                <div class="form-group full-width">
                  <label for="category-name">Tên danh mục <span class="required">*</span></label>
                  <input type="text" id="category-name" name="category_name" placeholder="Nhập tên danh mục" required>
                </div>

                <div class="form-group full-width">
                  <label for="category-slug">Slug (Đường dẫn) <span class="required">*</span></label>
                  <input type="text" id="category-slug" name="category_slug" placeholder="vi-du: gom-su-cao-cap" required>
                </div>

                <div class="form-group full-width">
                  <label for="parent-category">Danh mục cha</label>
                  <select id="parent-category" name="parent_id">
                    <option value="0">-- Không có (Danh mục gốc) --</option>
                    <%-- Giả sử bạn gửi list categories từ Servlet --%>
                    <c:forEach var="cat" items="${parentCategories}">
                      <option value="${cat.id}">${cat.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <div class="form-group full-width">
                  <label for="category-description">Mô tả</label>
                  <textarea id="category-description" name="category_description" rows="4"></textarea>
                </div>

                <div class="form-group full-width">
                  <label for="category-image">Ảnh đại diện</label>
                  <input type="file" id="category-image" name="category_image" accept="image/*">
                </div>

                <div class="form-group">
                  <label for="display-order">Thứ tự hiển thị</label>
                  <input type="number" id="display-order" name="display_order" value="0">
                </div>

                <div class="form-group">
                  <label for="category-status">Trạng thái</label>
                  <select id="category-status" name="status">
                    <option value="1">Hiển thị</option>
                    <option value="0">Ẩn</option>
                  </select>
                </div>
              </div>
            </div>

            <div class="form-actions">
              <button type="submit" class="btn-submit"><i class="fa-solid fa-check"></i> Lưu danh mục</button>
              <button type="reset" class="btn-reset"><i class="fa-solid fa-rotate-right"></i> Đặt lại</button>
            </div>
          </form>
        </div>
      </div>

      <div class="categories-tree-section">
        <div class="tree-card">
          <div class="tree-header">
            <h3><i class="fa-solid fa-sitemap"></i> Cây danh mục</h3>
          </div>
          <div class="tree-body">
            <ul class="category-tree">
              <%--
                 Đây là nơi bạn sử dụng vòng lặp c:forEach
                 để render dữ liệu thật từ database
              --%>
              <li class="category-item level-1">
                <div class="category-row">
                  <div class="category-info">
                    <button class="toggle-btn"><i class="fa-solid fa-chevron-down"></i></button>
                    <div class="category-icon"><i class="fa-solid fa-folder"></i></div>
                    <div class="category-details">
                      <h4>Thủ công mỹ nghệ</h4>
                      <span class="category-slug">thu-cong-my-nghe</span>
                    </div>
                  </div>
                  <div class="category-meta">
                    <span class="product-count"><i class="fa-solid fa-box"></i> 120 sản phẩm</span>
                    <span class="status-badge visible">Hiển thị</span>
                  </div>
                  <div class="category-actions">
                    <a href="edit?id=1" class="btn-edit"><i class="fa-solid fa-edit"></i></a>
                    <button class="btn-delete" onclick="confirmDelete(1)"><i class="fa-solid fa-trash"></i></button>
                  </div>
                </div>
                <%-- Thêm các cấp con ở đây --%>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</main>

<script>
  function confirmDelete(id) {
    if(confirm('Bạn có chắc chắn muốn xóa danh mục này?')) {
      window.location.href = 'delete?id=' + id;
    }
  }
</script>
</body>
</html>