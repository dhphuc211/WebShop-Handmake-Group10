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
    // Gap giữa các item (phải khớp với CSS gap: 10px)
    const GAP = 40; 

    /**
     * Tính toán chiều rộng của 1 item để biết cần trượt bao nhiêu pixel
     * Công thức: Chiều rộng item + khoảng cách (gap)
     */
    function getScrollStep() {
        const itemWidth = items[0].offsetWidth;
        return itemWidth + GAP;
    }

    // 3. Xử lý sự kiện click Next
    // Logic: Khi click Next, container cuộn sang phải một khoảng bằng (1 item + gap)
    // Điều này sẽ làm item đầu tiên bị ẩn đi (cuộn ra ngoài) và item thứ 5 hiện ra.
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
            left: -step, // Giá trị âm để cuộn ngược lại
            behavior: 'smooth'
        });
    });

    // 5. Cập nhật trạng thái nút (Ẩn/Hiện/Disable)
    function updateButtonState() {
        // Sai số cho phép (do zoom trình duyệt hoặc làm tròn pixel)
        const tolerance = 5; 
        
        const scrollLeft = container.scrollLeft;
        const maxScrollLeft = container.scrollWidth - container.clientWidth;

        // Nút Prev: Disable nếu đang ở đầu (scrollLeft gần bằng 0)
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
    
    // Cập nhật khi resize màn hình (vì width item thay đổi)
    window.addEventListener('resize', updateButtonState);

    // Khởi tạo trạng thái ban đầu
    updateButtonState();

    // 6. Xử lý click vào category-item để chuyển đến trang product.html
    items.forEach(item => {
        item.addEventListener('click', function() {
            // Lấy giá trị category từ data-category attribute
            const category = this.getAttribute('data-category');

            // Chuyển hướng đến trang product.html với query parameter
            if (category) {
                window.location.href = `product.html?category=${category}`;
            } else {
                // Nếu không có category cụ thể, chuyển đến trang product.html chung
                window.location.href = 'product.html';
            }
        });

        // Thêm hiệu ứng cursor pointer để người dùng biết có thể click
        item.style.cursor = 'pointer';
    });

    console.log('Hero Carousel initialized: Display 4 items logic with navigation to product page.');
});