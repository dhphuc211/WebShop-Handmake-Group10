<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sản phẩm | Nhóm 10</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/products.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/hero-section.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
</head>
<body>
<c:set var="activeTab" value="products" scope="request"/>
<%@ include file="compenents/header.jsp" %>
<c:set var="pageTitle" value="Sản phẩm" scope="request"/>
<c:set var="breadcrumbText"
       value="<a href='${pageContext.request.contextPath}/products'>Sản phẩm</a>"
       scope="request"/>
<jsp:include page="compenents/hero-section.jsp"/>

<div class="products-wrapper">
    <section class="filter-bar">
        <div class="filter-controls">
            <select name="danh-muc" id="category">
                <option selected> Danh mục</option>
                <option>Trà - cà phê</option>
                <option>Nồi sứ dưỡng sinh</option>
                <option>Sứ dưỡng sinh</option>
                <option>Phụ kiện bàn ăn</option>
                <option>Sứ nghệ thuật</option>
            </select>
            <div class="select">
                <select name="bo-loc" id="bo-loc">
                    <option selected>Bộ lọc</option>
                </select>
            </div>
        </div>

        <div class="sort-control">
            <span id="sor">Sắp xếp: </span>
            <button type="button" class="sort-btn">
                <i class="fa-solid fa-arrow-down-a-z"></i>
                <span>A-Z</span>
            </button>
            <button type="button" class="sort-btn">
                <i class="fa-solid fa-arrow-up-z-a"></i>
                <span>Z-A</span>
            </button>
            <button type="button" class="sort-btn">
                <i class="fa-solid fa-arrow-up-right-dots"></i>
                <span>Giá tăng dần</span>
            </button>
            <button type="button" class="sort-btn">
                <i class="fa-solid fa-chart-line"></i>
                <span>Giá giảm dần</span>
            </button>
        </div>
    </section>

    <main class="product-list">
        <div class="container">
            <div class="product-grid">
                <c:if test="${empty productList}">
                    <p style="text-align: center; width: 100%;">Không tìm thấy sản phẩm nào.</p>
                </c:if>
                <c:forEach items="${productList}" var="p">
                    <div class="product-card">
                        <a href="${pageContext.request.contextPath}/productdetail?id=${p.id}" class="product-link">
                            <div class="product-img">
                                <c:if test="${p.price > 0}">
                                </c:if>
                                <img src="${p.imageUrl}" alt="${p.name}">
                            </div>
                            <div class="product-info">
                                <h3>${p.name}</h3>
                                <span class="price">
                    <fmt:setLocale value="vi_VN"/>
                    <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="đ"/>
                  </span>
                                    <%-- Giá gốc (Nếu có logic giá sale thì thêm vào đây) --%>
                                    <%-- <del class="compare-price">2.000.000₫</del> --%>
                            </div>
                        </a>
                    </div>
                </c:forEach>

            </div>
        </div>
        <div class="pagination-area">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="products?page=${currentPage - 1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <li class="page-item ${currentPage == 1 ? 'active' : ''}">
                        <a class="page-link" href="products?page=1">1</a>
                    </li>
                    <c:if test="${currentPage > 3}">
                        <li class="page-item disabled"><span class="page-link">...</span></li>
                    </c:if>

                    <c:forEach begin="${currentPage - 1 > 2 ? currentPage - 1 : 2}"
                               end="${currentPage + 1 < totalPages ? currentPage + 1 : totalPages - 1}"
                               var="i">
                        <c:if test="${i > 1 && i < totalPages}">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="products?page=${i}">${i}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${currentPage < totalPages - 2}">
                        <li class="page-item disabled"><span class="page-link">...</span></li>
                    </c:if>
                    <c:if test="${totalPages > 1}">
                        <li class="page-item ${currentPage == totalPages ? 'active' : ''}">
                            <a class="page-link" href="products?page=${totalPages}">${totalPages}</a>
                        </li>
                    </c:if>
                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="products?page=${currentPage + 1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </main>
</div>

<footer id="footer">
    <div class="container">
        <div class="content">
            <div class="info">
                <div class="info_details">
                    <h2>Thông tin</h2>
                    <div>
                        <i class="fa-solid fa-location-dot"></i>
                        <p>Trường Đại học Nông Lâm Tp.Hcm</p>
                    </div>
                    <div>
                        <i class="fa-solid fa-phone"></i>
                        <p>0337429995</p></div>
                    <div>
                        <i class="fa-solid fa-envelope"></i>
                        <p>23130240@st.hcmuaf.edu.vn</p></div>
                </div>

                <div class="info_media">
                    <h2>Mạng xã hội</h2>
                    <div class="social-icons">
                        <a href="#" class="social-icon zalo" aria-label="Zalo"><strong>Za</strong></a>
                        <a href="#" class="social-icon fb" aria-label="Facebook"><i class="fa-brands fa-facebook-f"></i></a>
                        <a href="#" class="social-icon yt" aria-label="YouTube"><i class="fa-brands fa-youtube"></i></a>
                        <a href="#" class="social-icon gg" aria-label="Google"><i class="fa-brands fa-google"></i></a>
                    </div>
                </div>
            </div>

            <div class="policy-guide-wrapper">
                <div class="component policy">
                    <h2>Chính sách</h2>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/policy/security.jsp" class="hover color">Chính
                            sách bảo mật</a></li>
                        <li><a href="${pageContext.request.contextPath}/policy/transport.jsp" class="hover color">Chính
                            sách vận chuyển</a></li>
                        <li><a href="${pageContext.request.contextPath}/policy/change.jsp" class="hover color">Chính
                            sách đổi trả</a></li>
                        <li><a href="${pageContext.request.contextPath}/policy/regulation-use.jsp" class="hover color">Quy
                            định sử dụng</a></li>
                    </ul>
                </div>
                <div class="component guide">
                    <h2>Hướng dẫn</h2>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/guide/purchase.jsp" class="hover color">Hướng
                            dẫn mua hàng</a></li>
                        <li><a href="${pageContext.request.contextPath}/guide/payment.jsp" class="hover color">Hướng dẫn
                            thanh toán</a></li>
                        <li><a href="${pageContext.request.contextPath}/guide/delivery.jsp" class="hover color">Hướng
                            dẫn giao nhận</a></li>
                        <li><a href="${pageContext.request.contextPath}/guide/clause.jsp" class="hover color">Điều khoản
                            sử dụng</a></li>
                    </ul>
                </div>
            </div>

            <div class="others">
                <h2>Đăng ký nhận tin</h2>
                <p>Đăng ký ngay! Để nhận nhiều ưu đãi</p>
                <div class="input">
                    <input type="text" name="Email" id="email" placeholder="Nhập địa chỉ email">
                    <button>Đăng ký</button>
                </div>
            </div>
        </div>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/js/hero-section.js"></script>

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
                    <img src="#" alt="Ca tháp">
                </div>
                <div class="search-item-info">
                    <p class="product-name">Ca tháp quai tròn 0.35 L - Jasmine - Trắng</p>
                    <p class="product-price">93.500đ</p>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>