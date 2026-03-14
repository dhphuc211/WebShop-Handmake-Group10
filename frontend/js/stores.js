














(function() {
    'use strict';

    
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initCategoriesCarousel);
    } else {
        initCategoriesCarousel();
    }

    function initCategoriesCarousel() {
        
        const categoriesContainer = document.getElementById('categoriesContainer');
        const prevButton = document.getElementById('prevButton');
        const nextButton = document.getElementById('nextButton');
        const categoryItems = document.querySelectorAll('.category-item');

        
        if (!categoriesContainer || !prevButton || !nextButton) {
            console.warn('Categories carousel elements not found');
            return;
        }

        
        
        

        






        function updateNavigationButtons() {
            const scrollLeft = categoriesContainer.scrollLeft;
            const scrollWidth = categoriesContainer.scrollWidth;
            const clientWidth = categoriesContainer.clientWidth;

            
            if (scrollLeft <= 0) {
                prevButton.style.opacity = '0';
                prevButton.style.pointerEvents = 'none';
            } else {
                prevButton.style.opacity = '1';
                prevButton.style.pointerEvents = 'auto';
            }

            
            
            if (scrollLeft + clientWidth >= scrollWidth - 1) {
                nextButton.style.opacity = '0';
                nextButton.style.pointerEvents = 'none';
            } else {
                nextButton.style.opacity = '1';
                nextButton.style.pointerEvents = 'auto';
            }
        }

        
        prevButton.style.transition = 'opacity 300ms ease';
        nextButton.style.transition = 'opacity 300ms ease';

        
        categoriesContainer.addEventListener('scroll', updateNavigationButtons);

        
        window.addEventListener('resize', updateNavigationButtons);

        
        updateNavigationButtons();

        
        
        

        let touchStartX = 0;
        let touchEndX = 0;

        categoriesContainer.addEventListener('touchstart', function(e) {
            touchStartX = e.changedTouches[0].screenX;
        }, { passive: true });

        categoriesContainer.addEventListener('touchend', function(e) {
            touchEndX = e.changedTouches[0].screenX;
            handleSwipe();
        }, { passive: true });

        function handleSwipe() {
            const swipeThreshold = 50; 
            const difference = touchStartX - touchEndX;

            if (Math.abs(difference) > swipeThreshold) {
                if (difference > 0) {
                    
                    categoriesContainer.scrollBy({
                        left: 300,
                        behavior: 'smooth'
                    });
                } else {
                    
                    categoriesContainer.scrollBy({
                        left: -300,
                        behavior: 'smooth'
                    });
                }
            }
        }

        console.log('Categories carousel initialized successfully');
    }
})();





