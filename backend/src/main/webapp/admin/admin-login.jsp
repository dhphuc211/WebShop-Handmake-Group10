<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Đăng nhập quản trị</title>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/admin-login.css">
</head>

<body>

<main class="admin-login-main">
  <div class="login-card">
    <div class="login-header">
      <div class="admin-logo">
        <i class="fa-solid fa-shield-halved"></i>
      </div>
      <h1>Đăng Nhập Quản Trị</h1>
      <p>Chào mừng trở lại! Vui lòng đăng nhập để tiếp tục.</p>

      <%-- Hiển thị thông báo lỗi nếu đăng nhập thất bại --%>
      <% if (request.getAttribute("errorMessage") != null) { %>
      <p style="color: #ff4d4d; font-size: 0.9rem; margin-top: 10px;">
        <%= request.getAttribute("errorMessage") %>
      </p>
      <% } %>
    </div>

    <%-- Đổi method sang POST để bảo mật mật khẩu, action trỏ tới Servlet xử lý --%>
    <form action="${pageContext.request.contextPath}/admin-login" method="post" class="login-form">
      <div class="form-group">
        <label for="username">Tên đăng nhập</label>
        <div class="input-wrapper">
          <i class="fa-solid fa-user input-icon"></i>
          <input type="text" id="username" name="username"
                 placeholder="Nhập tên đăng nhập hoặc email" required>
        </div>
      </div>

      <div class="form-group">
        <label for="password">Mật khẩu</label>
        <div class="input-wrapper">
          <i class="fa-solid fa-lock input-icon"></i>
          <input type="password" id="password" name="password"
                 placeholder="Nhập mật khẩu" required>
        </div>
      </div>

      <div class="form-options">
        <label class="remember-me">
          <input type="checkbox" id="rememberMe" name="rememberMe">
          <span>Ghi nhớ tôi</span>
        </label>
        <a href="#" class="forgot-password">Quên mật khẩu?</a>
      </div>

      <button type="submit" class="btn-login">
        Đăng nhập
      </button>
    </form>

    <div class="login-footer">
      <a href="${pageContext.request.contextPath}/index.jsp" class="back-link">
        <i class="fa-solid fa-arrow-left"></i>
        Quay lại trang chủ
      </a>
    </div>
  </div>
</main>

</body>
</html>