<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="compenents/header.jsp" %>
<main class="component">
    <section class="section_intro">
        <img id="intro-bg"
             src="${not empty banners ? banners[0].image_url.trim() : ''}"
             class="intro-bg-img"
             alt="Background Banner">

        <div class="section_intro-content">
            <h1>Đồ thủ công mỹ nghệ</h1>
            <p>Tinh tế – Mộc mạc – Đậm bản sắc Việt</p>
            <a href="${pageContext.request.contextPath}/products" class="btn">Khám phá ngay</a>
        </div>
    </section>
    <section class="section_danhmuc">
        <div class="container">
            <div class="category-list">
                <div class="category-item">
                    <a href="${pageContext.request.contextPath}/products.jsp?cat=maytre">
                        <div class="category-image-wrapper">
                            <img src="https://denmaytre.net/wp-content/uploads/2019/12/san-pham-may-tre-dan-xuat-khau-lang-nghe-tang-tien.jpg.webp"
                                 alt="Mây tre đan">
                        </div>
                        <h3>Mây tre đan</h3>
                        <p>+ 15 sản phẩm</p>
                    </a>
                </div>

                <div class="category-item">
                    <a href="#">
                        <div class="category-image-wrapper">
                            <img src="https://cdn.pixabay.com/photo/2023/05/29/18/10/pottery-8026823_1280.jpg"
                                 alt="Gốm sứ">
                        </div>
                        <h3>Gốm sứ</h3>
                        <p>+ 30 sản phẩm</p>
                    </a>
                </div>
                <div class="category-item">
                    <a href="#">
                        <div class="category-image-wrapper">
                            <img src="https://dntt.mediacdn.vn/197608888129458176/2023/7/14/27-1-1689328749793900374452.jpg"
                                 alt="Đồ gỗ mỹ nghệ">
                        </div>
                        <h3>Đồ gỗ mỹ nghệ</h3>
                        <p>+ 0 sản phẩm</p>
                    </a>
                </div>

                <div class="category-item">
                    <a href="#">
                        <div class="category-image-wrapper">
                            <img src="https://images.pexels.com/photos/236748/pexels-photo-236748.jpeg" alt="Dệt thêu">
                        </div>
                        <h3>Dệt thêu & may mặc</h3>
                        <p>+ 21 sản phẩm</p>
                    </a>
                </div>
            </div>

            <div class="promotions-container discount">
                <div class="section-header">
                    <h2>Khuyến mãi đặc biệt</h2>
                    <a href="#" class="view-all">Chương trình đã kết thúc, hẹn gặp lại! →</a>
                </div>

                <div class="product-grid">
                    <div class="product-card">
                        <a href="#" class="product-link">
                            <div class="product-img">
                                <span class="sale">Giảm 44%</span>
                                <img src="https://bizweb.dktcdn.net/100/485/241/themes/911577/assets/danhmuc_4.jpg?1758008990171"
                                     alt="Bộ khay mứt">
                            </div>
                            <div class="product-info">
                                <h3>Bộ khay mứt 31.5cm Thiên Kim</h3>
                                <span class="price">1.688.500đ</span>
                                <del class="compare-price">3.000.000đ</del>
                            </div>
                        </a>
                    </div>
                    <div class="product-card">
                        <a href="#" class="product-link">
                            <div class="product-img">
                                <span class="sale">Giảm 62%</span>
                                <img src="https://bizweb.dktcdn.net/100/485/241/themes/911577/assets/danhmuc_4.jpg?1758008990171"
                                     alt="Bộ trà">
                            </div>
                            <div class="product-info">
                                <h3>Bộ trà 0.35 L - IFP - Chỉ Vàng</h3>
                                <span class="price">764.500đ</span>
                                <del class="compare-price">2.000.000đ</del>
                            </div>
                        </a>
                    </div>
                </div>
            </div>

            <div class="promotions-container">
                <div class="section-header featured-header">
                    <h2>Sản phẩm nổi bật</h2>
                    <nav class="filter-tabs">
                        <ul>
                            <li><a href="#" class="active">Tất cả</a></li>
                            <li><a href="#">Bộ Trà</a></li>
                            <li><a href="#">Cà phê</a></li>
                            <li><a href="#">Phụ kiện</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="product-grid">
                    <div class="product-card">
                        <a href="#" class="product-link">
                            <div class="product-img">
                                <span class="sale">Giảm 50%</span>
                                <img src="https://bizweb.dktcdn.net/100/485/241/themes/911577/assets/danhmuc_4.jpg?1758008990171"
                                     alt="Lọ Bud Vase">
                            </div>
                            <div class="product-info">
                                <h3>Lọ Bud Vase trong Silver Spruce</h3>
                                <span class="price">2.500.000đ</span>
                                <del class="compare-price">5.000.000đ</del>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="news-section">
        <div class="container">
            <div class="section-header">
                <h2>Tin tức mới nhất</h2>
                <p>Cập nhật những tin tức xu hướng mới nhất hiện nay.</p>
            </div>
            <div class="news-grid">
                <article class="news-card">
                    <div class="card-image-container">
                        <a href="${pageContext.request.contextPath}/news-detail.jsp">
                            <img src="https://bizweb.dktcdn.net/100/485/241/articles/blog2.jpg?v=1685960411533"
                                 alt="Hình ảnh bài viết">
                            <span class="date-badge"><i class="fa-regular fa-clock"></i> 17/11/2025</span>
                        </a>
                    </div>
                    <div class="card-content">
                        <h3><a href="#">Mẹo phát hiện ly cốc bát đĩa nhiễm độc</a></h3>
                        <a href="#" class="read-more">Xem thêm</a>
                    </div>
                </article>
            </div>
            <a href="${pageContext.request.contextPath}/news.jsp" class="btn-view-all">Xem Tất Cả</a>
        </div>
    </section>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const bgImg = document.getElementById('intro-bg');
            if (!bgImg) return;

            const rawImages = [
                <c:forEach var="url" items="${banners}" varStatus="status">
                '${url.image_url}'${!status.last ? ',' : ''}
                </c:forEach>
            ];

            const cleanImages = rawImages
                .map(img => img.replace(/\s/g, ''))
                .filter(img => img !== "");

            if (cleanImages.length === 0) return;

            let currentIndex = 0;

            function updateBanner(index) {
                const url = cleanImages[index];
                bgImg.src = url;
            }

            updateBanner(0);
            if (cleanImages.length > 1) {
                setInterval(() => {
                    currentIndex = (currentIndex + 1) % cleanImages.length;
                    updateBanner(currentIndex);
                }, 5000);
            }
        });
    </script>
</main>

<%@ include file="compenents/footer.jsp" %>