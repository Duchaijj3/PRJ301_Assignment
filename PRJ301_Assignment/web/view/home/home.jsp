<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý nhân sự công ty ABC</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 800px;
                margin: 20px auto;
                padding: 20px;
                background-color: white;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }
            .menu {
                list-style: none;
                padding: 0;
            }
            .menu li {
                margin-bottom: 10px;
            }
            .menu a {
                display: block;
                padding: 12px 20px;
                background-color: #5cb85c;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s, transform 0.2s;
            }
            .menu a:hover {
                background-color: #4cae4c;
                transform: translateX(10px);
            }
            .footer {
                text-align: center;
                margin-top: 20px;
                color: #777;
                font-size: 12px;
            }
            .request-table {
                margin-top: 20px;
            }
            .request-table table {
                width: 100%;
                border-collapse: collapse;
            }
            .request-table th, .request-table td {
                padding: 10px;
                border: 1px solid #ddd;
            }
            .request-table th {
                background-color: #5cb85c;
                color: white;
            }
            .request-table tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            .request-table tr:hover {
                background-color: #f1f1f1;
            }
        </style>
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                const links = document.querySelectorAll('.menu a');
                links.forEach(link => {
                    link.addEventListener('mouseover', function() {
                        this.style.transition = 'all 0.3s ease';
                    });
                    link.addEventListener('mouseout', function() {
                        this.style.transition = 'all 0.3s ease';
                        this.style.transform = 'translateX(0)';
                    });
                });
            });
        </script>
    </head>
    <body>
        <div class="container">
            <h1>Quản lý nhân sự công ty ABC</h1>
            <ul class="menu">
                <li><a href="list">Hiển thị đơn xin nghỉ / Quản lý duyệt đơn</a></li>
                <li><a href="details">Hiển thị chi tiết đơn xin nghỉ</a></li>
            </ul>
            <div class="request-table">
                <h3>Danh sách đơn xin nghỉ gần đây</h3>
                <table>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Employee</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${rfls}" var="r">
                        <tr>
                            <td>${r.id}</td>
                            <td>${r.title}</td>
                            <td>${r.createdby.username}</td>
                            <td>${r.status eq 0 ? "Waiting" : r.status eq 1 ? "Approved" : "Rejected"}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="footer">
                © 2025 Công ty ABC - Hệ thống quản lý nhân sự
            </div>
        </div>
    </body>
    </html>