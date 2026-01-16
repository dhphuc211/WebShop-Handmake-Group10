<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <title>Đăng nhập</title>
</head>
<body>
<main class="login-main">
    <div class="login-container">
        <!-- Left side - Decorative -->
        <div class="login-decoration">
            <div class="decoration-content">
                <h2>Đồ thủ công mỹ nghệ Việt</h2>
                <p>Tinh tế trong từng chi tiết, mang đậm bản sắc Việt</p>
                <div class="decoration-pattern">
                    <i class="fa-solid fa-leaf"></i>
                    <i class="fa-solid fa-spa"></i>
                    <i class="fa-solid fa-leaf"></i>
                </div>
            </div>
        </div>

        <!-- Right side - Login Form -->
        <div class="login-form-wrapper">
            <div class="login-form-container">
                <div class="login-header">
                    <h1>Đăng Nhập</h1>
                    <p>Chào mừng bạn quay trở lại</p>
                </div>

                <!-- Hiển thị thông báo -->
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">
                        <i class="fa-solid fa-check-circle"></i> ${successMessage}
                    </div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-error">
                        <i class="fa-solid fa-exclamation-circle"></i> ${errorMessage}
                    </div>
                </c:if>

                <form class="login-form" id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group">
                        <label for="emailOrPhone">
                            <i class="fa-solid fa-envelope"></i>
                            Email hoặc Số điện thoại
                        </label>
                        <input type="text" id="emailOrPhone" name="emailOrPhone"
                               placeholder="Nhập email hoặc số điện thoại" required>
                    </div>

                    <div class="form-group">
                        <label for="password">
                            <i class="fa-solid fa-lock"></i>
                            Mật khẩu
                        </label>
                        <div class="password-input-wrapper">
                            <input type="password" id="password" name="password"
                                   placeholder="Nhập mật khẩu" required>
                            <button type="button" class="toggle-password">
                                <i class="fa-solid fa-eye" id="toggleIcon"></i>
                            </button>
                        </div>
                    </div>

                    <div class="form-options">
                        <label class="remember-me">
                            <input type="checkbox" id="rememberMe" name="rememberMe">
                            <span>Ghi nhớ đăng nhập</span>
                        </label>
                        <a href="${pageContext.request.contextPath}/forgot-password" class="forgot-password">Quên mật khẩu?</a>
                    </div>

                    <button type="submit" class="btn-login">
                        <i class="fa-solid fa-right-to-bracket"></i> Đăng nhập
                    </button>
                </form>

                <div class="divider">
                    <span>Hoặc đăng nhập bằng</span>
                </div>

                <div class="social-login">
                    <button type="button" class="btn-social btn-facebook">
                        <i class="fa-brands fa-facebook-f"></i> Facebook
                    </button>
                    <button type="button" class="btn-social btn-google">
                        <i class="fa-brands fa-google"></i> Google
                    </button>
                </div>

                <div class="register-link">
                    <p>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a></p>
                </div>

                <div class="admin-login-link">
                    <a href="${pageContext.request.contextPath}/admin/admin-login">
                        <i class="fa-solid fa-user-shield"></i>
                        Đăng nhập với tư cách admin
                    </a>
                </div>
            </div>
        </div>
    </div>
</main>

<script>
    // Toggle password visibility
    document.querySelector('.toggle-password').addEventListener('click', function() {
        const passwordInput = document.getElementById('password');
        const toggleIcon = document.getElementById('toggleIcon');

        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            toggleIcon.classList.remove('fa-eye');
            toggleIcon.classList.add('fa-eye-slash');
        } else {
            passwordInput.type = 'password';
            toggleIcon.classList.remove('fa-eye-slash');
            toggleIcon.classList.add('fa-eye');
        }
    });
</script>
</body>
</html>