(function() {
    'use strict';

    
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initSearchAndFilter);
    } else {
        initSearchAndFilter();
    }

    function initSearchAndFilter() {
        
        const searchInput = document.querySelector('.search-input');
        const provinceDropdown = document.querySelector('.filter-dropdown');
        const districtDropdown = document.querySelector('.district-dropdown');
        const storeCards = document.querySelectorAll('.store-card');
        const storeList = document.querySelector('.store-list');

        
        if (!searchInput || !provinceDropdown || !districtDropdown || !storeList) {
            console.warn('Search & Filter elements not found');
            return;
        }

        
        
        
        const districtData = {
            'Hồ Chí Minh': ['Quận 1', 'Quận 3', 'Quận 5', 'Quận 10', 'Quận 11'],
            'Hà Nội': ['Quận Ba Đình', 'Quận Cầu Giấy', 'Quận Hà Đông', 'Quận Hoàn Kiếm'],
            'Đà Nẵng': ['Quận Hải Châu', 'Quận Thanh Khê', 'Quận Sơn Trà', 'Quận Ngũ Hành Sơn'],
            'Bình Dương': ['TP Thủ Dầu Một', 'TP Dĩ An', 'TP Thuận An'],
            'Cần Thơ': ['Quận Ninh Kiều', 'Quận Cái Răng', 'Quận Bình Thủy']
        };

        
        
        

        let searchDebounceTimer;
        const DEBOUNCE_DELAY = 300; 

        


        function debounce(func, delay) {
            return function(...args) {
                clearTimeout(searchDebounceTimer);
                searchDebounceTimer = setTimeout(() => func.apply(this, args), delay);
            };
        }

        


        function performSearch() {
            const searchValue = searchInput.value.toLowerCase().trim();
            const selectedProvince = provinceDropdown.value;
            const selectedDistrict = districtDropdown.value;

            let visibleCount = 0;

            storeCards.forEach(card => {
                const storeName = card.querySelector('.store-header').textContent.toLowerCase();
                const storeAddress = card.querySelector('.store-address').textContent.toLowerCase();

                
                const matchesSearch = searchValue === '' || storeName.includes(searchValue);

                
                const matchesProvince = selectedProvince === 'Chọn tỉnh thành' ||
                                       storeAddress.includes(selectedProvince.toLowerCase());

                
                const matchesDistrict = selectedDistrict === 'Chọn quận/huyện' ||
                                       storeAddress.includes(selectedDistrict.toLowerCase());

                
                const shouldShow = matchesSearch && matchesProvince && matchesDistrict;

                if (shouldShow) {
                    
                    card.style.opacity = '0';
                    card.style.display = 'block';
                    setTimeout(() => {
                        card.style.transition = 'opacity 300ms ease';
                        card.style.opacity = '1';
                    }, 10);
                    visibleCount++;
                } else {
                    
                    card.style.transition = 'opacity 200ms ease';
                    card.style.opacity = '0';
                    setTimeout(() => {
                        card.style.display = 'none';
                    }, 200);
                }
            });

            
            showNoResultsMessage(visibleCount);
        }

        


        function showNoResultsMessage(visibleCount) {
            let noResultsMsg = storeList.querySelector('.no-results-message');

            if (visibleCount === 0) {
                if (!noResultsMsg) {
                    noResultsMsg = document.createElement('div');
                    noResultsMsg.className = 'no-results-message';
                    noResultsMsg.innerHTML = '<p>Không tìm thấy cửa hàng nào phù hợp</p>';
                    noResultsMsg.style.cssText = `
                        text-align: center;
                        padding: 40px 20px;
                        color: #999;
                        font-size: 16px;
                        opacity: 0;
                        transition: opacity 300ms ease;
                    `;
                    storeList.appendChild(noResultsMsg);
                    setTimeout(() => {
                        noResultsMsg.style.opacity = '1';
                    }, 10);
                }
            } else {
                if (noResultsMsg) {
                    noResultsMsg.style.opacity = '0';
                    setTimeout(() => {
                        noResultsMsg.remove();
                    }, 300);
                }
            }
        }

        
        searchInput.addEventListener('input', debounce(performSearch, DEBOUNCE_DELAY));

        
        
        

        


        function updateDistrictDropdown(province) {
            
            districtDropdown.innerHTML = '<option selected>Chọn quận/huyện</option>';

            if (province && province !== 'Chọn tỉnh thành' && districtData[province]) {
                
                districtData[province].forEach(district => {
                    const option = document.createElement('option');
                    option.value = district;
                    option.textContent = district;
                    districtDropdown.appendChild(option);
                });
                districtDropdown.disabled = false;
            } else {
                
                districtDropdown.disabled = true;
            }
        }

        provinceDropdown.addEventListener('change', function() {
            const selectedProvince = this.value;

            
            updateDistrictDropdown(selectedProvince);

            
            performSearch();
        });

        
        
        

        districtDropdown.addEventListener('change', function() {
            performSearch();
        });

        
        
        

        
        districtDropdown.disabled = true;

        
        storeCards.forEach(card => {
            card.style.transition = 'opacity 300ms ease';
        });

        console.log('Search & Filter system initialized successfully');
    }
})();





