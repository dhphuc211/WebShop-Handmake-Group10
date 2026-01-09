<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm | Nhóm 10</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productdetail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/hero-section.css">
</head>
<body>
<%@ include file="compenents/header.jsp" %>
<c:set var="pageTitle" value="${product.name}" scope="request" />
<c:set var="breadcrumbText" value="<a href='${pageContext.request.contextPath}/products.jsp'>Sản phẩm</a> / ${product.name}" scope="request" />

<jsp:include page="compenents/hero-section.jsp" />

<div class="product-page">
    <section class="product-details">
        <div class="container">
            <div class="product-gallery">
                <div class="product-img">
                    <a href="#">
                        <img id="main-display" src="${product.imageUrl}" alt="${product.name}">
                    </a>
                </div>
            </div>

            <div class="product-detail-info">
                <div class="product-name">
                    <h2>${product.name}</h2>
                </div>
                <div class="inventory">
                    <span>Tình trạng: </span>
                    <span style="color: green;">${product.stock > 0 ? 'Còn hàng' : 'Hết hàng'}</span>
                </div>
                <div class="product-price">
                    <div class="prices">
                        <span class="price">
                            <fmt:setLocale value="vi_VN"/>
                            <fmt:formatNumber value="${product.price}" type="currency"/>
                        </span>
                    </div>
                </div>

                <div class="product-amount-cart">
                    <div class="quantity">
                        <label for="quantity">Số lượng: </label>
                        <input type="number" id="quantity" value="1" min="1" max="${product.stock}">
                    </div>
                    <div class="cart">
                        <button type="button" ${product.stock == 0 ? 'disabled' : ''}>Thêm vào giỏ hàng</button>
                    </div>
                </div>

                <div class="product-attributes-short">
                    <h3>Thông tin chi tiết</h3>
                    <ul>
                        <li><strong>Xuất
                            xứ:</strong> ${not empty product.attribute.origin ? product.attribute.origin : 'Đang cập nhật'}
                        </li>
                        <li><strong>Chất
                            liệu:</strong> ${not empty product.attribute.material ? product.attribute.material : 'Đang cập nhật'}
                        </li>
                        <li><strong>Kích
                            thước:</strong> ${not empty product.attribute.size ? product.attribute.size : 'Đang cập nhật'}
                        </li>
                        <li><strong>Trọng
                            lượng:</strong> ${not empty product.attribute.weight ? product.attribute.weight : '0'}
                        </li>
                        <li><strong>Màu
                            sắc:</strong> ${not empty product.attribute.color ? product.attribute.color : 'Đang cập nhật'}
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <section class="product-description">
        <div class="container">
            <div class="product-tab">
                <ul>
                    <li class="tab-link active"><h3>Mô tả sản phẩm</h3></li>
                </ul>
                <div class="tab">
                    <div class="tab-content">
                        <div class="product-content">
                            <p>${not empty product.fullDescription ? product.fullDescription : 'Chưa có mô tả chi tiết cho sản phẩm này.'}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="product-relate">
        <div class="container">
            <div class="product-relate">
                <div class="carousel">
                    <span>Sản phẩm liên quan</span>
                </div>
                <div class="product-grid">
                    <c:forEach items="${relatedProducts}" var="rp">
                        <div class="product-card">
                            <a href="${pageContext.request.contextPath}/productdetail?id=${rp.id}" class="product-link">
                                <div class="product-imgs">
                                    <img src="${rp.imageUrl}" alt="${rp.name}">
                                </div>
                                <div class="product-info">
                                    <h3>${rp.name}</h3>
                                    <span class="price">
                                        <fmt:formatNumber value="${rp.price}" type="currency"/>
                                    </span>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                    <c:if test="${empty relatedProducts}">
                        <p style="text-align: center; width: 100%;">Không có sản phẩm liên quan.</p>
                    </c:if>
                </div>
            </div>
        </div>
    </section>
</div>
<%@include file="compenents/footer.jsp"%>

<input type="checkbox" id="search-toggle" class="hidden-checkbox">
<label for="search-toggle" class="search-overlay"></label>

<div id="search-panel">
    <div class="search-panel-header">
        <label for="search-toggle" class="close-search-label">
            <i class="fa-solid fa-xmark"></i>
        </label>
    </div>

    <div class="search-panel-content">
        <div class="search-form">
            <input type="text" placeholder="Nhập tên sản phẩm...">
            <i class="fa-solid fa-magnifying-glass search-icon"></i>
        </div>

        <h3>Sản phẩm được tìm nhiều nhất</h3>

        <div class="search-results-list">
            <a href="${pageContext.request.contextPath}/search.jsp" class="search-result-item">
                <div class="search-item-image">
                    <img src="https://via.placeholder.com/60x60/f5f5f5/cccccc?text=Img" alt="Ca tháp">
                </div>
                <div class="search-item-info">
                    <p class="product-name">Ca tháp quai tròn 0.35 L - Jasmine - Trắng</p>
                    <p class="product-price">93.500đ</p>
                </div>
            </a>
        </div>
    </div>
</div>

<div id="cart-modal-overlay">
    <div class="cart-modal-container">
        <div class="cart-modal-header">
            <span><i class="fa-solid fa-cart-shopping"></i> Mua hàng thành công</span>
            <button id="modal-close-x" class="modal-close-btn">&times;</button>
        </div>

        <div class="cart-modal-body">
            <div class="modal-product-info">
                <img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg"
                     alt="product-image" id="modal-product-img">
                <div>
                    <p id="modal-product-name">Ca trà 0.30 L - Mẫu Đơn - Trắng</p>
                    <strong id="modal-product-price">108.000đ</strong>
                </div>
            </div>

            <p class="modal-cart-status">Giỏ hàng của bạn hiện có 1 sản phẩm</p>

            <div class="modal-actions">
                <button id="modal-continue-shopping" class="btn-continue">Tiếp tục mua hàng</button>
                <button id="modal-checkout" class="btn-checkout"><a
                        href="${pageContext.request.contextPath}/checkout.jsp">Thanh toán ngay</a></button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/hero-section.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {

        const openButton = document.querySelector('.product-detail-info .cart button');
        const modal = document.getElementById('cart-modal-overlay');
        const closeXBtn = document.getElementById('modal-close-x');
        const continueShoppingBtn = document.getElementById('modal-continue-shopping');

        function openModal() {
            if (modal) {
                modal.classList.add('show');
            }
        }

        function closeModal() {
            if (modal) {
                modal.classList.remove('show');
            }
        }

        if (openButton) {
            openButton.addEventListener('click', function (e) {
                e.preventDefault();
                openModal();
            });
        }

        if (closeXBtn) {
            closeXBtn.addEventListener('click', closeModal);
        }

        if (continueShoppingBtn) {
            continueShoppingBtn.addEventListener('click', closeModal);
        }

        if (modal) {
            modal.addEventListener('click', function (e) {
                if (e.target === modal) {
                    closeModal();
                }
            });
        }
    });
</script>
</body>
</html>