<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh toán</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
</head>
<body>

<main class="checkout-layout">
    <div class="checkout-main">
        <header class="header">
            <a href="${pageContext.request.contextPath}/index.jsp" class="logo">
                <img src="https://suncraft.com.vn/suncraft/Suncraft_website_Inf/suncraft_logo/e6e59529-b1df-4676-a5b2-f3757e67957e.png" alt="Suncraft Logo">
            </a>
        </header>

        <form id="checkoutForm" action="checkout" method="POST" class="container">

            <div class="checkout-info-left">
                <div class="checkout-info">
                    <div class="section-header">
                        <h3>Thông tin nhận hàng</h3>
                    </div>

                    <div class="info-form">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" placeholder="Email"
                                   value="${sessionScope.user != null ? sessionScope.user.email : ''}" required>
                        </div>

                        <div class="form-group">
                            <label for="full-name">Họ và tên</label>
                            <input type="text" name="fullname" id="full-name" placeholder="Họ và tên"
                                   value="${sessionScope.user != null ? sessionScope.user.fullName : ''}" required>
                        </div>

                        <div class="form-group">
                            <label for="phone">Số điện thoại</label>
                            <div class="phone-input-group">
                                <input type="tel" name="phone" id="phone" placeholder="Số điện thoại"
                                       value="${sessionScope.user != null ? sessionScope.user.phone : ''}" required>
                                <select class="country-code">
                                    <option>🇻🇳</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="address">Địa chỉ</label>
                            <input type="text" name="address" id="address" placeholder="Địa chỉ (Số nhà, đường...)" required>
                        </div>

                        <div class="form-group">
                            <label for="province">Tỉnh / Thành phố</label>
                            <select id="province" name="province">
                                <option value="" selected disabled>Chọn Tỉnh / Thành phố</option>
                                <option value="Hồ Chí Minh">Hồ Chí Minh</option>
                                <option value="Hà Nội">Hà Nội</option>
                                <option value="Đà Nẵng">Đà Nẵng</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="district">Quận / Huyện</label>
                            <select id="district" name="district">
                                <option value="" selected disabled>Chọn Quận / Huyện</option>
                                <option value="Quận 1">Quận 1</option>
                                <option value="Thủ Đức">Thủ Đức</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="ward">Phường / Xã</label>
                            <select id="ward" name="ward">
                                <option value="" selected disabled>Chọn Phường / Xã</option>
                                <option value="Linh Trung">Linh Trung</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="note">Ghi chú</label>
                            <textarea id="note" name="note" rows="3" placeholder="Ghi chú (tùy chọn)"></textarea>
                        </div>
                    </div>
                </div>
            </div>

            <div class="checkout-info-right">
                <h3>Vận chuyển</h3>
                <div class="checkout-sidebars">
                    <div class="shipping-note">
                        <label for="shipping-note" class="payment-option">
                            <input type="radio" id="shipping-note" checked>
                            <span>Giao hàng tận nơi</span>
                        </label>
                        <div class="shipping-price">
                            <span>30.000₫</span>
                        </div>
                    </div>
                </div>

                <div class="payment">
                    <h3>Thanh toán</h3>
                    <div class="payment-options">
                        <label for="payment-cod" class="payment-option">
                            <input type="radio" id="payment-cod" name="paymentMethod" value="COD" checked>
                            <span>Thanh toán khi giao hàng (COD)</span>
                            <i class="fa fa-money-bill-alt"></i>
                        </label>
                        <div class="payment-info">
                            <span>Bạn chỉ phải thanh toán khi nhận được hàng</span>
                        </div>
                    </div>
                </div>
            </div>

        </form> </div>

    <div class="checkout-sidebar">
        <div class="order-info">
            <div class="order-amount">
                <h3>Đơn hàng (${sessionScope.cart.totalQuantity} sản phẩm)</h3>
            </div>

            <div class="order-product">
                <div class="order-summary" style="max-height: 400px; overflow-y: auto;">

                    <c:forEach var="item" items="${sessionScope.cart.items}">
                        <div class="cart-row">
                            <div class="cart-items">
                                <div class="cart-image" style="position: relative;">
                                    <a href="#" class="cart-item">
                                        <img src="${item.product.image}" alt="${item.product.name}" onerror="this.src='https://via.placeholder.com/60'">
                                    </a>
                                    <span class="quantity" style="position: absolute; top: -5px; right: -5px; background: #2a9dcc; color: #fff; border-radius: 50%; width: 20px; height: 20px; display: flex; align-items: center; justify-content: center; font-size: 11px;">${item.quantity}</span>
                                </div>
                                <div class="cart-info">
                                    <a href="#" class="cart-item-name">${item.product.name}</a>
                                </div>
                            </div>
                            <div class="price-total">
                                <span><fmt:formatNumber value="${(item.product.salePrice > 0 ? item.product.salePrice : item.product.price) * item.quantity}" type="currency" currencySymbol="₫" maxFractionDigits="0"/></span>
                            </div>
                        </div>
                    </c:forEach>

                </div>

                <div class="order-total">
                    <div class="order-total-top">
                        <span>Tạm tính</span>
                        <span class="price">
                            <fmt:formatNumber value="${sessionScope.cart.totalMoney}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                        </span>
                    </div>
                    <div class="order-total-bottom">
                        <span>Phí vận chuyển</span>
                        <span class="price">30.000₫</span>
                    </div>
                </div>

                <div class="order-price">
                    <div class="order-price-top">
                        <span>Tổng cộng</span>
                        <span class="price" style="color: #d0021b; font-size: 20px; font-weight: bold;">
                            <fmt:formatNumber value="${sessionScope.cart.totalMoney + 30000}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                        </span>
                    </div>
                    <div class="order-price-bottom">
                        <a href="${pageContext.request.contextPath}/shopping-cart.jsp" class="back-to-cart">
                            <span><i class="fa-solid fa-chevron-left"></i> Quay về giỏ hàng</span>
                        </a>

                        <button type="submit" form="checkoutForm" class="btn-order" style="border: none; cursor: pointer;">Đặt hàng</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

</body>
</html>