(function() {
    'use strict';

    
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initStoreCardInteractions);
    } else {
        initStoreCardInteractions();
    }

    function initStoreCardInteractions() {
        
        const storeCards = document.querySelectorAll('.store-card');
        const mapIframe = document.querySelector('#map iframe');
        const mapContainer = document.querySelector('#map');

        
        if (storeCards.length === 0) {
            console.warn('Store cards not found');
            return;
        }

        
        
        
        const storeData = {
            'Sudes Sài Gòn': {
                lat: 10.7626,
                lng: 106.6677,
                address: 'Tầng 3, 70 Lữ Gia, Phường 15, Quận 11, Thành phố Hồ Chí Minh'
            },
            'Sudes Bình Dương': {
                lat: 10.9804,
                lng: 106.6519,
                address: 'lẻ / 24 Nguyễn Hữu Cảnh, Phường Phú Thọ, TP Thủ Dầu Một'
            },
            'Sudes Cần Thơ': {
                lat: 10.0452,
                lng: 105.7469,
                address: '81 đường Phan Huy Chú, KDC Thới Nhựt 1, Phường An Khánh'
            },
            'Sudes Hà Nội': {
                lat: 21.0285,
                lng: 105.8252,
                address: 'Tầng 6 - 266 Đội Cấn, Phường Liễu Giai, Quận Ba Đình'
            },
            'Sudes Đà Nẵng': {
                lat: 16.0544,
                lng: 108.2022,
                address: '161 đường Huỳnh Tấn Phát, Phường Hoà Cường Nam'
            },
            'Sudes Hoàng Quốc Việt': {
                lat: 21.0490,
                lng: 105.7971,
                address: '36 Hoàng Quốc Việt, Phường Nghĩa Tân, Quận Cầu Giấy'
            },
            'Sudes Hoàng Đạo Thúy': {
                lat: 21.0078,
                lng: 105.7952,
                address: '150 Hoàng Đạo Thúy, Phường Trung Hòa, Quận Cầu Giấy'
            },
            'Sudes Trần Phú': {
                lat: 20.9719,
                lng: 105.7785,
                address: '53 Trần Phú, Phường Văn Quán, Quận Hà Đông'
            }
        };

        
        
        

        storeCards.forEach(card => {
            
            card.style.transition = 'all 300ms ease';

            card.addEventListener('click', function() {
                
                storeCards.forEach(c => {
                    c.classList.remove('active');
                    c.style.borderColor = '#ff8c00';
                    c.style.backgroundColor = 'white';
                    c.style.color = '#333';

                    
                    const header = c.querySelector('.store-header');
                    const address = c.querySelector('.store-address');
                    const hotline = c.querySelector('.store-hotline');
                    const hotlineSpan = c.querySelector('.store-hotline span');

                    if (header) header.style.color = '';
                    if (address) address.style.color = '';
                    if (hotline) hotline.style.color = '';
                    if (hotlineSpan) hotlineSpan.style.color = '';
                });

                
                this.classList.add('active');

                
                this.style.borderColor = '#ff5500';
                this.style.backgroundColor = '#ff8c00';
                this.style.color = 'white';

                
                const header = this.querySelector('.store-header');
                const address = this.querySelector('.store-address');
                const hotline = this.querySelector('.store-hotline');
                const hotlineSpan = this.querySelector('.store-hotline span');

                if (header) header.style.color = 'white';
                if (address) address.style.color = 'white';
                if (hotline) hotline.style.color = 'white';
                if (hotlineSpan) hotlineSpan.style.color = 'white';

                
                this.scrollIntoView({
                    behavior: 'smooth',
                    block: 'nearest'
                });

                
                const storeName = this.querySelector('.store-header').textContent.trim();
                updateMap(storeName);
            });
        });
        
        
        

        


        function updateMap(storeName) {
            if (!mapIframe || !storeData[storeName]) {
                console.warn('Map iframe or store data not found');
                return;
            }

            const store = storeData[storeName];

            
            showLoadingOverlay();

            
            const mapUrl = `https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.6456!2d${store.lng}!3d${store.lat}!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zM!5e0!3m2!1svi!2s!4v1234567890`;

            
            mapContainer.style.transition = 'opacity 300ms ease';
            mapContainer.style.opacity = '0.3';

            
            setTimeout(() => {
                mapIframe.src = mapUrl;

                
                setTimeout(() => {
                    mapContainer.style.opacity = '1';
                    hideLoadingOverlay();
                }, 1000);
            }, 300);
        }

        


        function showLoadingOverlay() {
            let overlay = document.querySelector('.map-loading-overlay');

            if (!overlay) {
                overlay = document.createElement('div');
                overlay.className = 'map-loading-overlay';
                overlay.innerHTML = `
                    <div class="spinner"></div>
                    <p>Đang tải bản đồ...</p>
                `;
                overlay.style.cssText = `
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    text-align: center;
                    color: #ff8c00;
                    font-size: 16px;
                    z-index: 100;
                `;

                const spinner = document.createElement('style');
                spinner.textContent = `
                    .spinner {
                        width: 40px;
                        height: 40px;
                        margin: 0 auto 10px;
                        border: 4px solid #f3f3f3;
                        border-top: 4px solid #ff8c00;
                        border-radius: 50%;
                        animation: spin 1s linear infinite;
                    }
                    @keyframes spin {
                        0% { transform: rotate(0deg); }
                        100% { transform: rotate(360deg); }
                    }
                `;
                document.head.appendChild(spinner);

                mapContainer.style.position = 'relative';
                mapContainer.appendChild(overlay);
            }

            overlay.style.display = 'block';
        }

        


        function hideLoadingOverlay() {
            const overlay = document.querySelector('.map-loading-overlay');
            if (overlay) {
                overlay.style.display = 'none';
            }
        }

        console.log('Store card interactions initialized successfully');
    }
})();





(function() {
    'use strict';

    
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initFloatingButtons);
    } else {
        initFloatingButtons();
    }

    function initFloatingButtons() {
        
        const btnTop = document.querySelector('.btn-top');
        const btnLocation = document.querySelector('.btn-location');
        const btnMessenger = document.querySelector('.btn-messenger');
        const storeList = document.querySelector('.store-list');

        
        if (!btnTop || !btnLocation || !btnMessenger || !storeList) {
            console.warn('Floating buttons elements not found');
            return;
        }
    }
})();





