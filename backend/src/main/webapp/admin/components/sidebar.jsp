<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<aside class="admin-sidebar">
  <div class="sidebar-header">
    <div class="admin-logo"><i class="fa-solid fa-user-shield"></i></div>
    <h2>Bảng quản lý</h2>
    <p>Quản trị viên</p>
  </div>
  <nav class="admin-menu">
    <%-- 1. Bảng điều khiển --%>
    <a href="${pageContext.request.contextPath}/admin/dashboard"
       class="admin-menu-item ${param.active == 'dashboard' ? 'active' : ''}">
      <i class="fa-solid fa-chart-line"></i> <span>Bảng điều khiển</span>
    </a>

    <%-- 2. Quản lý sản phẩm --%>
    <a href="${pageContext.request.contextPath}/admin/products"
       class="admin-menu-item ${param.active == 'products' ? 'active' : ''}">
      <i class="fa-solid fa-box"></i> <span>Quản lý sản phẩm</span>
    </a>

    <%-- 4. Quản lý đơn hàng --%>
    <a href="${pageContext.request.contextPath}/admin/orders"
       class="admin-menu-item ${param.active == 'orders' ? 'active' : ''}">
      <i class="fa-solid fa-shopping-cart"></i> <span>Quản lý đơn hàng</span>
    </a>

    <%-- 5. Quản lý khách hàng --%>
    <a href="${pageContext.request.contextPath}/admin/customers"
       class="admin-menu-item ${param.active == 'customers' ? 'active' : ''}">
      <i class="fa-solid fa-users"></i> <span>Quản lý khách hàng</span>
    </a>

    <%-- 3. Quản lý danh mục --%>
    <a href="${pageContext.request.contextPath}/admin/categories/list.jsp"
       class="admin-menu-item ${param.active == 'categories' ? 'active' : ''}">
      <i class="fa-solid fa-folder-tree"></i> <span>Quản lý danh mục</span>
    </a>

    <%-- 6. Quản lý đánh giá --%>
    <a href="${pageContext.request.contextPath}/admin/review/list.jsp"
       class="admin-menu-item ${param.active == 'reviews' ? 'active' : ''}">
      <i class="fa-solid fa-star"></i> <span>Quản lý đánh giá</span>
    </a>

    <%-- 9. Banner & Slider --%>
    <a href="${pageContext.request.contextPath}/admin/banner/list.jsp"
       class="admin-menu-item ${param.active == 'banners' ? 'active' : ''}">
      <i class="fa-solid fa-images"></i> <span>Banner & Slider</span>
    </a>

    <%-- 7. Quản lý liên hệ --%>
    <a href="${pageContext.request.contextPath}/admin/contacts"
       class="admin-menu-item ${param.active == 'contacts' ? 'active' : ''}">
      <i class="fa-solid fa-envelope"></i> <span>Quản lý liên hệ</span>
    </a>

    <%-- 8. Quản lý Blog --%>
    <a href="${pageContext.request.contextPath}/admin/blog"
       class="admin-menu-item ${param.active == 'blog' ? 'active' : ''}">
      <i class="fa-solid fa-blog"></i> <span>Blog</span>
    </a>

    <%-- Nút thoát về Website --%>
    <a href="${pageContext.request.contextPath}/" class="admin-menu-item logout">
      <i class="fa-solid fa-right-from-bracket"></i> <span>Về Website</span>
    </a>
  </nav>
</aside>