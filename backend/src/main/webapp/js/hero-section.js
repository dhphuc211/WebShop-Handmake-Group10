/**
 * HERO-SECTION.JS
 * Xử lý carousel danh mục: Hiển thị 4 item, trượt 1 item mỗi lần bấm.
 */

document.addEventListener('DOMContentLoaded', function() {
    // 1. Lấy các phần tử DOM
    const container = document.getElementById('categoriesContainer');
    const prevBtn = document.getElementById('prevButton');
    const nextBtn = document.getElementById('nextButton');
    const items = document.querySelectorAll('.category-item');

    if (!container || !prevBtn || !nextBtn || items.length === 0) return;

    // 2. Cấu hình
    const GAP = 40;

    /**
     * Tính toán chiều rộng của 1 item để biết cần trượt bao nhiêu pixel
     */
    function getScrollStep() {
        const itemWidth = items[0].offsetWidth;
        return itemWidth + GAP;
    }

    // 3. Xử lý sự kiện click Next
    nextBtn.addEventListener('click', () => {
        const step = getScrollStep();
        container.scrollBy({
            left: step,
            behavior: 'smooth'
        });
    });

    // 4. Xử lý sự kiện click Prev
    prevBtn.addEventListener('click', () => {
        const step = getScrollStep();
        container.scrollBy({
            left: -step,
            behavior: 'smooth'
        });
    });

    // 5. Cập nhật trạng thái nút (Ẩn/Hiện/Disable)
    function updateButtonState() {
        const tolerance = 5;
        const scrollLeft = container.scrollLeft;
        const maxScrollLeft = container.scrollWidth - container.clientWidth;

        // Nút Prev: Disable nếu đang ở đầu
        if (scrollLeft <= tolerance) {
            prevBtn.disabled = true;
            prevBtn.style.opacity = '0.5';
        } else {
            prevBtn.disabled = false;
            prevBtn.style.opacity = '1';
        }

        // Nút Next: Disable nếu đang ở cuối
        if (scrollLeft >= maxScrollLeft - tolerance) {
            nextBtn.disabled = true;
            nextBtn.style.opacity = '0.5';
        } else {
            nextBtn.disabled = false;
            nextBtn.style.opacity = '1';
        }
    }

    // Lắng nghe sự kiện cuộn để cập nhật nút
    container.addEventListener('scroll', updateButtonState);
    window.addEventListener('resize', updateButtonState);

    // Khởi tạo trạng thái ban đầu
    updateButtonState();

    // 6. Xử lý click vào category-item để chuyển đến trang products
    items.forEach(item => {
        item.addEventListener('click', function() {
            const category = this.getAttribute('data-category');
            const contextPath = document.body.getAttribute('data-context-path') || '';

            if (category) {
                window.location.href = contextPath + '/products.jsp?category=' + category;
            } else {
                window.location.href = contextPath + '/products.jsp';
            }
        });

        item.style.cursor = 'pointer';
    });

    console.log('Hero Carousel initialized.');
});
