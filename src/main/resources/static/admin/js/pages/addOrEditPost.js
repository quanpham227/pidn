// Sự kiện DOMContentLoaded: Thay thế CKEditor
document.addEventListener("DOMContentLoaded", function() {
    CKEDITOR.replace('editor', {});
});

// Sự kiện thay đổi của input file thumbnail
document.getElementById('thumbnailFile').addEventListener('change', function() {
    var file = this.files[0]; // Lấy file từ danh sách các files được chọn
    var fileName = file.name; // Lấy tên của file
    var img = document.getElementById('previewImage');
    var reader = new FileReader(); // Tạo một đối tượng FileReader để đọc dữ liệu của file
    reader.onload = function(e) {
        // Khi file được đọc thành công, gán dữ liệu dưới dạng URL vào thuộc tính src của thẻ img
        img.src = e.target.result;
    }
    // Đọc file dưới dạng URL
    reader.readAsDataURL(file);
});
// Hàm kiểm tra dữ liệu của CKEditor trước khi gửi form
function validateCKEditorContent() {
    // Lấy nội dung của CKEditor
    var editorData = CKEDITOR.instances['editor'].getData();
    // Kiểm tra xem nội dung có rỗng không
    if (editorData.trim() === '') {
        alert('Please enter content for the post.');
        return false; // Ngăn form được gửi đi
    }
    // Nếu nội dung hợp lệ, cho phép form được gửi đi
    return true;
}
// Hàm kiểm tra form trước khi submit
function validateForm() {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    var isChecked = false;
    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            isChecked = true;
        }
    });
    if (!isChecked) {
        alert('Please select at least one category.');
        return false; // Ngăn chặn biểu mẫu được gửi đi
    }
    return true;
}