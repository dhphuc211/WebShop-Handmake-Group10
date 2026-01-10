<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    <title>Đăng Ký Tài Khoản</title>
</head>
<body>
<main class="register-main">
    <div class="register-container">
        <!-- Left side - Decorative -->
        <div class="register-decoration">
            <div class="star-container">
                <div class="star-shape">
                    <img src="https://i.pinimg.com/1200x/df/8d/24/df8d24cf36eb3e03aa91e94c71bbccaf.jpg" class="star-image" alt="Vietnamese Craft">
                </div>
                <div class="circular-text">
                    <p>
                        <span style="--i:1">M</span><span style="--i:2">ỗ</span><span style="--i:3">i</span><span style="--i:4"> </span>
                        <span style="--i:5">s</span><span style="--i:6">ả</span><span style="--i:7">n</span><span style="--i:8"> </span>
                        <span style="--i:9">p</span><span style="--i:10">h</span><span style="--i:11">ẩ</span><span style="--i:12">m</span><span style="--i:13"> </span>
                        <span style="--i:14">l</span><span style="--i:15">à</span><span style="--i:16"> </span>
                        <span style="--i:17">m</span><span style="--i:18">ộ</span><span style="--i:19">t</span><span style="--i:20"> </span>
                        <span style="--i:21">c</span><span style="--i:22">â</span><span style="--i:23">u</span><span style="--i:24"> </span>
                        <span style="--i:25">c</span><span style="--i:26">h</span><span style="--i:27">u</span><span style="--i:28">y</span><span style="--i:29">ệ</span><span style="--i:30">n</span><span style="--i:31"> </span>
                        <span style="--i:32">•</span><span style="--i:33"> </span>
                        <span style="--i:34">M</span><span style="--i:35">ỗ</span><span style="--i:36">i</span><span style="--i:37"> </span>
                        <span style="--i:38">h</span><span style="--i:39">ọ</span><span style="--i:40">a</span><span style="--i:41"> </span>
                        <span style="--i:42">t</span><span style="--i:43">i</span><span style="--i:44">ế</span><span style="--i:45">t</span><span style="--i:46"> </span>
                        <span style="--i:47">l</span><span style="--i:48">à</span><span style="--i:49"> </span>
                        <span style="--i:50">m</span><span style="--i:51">ộ</span><span style="--i:52">t</span><span style="--i:53"> </span>
                        <span style="--i:54">t</span><span style="--i:55">â</span><span style="--i:56">m</span><span style="--i:57"> </span>
                        <span style="--i:58">h</span><span style="--i:59">ồ</span><span style="--i:60">n</span><span style="--i:61"> </span>
                        <span style="--i:62">•</span><span style="--i:63"> </span>
                    </p>
                </div>
            </div>
        </div>

        <!-- Right side - Registration Form -->
        <div class="register-form-wrapper">
            <div class="register-form-container">
                <div class="register-header">
                    <h1>Đăng Ký Tài Khoản</h1>
                    <p>Tạo tài khoản để trải nghiệm mua sắm tuyệt vời</p>
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


                <form class="register-form" id="registerForm" action="${pageContext.request.contextPath}/register" method="post">
                    <div class="form-group">
                        <label for="fullName">
                            <i class="fa-solid fa-user"></i>
                            Họ và tên
                        </label>
                        <input type="text" id="fullName" name="fullName" value="${fullName}"
                               placeholder="Nhập họ và tên đầy đủ" required>
                    </div>

                    <div class="form-group">
                        <label for="email">
                            <i class="fa-solid fa-envelope"></i>
                            Email
                        </label>
                        <input type="email" id="email" name="email" value="${email}"
                               placeholder="example@email.com" required>
                    </div>

                    <div class="form-group">
                        <label for="phone">
                            <i class="fa-solid fa-phone"></i>
                            Số điện thoại
                        </label>
                        <input type="tel" id="phone" name="phone" value="${phone}"
                               placeholder="Nhập số điện thoại" pattern="[0-9]{10,11}" required>
                    </div>

                    <div class="form-group">
                        <label for="password">
                            <i class="fa-solid fa-lock"></i>
                            Mật khẩu
                        </label>
                        <div class="password-input-wrapper">
                            <input type="password" id="password" name="password"
                                   placeholder="Nhập mật khẩu (tối thiểu 6 ký tự)" minlength="6" required>
                            <button type="button" class="toggle-password" data-target="password">
                                <i class="fa-solid fa-eye"></i>
                            </button>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="confirmPassword">
                            <i class="fa-solid fa-lock"></i>
                            Xác nhận mật khẩu
                        </label>
                        <div class="password-input-wrapper">
                            <input type="password" id="confirmPassword" name="confirmPassword"
                                   placeholder="Nhập lại mật khẩu" minlength="6" required>
                            <button type="button" class="toggle-password" data-target="confirmPassword">
                                <i class="fa-solid fa-eye"></i>
                            </button>
                        </div>
                    </div>

                    <div class="form-options">
                        <label class="terms-checkbox">
                            <input type="checkbox" id="agreeTerms" name="agreeTerms" required>
                            <span>Tôi đồng ý với <a href="#" class="terms-link">điều khoản sử dụng</a> và <a href="#" class="terms-link">Chính sách bảo mật</a></span>
                        </label>
                    </div>

                    <button type="submit" class="btn-register">
                        <i class="fa-solid fa-user-plus"></i> Đăng ký
                    </button>
                </form>

                <div class="divider">
                    <span>Hoặc đăng ký bằng</span>
                </div>

                <div class="social-login">
                    <button type="button" class="btn-social btn-facebook">
                        <i class="fa-brands fa-facebook-f"></i> Facebook
                    </button>
                    <button type="button" class="btn-social btn-google">
                        <i class="fa-brands fa-google"></i> Google
                    </button>
                </div>

                <div class="login-link">
                    <p>Bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập ngay</a></p>
                </div>
            </div>
        </div>
    </div>
</main>

<script>
    // Toggle password visibility
    document.querySelectorAll('.toggle-password').forEach(button => {
        button.addEventListener('click', function() {
            const targetId = this.getAttribute('data-target');
            const passwordInput = document.getElementById(targetId);
            const icon = this.querySelector('i');

            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                passwordInput.type = 'password';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        });
    });

    // Validate password match
    document.getElementById('registerForm').addEventListener('submit', function(e) {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (password !== confirmPassword) {
            e.preventDefault();
            alert('Mật khẩu xác nhận không khớp!');
        }
    });
</script>
</body>
</html>
