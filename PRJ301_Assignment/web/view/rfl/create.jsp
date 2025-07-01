<%-- 
    Document   : create
    Created on : Jun 30, 2025, 10:28:52 AM
    Author     : sonnt
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Request for Leave</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                max-width: 400px;
                margin: 20px auto;
                padding: 20px;
                background-color: #f9f9f9;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            input[type="text"], input[type="date"] {
                width: 100%;
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
            }
            input[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #5cb85c;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            input[type="submit"]:hover {
                background-color: #4cae4c;
            }
            .error {
                color: red;
                font-size: 14px;
                margin-top: 10px;
                padding: 5px;
                border-radius: 4px;
                display: none;
            }
            .error.show {
                display: block;
                background-color: #ffebee;
                border: 1px solid #ef9a9a;
            }
        </style>
        <script>
            function validateForm() {
                var fromDate = new Date(document.querySelector('input[name="from"]').value);
                var toDate = new Date(document.querySelector('input[name="to"]').value);
                var errorDiv = document.querySelector('.error');

                // Kiểm tra lỗi từ Servlet
                var servletError = "${error}";
                if (servletError && servletError.trim() !== "") {
                    errorDiv.textContent = servletError;
                    errorDiv.classList.add('show');
                    return false;
                }

                // Kiểm tra ngày
                if (fromDate > toDate) {
                    errorDiv.textContent = "From date must be before To date!";
                    errorDiv.classList.add('show');
                    return false;
                }

                errorDiv.classList.remove('show');
                return true;
            }

            // Hiển thị lỗi ngay khi trang load nếu có từ Servlet
            window.onload = function() {
                var errorDiv = document.querySelector('.error');
                var servletError = "${error}";
                if (servletError && servletError.trim() !== "") {
                    errorDiv.textContent = servletError;
                    errorDiv.classList.add('show');
                }
            };
        </script>
    </head>
    <body>
        <h2>Create New Request for Leave</h2>
        <form action="create" method="POST" onsubmit="return validateForm()">
            <div class="form-group">
                <label>Title:</label>
                <input type="text" name="title" required />
            </div>
            <div class="form-group">
                <label>From:</label>
                <input type="date" name="from" required />
            </div>
            <div class="form-group">
                <label>To:</label>
                <input type="date" name="to" required />
            </div>
            <div class="form-group">
                <label>Reason:</label>
                <input type="text" name="reason" required />
            </div>
            <div class="form-group">
                <input type="submit" value="Request" />
            </div>
            <div class="error"></div>
        </form>
    </body>
</html>