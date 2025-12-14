<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<header id="header">
    <div class="container">
        <a href="${pageContext.request.contextPath}/index.jsp" class="logo">
            <img src="https://suncraft.com.vn/suncraft/Suncraft_website_Inf/suncraft_logo/e6e59529-b1df-4676-a5b2-f3757e67957e.png" alt="Suncraft Logo">
        </a>
        <div class="menu">
            <li class="nav-item"><a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></li>
            <li class="nav-item"><a href="${pageContext.request.contextPath}/about.jsp">Giới thiệu</a></li>

            <li class="nav-item list-product">
                <a href="${pageContext.request.contextPath}/products.jsp" class="caret-down a">
                    Sản phẩm
                    <i class="fa-solid fa-caret-down"></i>
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
                            <h4><a href="#">Dệt thêu & may mặc thủ công</a></h4>
                            <ul>
                                <li><a href="#">Khăn</a></li>
                                <li><a href="#">Túi</a></li>
                                <li><a href="#">Áo thổ cẩm</a></li>
                            </ul>
                        </div>

                        <div class="menu-product-item">
                            <h4><a href="#">Trang sức & phụ kiện thủ công</a></h4>
                            <ul>
                                <li><a href="#">Vòng</a></li>
                                <li><a href="#">Dây chuyền</a></li>
                                <li><a href="#">Nhẫn</a></li>
                            </ul>
                        </div>

                        <div class="menu-product-item">
                            <h4><a href="#">Đồ trang trí & quà tặng nghệ thuật</a></h4>
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
            <li class="nav-item"><a href="${pageContext.request.contextPath}/news.jsp">Tin tức</a></li>
            <li class="nav-item"><a href="${pageContext.request.contextPath}/stores.jsp">Cửa hàng</a></li>
            <li class="nav-item"><a href="${pageContext.request.contextPath}/contact.jsp">Liên hệ</a></li>
        </div>
        <div class="others">
            <div class="others">
                <div class="icon">
                    <label for="search-toggle" class="search-toggle-label">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </label>
                </div>
                <a href="${pageContext.request.contextPath}/login.jsp" class="icon">
                    <i class="fa-regular fa-user"></i>
                </a>
                <a href="${pageContext.request.contextPath}/wishlist.jsp" class="icon badge" >
                    <i class="fa-regular fa-heart"></i>
                    <span>0</span>
                </a>
                <a href="${pageContext.request.contextPath}/shopping-cart.jsp" class="icon badge">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <span>0</span>
                </a>
            </div>
        </div>
    </div>
</header>

<div class="hero-section-container">
    <section class="hero">
        <div class="hero-content">
            <h1>Bộ khay mứt 31.5cm Thiên Kim</h1>
            <div class="breadcrumb">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a> /
                <a href="${pageContext.request.contextPath}/products.jsp">Sản phẩm</a> /
                Bộ khay mứt 31.5cm Thiên Kim
            </div>
        </div>
    </section>

    <div class="categories-wrapper">
        <button id="prevButton" class="nav-button prev-button">&#8249;</button>

        <div class="categories" id="categoriesContainer">
            <div class="category-item" data-category="may-tre-dan">
                <div class="category-circle">
                    <img src="https://denmaytre.net/wp-content/uploads/2019/12/san-pham-may-tre-dan-xuat-khau-lang-nghe-tang-tien.jpg.webp" alt="Đồ mây tre đan">
                </div>
                <div class="category-name">Đồ mây tre đan</div>
            </div>

            <div class="category-item" data-category="gom-su">
                <div class="category-circle">
                    <img src="https://cdn.pixabay.com/photo/2023/05/29/18/10/pottery-8026823_1280.jpg" alt="Gốm sứ">
                </div>
                <div class="category-name">Gốm sứ</div>
            </div>

            <div class="category-item" data-category="go-my-nghe">
                <div class="category-circle">
                    <img src="https://dntt.mediacdn.vn/197608888129458176/2023/7/14/27-1-1689328749793900374452.jpg" alt="Đồ gỗ mỹ nghệ">
                </div>
                <div class="category-name">Đồ gỗ mỹ nghệ</div>
            </div>

            <div class="category-item" data-category="det-theu">
                <div class="category-circle">
                    <img src="https://images.pexels.com/photos/236748/pexels-photo-236748.jpeg" alt="Dệt thêu & may mặc thủ công">
                </div>
                <div class="category-name">Dệt thêu & may mặc thủ công</div>
            </div>

            <div class="category-item" data-category="trang-suc">
                <div class="category-circle">
                    <img src="https://nvhphunu.vn/wp-content/uploads/2023/12/2023-07-29_0000478.png" alt="Trang sức & phụ kiện thủ công">
                </div>
                <div class="category-name">Trang sức & phụ kiện thủ công</div>
            </div>

            <div class="category-item" data-category="trang-tri">
                <div class="category-circle">
                    <img src="https://chus.vn/images/detailed/239/10237_21_F1.jpg" alt="Đồ trang trí & quà tặng nghệ thuật">
                </div>
                <div class="category-name">Đồ trang trí & quà tặng nghệ thuật</div>
            </div>
        </div>

        <button id="nextButton" class="nav-button next-button">&#8250;</button>
    </div>
