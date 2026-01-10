<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi"> <head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
  <title>Nhóm 10 | Trang chủ</title>
</head>
<body>
<header id="header">
  <div class="container">
    <a href="${pageContext.request.contextPath}/index.jsp" class="logo">
      <img src="https://suncraft.com.vn/suncraft/Suncraft_website_Inf/suncraft_logo/e6e59529-b1df-4676-a5b2-f3757e67957e.png" alt="Logo Suncraft">
    </a>
    <div class="menu">
      <li class="nav-item"><a href="${pageContext.request.contextPath}/index" class="active">Trang chủ</a></li>
      <li class="nav-item"><a href="${pageContext.request.contextPath}/about">Giới thiệu</a></li>

      <li class="nav-item list-product">
        <a href="${pageContext.request.contextPath}/products" class="caret-down a">
          Sản phẩm <i class="fa-solid fa-caret-down"></i>
        </a>
        <div class="dropdown">
          <div class="product-items">
            <div class="menu-product-item">
              <h4><a href="#">Đồ mây tre đan</a></h4>
              <ul>
                <li><a href="#">Giỏ</a></li>
                <li><a href="#">Đèn tre</a></li>
                <li><a href="#">Túi cói</a></li>
              </ul>
            </div>
            <div class="menu-product-item">
              <h4><a href="#">Gốm sứ</a></h4>
              <ul>
                <li><a href="#">Bình</a></li>
                <li><a href="#">Ấm chén</a></li>
                <li><a href="#">Tượng gốm</a></li>
              </ul>
            </div>
            <div class="menu-product-item">
              <h4><a href="#">Đồ gỗ mỹ nghệ</a></h4>
              <ul>
                <li><a href="#">Tượng</a></li>
                <li><a href="#">Hộp</a></li>
                <li><a href="#">Khung ảnh</a></li>
              </ul>
            </div>
            <div class="menu-product-item">
              <h4><a href="#">Dệt thêu & may mặc</a></h4>
              <ul>
                <li><a href="#">Khăn</a></li>
                <li><a href="#">Túi</a></li>
                <li><a href="#">Áo thổ cẩm</a></li>
              </ul>
            </div>
            <div class="menu-product-item">
              <h4><a href="#">Trang sức & phụ kiện</a></h4>
              <ul>
                <li><a href="#">Vòng</a></li>
                <li><a href="#">Dây chuyền</a></li>
                <li><a href="#">Nhẫn</a></li>
              </ul>
            </div>
            <div class="menu-product-item">
              <h4><a href="#">Quà tặng nghệ thuật</a></h4>
              <ul>
                <li><a href="#">Nến</a></li>
                <li><a href="#">Thiệp 3D</a></li>
                <li><a href="#">Tranh giấy</a></li>
              </ul>
            </div>
          </div>
          <div class="dropdown-image">
            <img width="300" src="https://bizweb.dktcdn.net/100/485/241/themes/911577/assets/megamenu_banner.png?1758008990171" alt="Gốm sứ Bát Tràng">
          </div>
        </div>
      </li>
      <li class="nav-item"><a href="${pageContext.request.contextPath}/news">Tin tức</a></li>
      <li class="nav-item"><a href="${pageContext.request.contextPath}/contact">Liên hệ</a></li>
    </div>
    <div class="others">
      <div class="others">
        <div class="icon">
          <label for="search-toggle" class="search-toggle-label">
            <i class="fa-solid fa-magnifying-glass"></i>
          </label>
        </div>
        <div class="icon user-menu-container">
          <a href="${pageContext.request.contextPath}/login" class="icon">
            <i class="fa-regular fa-user"></i>
          </a>
          <% if (session.getAttribute("user") != null) { %>
            <div class="user-dropdown">
              <div class="user-info-header">
                <span>Xin chào, <strong>${sessionScope.user.fullName}</strong></span>
              </div>
              <hr>
              <ul>
                <li><a href="${pageContext.request.contextPath}/account/dashboard.jsp"><i class="fa-regular fa-address-card"></i> Thông tin cá nhân</a></li>
                <li><a href="${pageContext.request.contextPath}/logout" class="logout-link"><i class="fa-solid fa-right-from-bracket"></i> Đăng xuất</a></li>
              </ul>
            </div>
          <% } %>
        </div>
        <a href="${pageContext.request.contextPath}/wishlist" class="icon badge" >
          <i class="fa-regular fa-heart"></i>
          <span>0</span>
        </a>
        <a href="${pageContext.request.contextPath}/shopping-cart" class="icon badge">
          <i class="fa-solid fa-cart-shopping"></i>
          <span>0</span>
        </a>
      </div>
    </div>
  </div>
</header>