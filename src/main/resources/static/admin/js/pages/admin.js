$(document).ready(function() {
    // Xử lý sự kiện khi người dùng nhấp vào các nav-item có dropdown
    $('.nav-treeview .nav-item').on('click', function() {
        $(this).toggleClass('menu-open');

        // Lưu trạng thái của sidebar vào Local Storage
        localStorage.setItem('sidebarState', $('.sidebar').html());
    });

    // Khôi phục trạng thái của sidebar khi trang được tải lại
    var sidebarState = localStorage.getItem('sidebarState');
    if (sidebarState) {
        $('.sidebar').html(sidebarState);
    }
});