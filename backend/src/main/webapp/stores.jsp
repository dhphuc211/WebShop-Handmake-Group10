<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hệ thống cửa hàng</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stores.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/hero-section.css">
</head>
<body data-context-path="${pageContext.request.contextPath}">
<!-- Header -->
<jsp:include page="/compenents/header.jsp" />

<main>
    <!-- Hero Section -->
    <c:set var="pageTitle" value="Vị trí cửa hàng" scope="request" />
    <c:set var="breadcrumbText" value="Vị trí cửa hàng" scope="request" />
    <jsp:include page="/compenents/hero-section.jsp" />

    <div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <div class="store-list">
                <div class="store-card">
                    <div class="store-header">Cửa hàng đồ thủ công mỹ nghệ</div>
                    <div class="store-address">Địa chỉ: Linh Trung, Thủ Đức, TP.HCM</div>
                    <div class="store-hotline">Hotline: <span>1900 0000</span></div>
                </div>
            </div>
        </div>

        <!-- Map Section -->
        <div class="map-section">
            <div id="map">
                <iframe
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.6456!2d106.6677!3d10.7626!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zMTDCsDQ1JzQ1LjQiTiAxMDbCsDQwJzAzLjciRQ!5e0!3m2!1svi!2s!4v1234567890"
                        allowfullscreen="" loading="lazy"></iframe>
            </div>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="/compenents/footer.jsp" />
</body>
</html>
