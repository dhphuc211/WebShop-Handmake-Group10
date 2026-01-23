<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<aside class="admin-sidebar">
  <div class="sidebar-header">
    <div class="admin-logo"><i class="fa-solid fa-user-shield"></i></div>
    <h2>Bảng quản lý</h2>
    <p>Quản trị viên</p>
  </div>
  <nav class="admin-menu">
    <%-- Kiểm tra biến 'active' truyền từ trang mẹ --%>
    <a href="${pageContext.request.contextPath}/admin/dashboard"
       class="admin-menu-item ${param.active == 'dashboard' ? 'active' : ''}">
      <i class="fa-solid fa-chart-line"></i> <span>Bảng điều khiển</span>
    </a>

    <a href="${pageContext.request.contextPath}/admin/products"
       class="admin-menu-item ${param.active == 'products' ? 'active' : ''}">
      <i class="fa-solid fa-box"></i> <span>Quản lý sản phẩm</span>
    </a>

    <a href="${pageContext.request.contextPath}/admin/customers"
       class="admin-menu-item ${param.active == 'customers' ? 'active' : ''}">
      <i class="fa-solid fa-users"></i> <span>Quản lý khách hàng</span>
    </a>

    <a href="${pageContext.request.contextPath}/admin/orders/order-list.jsp"
       class="admin-menu-item ${param.active == 'orders' ? 'active' : ''}">
      <i class="fa-solid fa-shopping-cart"></i> <span>Quản lý đơn hàng</span>
    </a>

    <a href="${pageContext.request.contextPath}/" class="admin-menu-item logout">
      <i class="fa-solid fa-right-from-bracket"></i> <span>Về Website</span>
    </a>
  </nav>
</aside>