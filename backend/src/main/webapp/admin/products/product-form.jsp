<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${product != null ? 'Cập nhật sản phẩm' : 'Thêm sản phẩm mới'}</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/admin-dashboard.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/products-edit.css">
</head>

<body>
<main class="admin-dashboard-main">
  <div class="admin-dashboard-container">
    <aside class="admin-sidebar">
      <div class="sidebar-header">
        <h2>Quản lý</h2>
      </div>
      <nav class="admin-menu">
        <a href="${pageContext.request.contextPath}/admin/products" class="admin-menu-item active">
          <i class="fa-solid fa-box"></i> <span>Sản phẩm</span>
        </a>
        <a href="${pageContext.request.contextPath}/admin/products" class="admin-menu-item">
          <i class="fa-solid fa-arrow-left"></i> <span>Quay lại</span>
        </a>
      </nav>
    </aside>

    <div class="admin-content">
      <div class="page-header-edit">
        <div class="header-left">
          <a href="${pageContext.request.contextPath}/admin/products" class="back-link">
            <i class="fa-solid fa-arrow-left"></i> Quay lại danh sách
          </a>
          <h1>${product != null ? 'Cập nhật sản phẩm' : 'Thêm sản phẩm mới'}</h1>
        </div>
        <div class="header-actions">
          <button type="submit" form="product-form" class="btn-save">
            <i class="fa-solid fa-check"></i> Lưu sản phẩm
          </button>
        </div>
      </div>

      <form id="product-form" action="${pageContext.request.contextPath}/admin/products" method="post">

        <input type="hidden" name="action" value="${product != null ? 'update' : 'insert'}">
        <c:if test="${product != null}">
          <input type="hidden" name="id" value="${product.id}">
        </c:if>

        <div class="form-layout">
          <div class="form-main-column">
            <div class="form-section">
              <div class="section-header"><h2>Thông tin cơ bản</h2></div>
              <div class="section-content">
                <div class="form-group full-width">
                  <label class="required">Tên sản phẩm</label>
                  <input type="text" name="product_name" value="${product.name}" required placeholder="VD: Bình gốm...">
                </div>
                <div class="form-row three-cols">
                  <div class="form-group">
                    <label class="required">Giá bán (VNĐ)</label>
                    <%-- Lưu ý: input number cần giá trị thô, không format --%>
                    <input type="number" name="price" value="${product.price}" required min="0">
                  </div>
                  <div class="form-group">
                    <label class="required">Tồn kho</label>
                    <input type="number" name="stock" value="${product.stock}" required min="0">
                  </div>
                  <div class="form-group">
                    <label>Danh mục (ID)</label>
                    <input type="number" name="categoryId" value="${product != null ? product.categoryId : 1}" required>
                  </div>
                </div>
              </div>
            </div>

            <div class="form-section">
              <div class="section-header"><h2>Mô tả</h2></div>
              <div class="section-content">
                <div class="form-group">
                  <textarea name="full_description" rows="6" placeholder="Mô tả chi tiết...">${product.fullDescription}</textarea>
                </div>
              </div>
            </div>

            <div class="form-section">
              <div class="section-header"><h2>Hình ảnh (URL)</h2></div>
              <div class="section-content">
                <div class="form-group">
                  <label>Link ảnh sản phẩm</label>
                  <input type="text" id="image-url" name="image_url"
                         class="form-control"
                         value="${product.imageUrl}"
                         placeholder="https://..."
                         oninput="document.getElementById('preview-img').src = this.value">
                </div>
                <div style="margin-top: 10px; border: 1px dashed #ccc; padding: 5px; width: 150px; text-align: center;">
                  <img id="preview-img" src="${product.imageUrl}" alt="Preview"
                       style="max-width: 100%; max-height: 150px;"
                       onerror="this.src='https://via.placeholder.com/150?text=No+Image'">
                </div>
              </div>
            </div>
          </div>

          <div class="form-sidebar-column">
            <div class="form-section">
              <div class="section-header"><h2>Trạng thái</h2></div>
              <div class="section-content">
                <div class="form-group">
                  <label>Trạng thái</label>
                  <select name="status">
                    <option value="active" ${product.status == 'active' ? 'selected' : ''}>Đang bán</option>
                    <option value="inactive" ${product.status == 'inactive' ? 'selected' : ''}>Tạm ngưng</option>
                  </select>
                </div>
                <div class="checkbox-group" style="margin-top: 15px;">
                  <label class="checkbox-label">
                    <input type="checkbox" name="featured" value="1" ${product.featured ? 'checked' : ''}>
                    <span class="checkbox-text"> Sản phẩm nổi bật</span>
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="form-actions">
          <a href="${pageContext.request.contextPath}/admin/product" class="btn-cancel">Hủy bỏ</a>
          <button type="submit" class="btn-save-main">Lưu sản phẩm</button>
        </div>
      </form>
    </div>
  </div>
</main>
</body>
</html>