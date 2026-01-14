<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <title>Quên mật khẩu</title>
</head>
<body>
<main class="login-main">
    <div class="login-container">
        <div class="login-decoration">
            <div class="decoration-content">
                <h2>Đồ thủ công mỹ nghệ Việt</h2>
                <p>Lấy lại mật khẩu để tiếp tục mua sắm</p>
                <div class="decoration-pattern">
                    <i class="fa-solid fa-leaf"></i>
                    <i class="fa-solid fa-spa"></i>
                    <i class="fa-solid fa-leaf"></i>
                </div>
            </div>
        </div>

        <div class="login-form-wrapper">
            <div class="login-form-container">
                <div class="login-header">
                    <h1>Quên mật khẩu</h1>
                    <p>Nhập email hoặc SĐT để nhận hướng dẫn đặt lại mật khẩu</p>
                </div>

                <form class="login-form" action="${pageContext.request.contextPath}/forgot-password.jsp" method="post">
                    <div class="form-group">
                        <label for="emailOrPhone">
                            <i class="fa-solid fa-envelope"></i>
                            Email hoặc Số điện thoại
                        </label>
                        <input type="text" id="emailOrPhone" name="emailOrPhone" placeholder="Nhập email hoặc số điện thoại" required>
                    </div>

                    <button type="submit" class="btn-login">
                        <i class="fa-solid fa-paper-plane"></i>
                        Gửi yêu cầu
                    </button>
                </form>

                <div class="register-link">
                    <p>Đã nhớ mật khẩu? <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a></p>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