(function() {
    'use strict';

    
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initMapInteractions);
    } else {
        initMapInteractions();
    }

    function initMapInteractions() {
        
        const playBtn = document.querySelector('.play-btn');
        const mapContainer = document.querySelector('#map');
        const mapIframe = document.querySelector('#map iframe');

        
        if (!playBtn || !mapContainer || !mapIframe) {
            console.warn('Map interaction elements not found');
            return;
        }
        
        
        

        


        function showMapLoader() {
            let loader = mapContainer.querySelector('.map-skeleton-loader');

            if (!loader) {
                loader = document.createElement('div');
                loader.className = 'map-skeleton-loader';
                loader.style.cssText = `
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    background: linear-gradient(90deg, #f0f0f0 25%, #e0e0e0 50%, #f0f0f0 75%);
                    background-size: 200% 100%;
                    animation: shimmer 1.5s infinite;
                    z-index: 50;
                `;
                mapContainer.style.position = 'relative';
                mapContainer.appendChild(loader);
            }

            loader.style.display = 'block';
            loader.style.opacity = '1';
        }

        


        function hideMapLoader() {
            const loader = mapContainer.querySelector('.map-skeleton-loader');
            if (loader) {
                loader.style.transition = 'opacity 300ms ease';
                loader.style.opacity = '0';
                setTimeout(() => {
                    loader.style.display = 'none';
                }, 300);
            }
        }

        
        mapIframe.addEventListener('load', function() {
            setTimeout(hideMapLoader, 1000);
        });

        
        
        

        let isFullscreen = false;
        let originalStyles = {};

        mapContainer.addEventListener('dblclick', function() {
            if (!isFullscreen) {
                enterFullscreen();
            } else {
                exitFullscreen();
            }
        });
    }
})();






(function() {
    'use strict';

    
    let toastContainer = null;
    const MAX_TOASTS = 3;
    const TOAST_DURATION = 3000;

    


    function initToastSystem() {
        if (!toastContainer) {
            toastContainer = document.createElement('div');
            toastContainer.className = 'toast-container';
            toastContainer.style.cssText = `
                position: fixed;
                top: 20px;
                right: 20px;
                z-index: 10000;
                display: flex;
                flex-direction: column;
                gap: 10px;
            `;
            document.body.appendChild(toastContainer);
        }
    }

    




    window.showToast = function(message, type = 'info') {
        initToastSystem();

        
        const existingToasts = toastContainer.querySelectorAll('.toast');
        if (existingToasts.length >= MAX_TOASTS) {
            existingToasts[0].remove();
        }

        
        const toast = document.createElement('div');
        toast.className = `toast toast-${type}`;

        
        const colors = {
            success: { bg: '#4caf50', icon: 'fa-check-circle' },
            error: { bg: '#f44336', icon: 'fa-exclamation-circle' },
            info: { bg: '#2196f3', icon: 'fa-info-circle' }
        };

        const color = colors[type] || colors.info;

        toast.style.cssText = `
            background: ${color.bg};
            color: white;
            padding: 16px 20px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            min-width: 300px;
            max-width: 400px;
            display: flex;
            align-items: center;
            gap: 12px;
            cursor: pointer;
            transform: translateX(400px);
            transition: transform 300ms ease;
            font-size: 14px;
        `;

        toast.innerHTML = `
            <i class="fas ${color.icon}" style="font-size: 20px;"></i>
            <span style="flex: 1;">${message}</span>
            <i class="fas fa-times" style="font-size: 16px; opacity: 0.8;"></i>
        `;

        toastContainer.appendChild(toast);

        
        setTimeout(() => {
            toast.style.transform = 'translateX(0)';
        }, 10);

        
        const dismissTimeout = setTimeout(() => {
            dismissToast(toast);
        }, TOAST_DURATION);

        
        toast.addEventListener('click', function() {
            clearTimeout(dismissTimeout);
            dismissToast(toast);
        });
    };

    


    function dismissToast(toast) {
        toast.style.transform = 'translateX(400px)';
        setTimeout(() => {
            toast.remove();
        }, 300);
    }

    console.log('Toast notification system initialized successfully');
})();





(function() {
    const style = document.createElement('style');
    style.textContent = `
        @keyframes shimmer {
            0% { background-position: -200% 0; }
            100% { background-position: 200% 0; }
        }

        @keyframes pulse {
            0%, 100% { transform: scale(1); }
            50% { transform: scale(1.05); }
        }

        @keyframes shake {
            0%, 100% { transform: translateX(0); }
            25% { transform: translateX(-10px); }
            75% { transform: translateX(10px); }
        }

        @keyframes highlight {
            0%, 100% { box-shadow: 0 0 0 0 rgba(255, 140, 0, 0); }
            50% { box-shadow: 0 0 0 10px rgba(255, 140, 0, 0.4); }
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    `;
    document.head.appendChild(style);
})();