</div>

<div class="product-page">
    <section class="product-details">
        <div class="container">
            <div class="product-gallery">
                <div class="product-img">
                    <a href="#">
                        <img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg" alt="botra">
                    </a>
                </div>
                <ul class="product-imgs">
                    <li>
                        <a href="#">
                            <img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg" alt="botra" class="active">
                        </a>
                    </li>
                    <li><a href="#"><img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg" alt="botra"></a></li>
                    <li><a href="#"><img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg" alt="botra"></a></li>
                    <li><a href="#"><img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg" alt="botra"></a></li>
                    <li><a href="#"><img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg" alt="botra"></a></li>
                </ul>
            </div>

            <div class="product-detail-info">
                <div class="product-name">
                    <h2>Bộ khay mứt 31.5cm Thiên Kim
                        <a href="${pageContext.request.contextPath}/wishlist.jsp" class="icon badge" >
                            <i class="fa-regular fa-heart"></i>
                        </a>
                    </h2>
                </div>
                <div class="inventory">
                    <span>Tình trạng: </span>
                    <span> Còn hàng</span>
                </div>
                <div class="product-price">
                    <div class="prices">
                        <span class="price">1.688.500₫</span>
                        <del class="compare-price">3.000.000₫</del>
                    </div>
                    <div class="sale-info">
                        <span class="save-label">Tiết kiệm:</span>
                        <span class="save-amount">1.311.500₫</span>
                    </div>
                </div>
                <div class="product-amount-cart">
                    <div class="quantity">
                        <label for="quantity">Số lượng: </label>
                        <input type="number" id="quantity" value="1" min="1">
                    </div>
                    <div class="cart">
                        <button type="button">Thêm vào giỏ hàng</button>
                    </div>
                </div>
                <ul>
                    <li><i class=""></i><span>Miễn phí giao hàng</span></li>
                    <li><i class=""></i><span>Đổi trả 7 ngày</span></li>
                    <li><i class=""></i><span>Cam kết hàng chính hãng 100%</span></li>
                    <li><i class=""></i><span>Mở hộp kiểm nha nhận hàng</span></li>
                </ul>
                <div class="notice">
                    <span>Chúc mừng! Bạn được freeship 100%</span>
                </div>

                <div class="product-attributes-short">
                    <h3>Thông tin chi tiết</h3>
                    <ul>
                        <li><strong>Xuất xứ:</strong> Việt Nam</li>
                        <li><strong>Chất liệu:</strong> Gốm sứ cao cấp</li>
                        <li><strong>Kích thước:</strong> 31,5 cm</li>
                        <li><strong>Trọng lượng:</strong> 1,2 kg</li>
                        <li><strong>Màu sắc:</strong> Trắng viền vàng</li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <section class="product-description">
        <div class="container">
            <div class="product-tab">
                <ul>
                    <li class="tab-link active">
                        <h3>Mô tả sản phẩm</h3>
                    </li>
                </ul>
                <div class="tab">
                    <div class="tab-content">
                        <div class="product-content">
                            <p>Tuyển chọn từ những nguyên liệu quý hiếm mỗi sản phẩm của Misc Assortment đảm bảo những tiêu chí cao nhất về chất lượng. Sở hữu nhiều tính năng vượt trội siêu cứng chắc, mặt men sáng bóng, khó trầy xước. Misc Assortment được nhiều người ưa chuộng và lựa chọn bởi sự cao cấp, an toàn, bền đẹp và thân thiện với môi trường</p>
                            <p>Bộ khay mứt của gốm sứ Minh Long là sản phẩm hoàn hảo cho những người yêu quý đồ đồng quê, tinh tế và đầy tính thẩm mỹ. Với chất liệu gốm sứ cao cấp và kĩ thuật sản xuất tinh tế, bộ khay mứt không chỉ giúp bạn làm đẹp cho không gian phòng khách, những lúc có khách đến chơi, mà còn giúp bạn giữ được các loại mứt được cất giữ ngon miệng và khô ráo.</p>
                            <p>Sản phẩm bao gồm 3 cái khay với màu sắc ấn tượng và lạ mắt, được trang trí từng đường nét tinh xảo trên bề mặt. Chất liệu gốm sứ sáng bóng, tạo cảm giác sang trọng, cao cấp chinh phục người dùng ngay cái nhìn đầu tiên. Mỗi khay có kích thước khác nhau, vừa ôm trọn các loại trái cây, hoa quả hay mứt, đem lại sự tiện lợi cho những bữa tiệc hoặc các buổi thư giãn khác nhau. </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="product-relate">
        <div class="container">
            <span class="review-title" style="margin-bottom: 20px;">Đánh giá sản phẩm</span>
            <div class="review-summary">
                <div class="review-average">
                    <span class="score">5.0</span>
                    <div class="stars">
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                    </div>
                    <p>Đánh giá trung bình</p>
                    <p>1 đánh giá</p>
                </div>

                <div class="review-breakdown">
                    <div class="rating-row">
                        <span class="rating-label">5</span>
                        <div class="rating-bar"><span style="width:100%"></span></div>
                        <span class="rating-count">100% | 1 đánh giá</span>
                    </div>
                    <div class="rating-row">
                        <span class="rating-label">4</span>
                        <div class="rating-bar"><span style="width:0%"></span></div>
                        <span class="rating-count">0% | 0 đánh giá</span>
                    </div>
                    <div class="rating-row">
                        <span class="rating-label">3</span>
                        <div class="rating-bar"><span style="width:0%"></span></div>
                        <span class="rating-count">0% | 0 đánh giá</span>
                    </div>
                    <div class="rating-row">
                        <span class="rating-label">2</span>
                        <div class="rating-bar"><span style="width:0%"></span></div>
                        <span class="rating-count">0% | 0 đánh giá</span>
                    </div>
                    <div class="rating-row">
                        <span class="rating-label">1</span>
                        <div class="rating-bar"><span style="width:0%"></span></div>
                        <span class="rating-count">0% | 0 đánh giá</span>
                    </div>
                </div>

                <div class="review-action">
                    <button type="button" class="btn-review">Đánh giá ngay</button>
                </div>
            </div>

            <div class="review-list">
                <div class="review-item">
                    <div class="review-item-header">
                        <div>
                            <strong>Hòa Phú</strong>
                            <span class="verified">Đã mua tại Suncraft</span>
                        </div>
                        <div class="stars small">
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                        </div>
                    </div>
                    <p class="review-comment">
                        Shop tư vấn nhiệt tình, hỗ trợ tốt. Sản phẩm đúng mô tả, màu sắc hài hòa, khay gọn nhẹ
                        đúng kích thước 31,5cm, bề mặt gốm sứ sáng bóng.
                    </p>
                    <p class="review-meta">Trả lời: 28/08/2025</p>
                </div>
            </div>

            <div class="product-relate">
                <div class="carousel">
                    <span>Sản phẩm liên quan</span>
                </div>
                <div class="product-grid">
                    <div class="product-card">
                        <a href="#" class="product-link">
                            <div class="product-imgs">
                                <span class="sale">Giảm 44%</span>
                                <img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg" alt="Bokhay">
                            </div>
                            <div class="product-info">
                                <h3>Bát sâu trong Silver Spruce</h3>
                                <span class="price">1.590.000đ</span>
                                <del class="compare-price">1.790.000đ</del>
                            </div>
                        </a>
                    </div>
                    <div class="product-card">
                        <a href="#" class="product-link">
                            <div class="product-imgs">
                                <span class="sale">Giảm 44%</span>
                                <img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg" alt="Bokhay">
                            </div>
                            <div class="product-info">
                                <h3>Bát sâu trong Silver Spruce</h3>
                                <span class="price">1.590.000đ</span>
                                <del class="compare-price">1.790.000đ</del>
                            </div>
                        </a>
                    </div>
                    <div class="product-card">
                        <a href="#" class="product-link">
                            <div class="product-imgs">
                                <span class="sale">Giảm 44%</span>
                                <img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg" alt="Bokhay">
                            </div>
                            <div class="product-info">
                                <h3>Bát sâu trong Silver Spruce</h3>
                                <span class="price">1.590.000đ</span>
                                <del class="compare-price">1.790.000đ</del>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </section>
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
                        <p>0337429995</p> </div>
                    <div>
                        <i class="fa-solid fa-envelope"></i>
                        <p>23130240@st.hcmuaf.edu.vn</p> </div>
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
                        <li><a href="${pageContext.request.contextPath}/policy/security.jsp" class="hover color">Chính sách bảo mật</a></li>
                        <li><a href="${pageContext.request.contextPath}/policy/transport.jsp" class="hover color">Chính sách vận chuyển</a></li>
                        <li><a href="${pageContext.request.contextPath}/policy/change.jsp" class="hover color">Chính sách đổi trả</a></li>
                        <li><a href="${pageContext.request.contextPath}/policy/regulation-use.jsp" class="hover color">Quy định sử dụng</a></li>
                    </ul>
                </div>
                <div class="component guide">
                    <h2>Hướng dẫn</h2>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/guide/purchase.jsp" class="hover color">Hướng dẫn mua hàng</a></li>
                        <li><a href="${pageContext.request.contextPath}/guide/payment.jsp" class="hover color">Hướng dẫn thanh toán</a></li>
                        <li><a href="${pageContext.request.contextPath}/guide/delivery.jsp" class="hover color">Hướng dẫn giao nhận</a></li>
                        <li><a href="${pageContext.request.contextPath}/guide/clause.jsp" class="hover color">Điều khoản sử dụng</a></li>
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
                <img src="https://mia.vn/media/uploads/blog-du-lich/Hang-thu-cong-my-nghe-net-dep-truyen-thong-tai-vung-tau-06-1635702336.jpg" alt="product-image" id="modal-product-img">
                <div>
                    <p id="modal-product-name">Ca trà 0.30 L - Mẫu Đơn - Trắng</p>
                    <strong id="modal-product-price">108.000đ</strong>
                </div>
            </div>

            <p class="modal-cart-status">Giỏ hàng của bạn hiện có 1 sản phẩm</p>

            <div class="modal-actions">
                <button id="modal-continue-shopping" class="btn-continue">Tiếp tục mua hàng</button>
                <button id="modal-checkout" class="btn-checkout"><a href="${pageContext.request.contextPath}/checkout.jsp">Thanh toán ngay</a></button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/hero-section.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {

        // --- 1. Lấy các phần tử ---
        const openButton = document.querySelector('.product-detail-info .cart button');
        const modal = document.getElementById('cart-modal-overlay');
        const closeXBtn = document.getElementById('modal-close-x');
        const continueShoppingBtn = document.getElementById('modal-continue-shopping');

        // --- 2. Hàm MỞ pop-up ---
        function openModal() {
            if (modal) {
                modal.classList.add('show');
            }
        }

        // --- 3. Hàm ĐÓNG pop-up ---
        function closeModal() {
            if (modal) {
                modal.classList.remove('show');
            }
        }

        // --- 4. Gán sự kiện "Lắng Nghe" ---
        if (openButton) {
            openButton.addEventListener('click', function(e) {
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
            modal.addEventListener('click', function(e) {
                if (e.target === modal) {
                    closeModal();
                }
            });
        }
    });
</script>
</body>
</html>