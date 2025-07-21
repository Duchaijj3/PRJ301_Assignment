<%-- 
    Document   : login
    Created on : Jun 16, 2025, 10:49:07 AM
    Author     : p14s
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Member Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            min-height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', 'Arial', sans-serif;
            background: linear-gradient(120deg, #7f53ac 0%, #647dee 100%);
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-container {
            background: #fff;
            border-radius: 14px;
            box-shadow: 0 8px 32px 0 rgba(31,38,135,0.12);
            display: flex;
            flex-direction: row;
            align-items: center;
            padding: 40px 50px;
            max-width: 550px;
            width: 100%;
            gap: 30px;
        }
        .login-img {
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-img img {
            width: 110px;
            height: 110px;
            border-radius: 50%;
            background: #f4f4f4;
            padding: 10px;
            box-shadow: 0 2px 12px #eee;
        }
        .login-form {
            display: flex;
            flex-direction: column;
            align-items: stretch;
            min-width: 220px;
        }
        .login-form h2 {
            margin: 0 0 16px 0;
            font-size: 1.25rem;
            font-weight: 600;
            text-align: center;
            color: #222;
            letter-spacing: 0.03em;
        }
        .login-form .input-group {
            display: flex;
            align-items: center;
            margin-bottom: 14px;
            background: #f4f4f4;
            border-radius: 24px;
            padding: 0 16px;
        }
        .login-form .input-group i {
            color: #999;
            margin-right: 8px;
        }
        .login-form input {
            border: none;
            background: transparent;
            outline: none;
            font-size: 1rem;
            padding: 12px 0;
            flex: 1;
        }
        .login-form button {
            border: none;
            border-radius: 22px;
            background: #5cb85c;
            color: #fff;
            padding: 11px 0;
            font-size: 1rem;
            font-weight: 600;
            margin-top: 6px;
            cursor: pointer;
            transition: background 0.2s;
        }
        .login-form button:hover {
            background: #449d44;
        }
        .login-form .links {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 12px;
            font-size: 0.96em;
        }
        .login-form .links a {
            color: #647dee;
            text-decoration: none;
            margin: 2px 0;
            transition: text-decoration 0.2s;
        }
        .login-form .links a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: #e74c3c;
            background: #ffeaea;
            border-radius: 6px;
            padding: 7px 13px;
            margin-bottom: 10px;
            font-size: 0.99em;
            text-align: center;
        }
        @media (max-width: 700px) {
            .login-container {
                flex-direction: column;
                padding: 30px 18px;
                gap: 16px;
            }
        }
    </style>
    <!-- FontAwesome CDN for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
</head>
<body>
    <div class="login-container">
        <div class="login-img">
            <img src="https://cdn-icons-png.flaticon.com/512/747/747376.png" alt="login icon">
        </div>
        <form class="login-form" method="post" action="login">
            <h2>Member Login</h2>
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>
            <div class="input-group">
                <i class="fa fa-envelope"></i>
                <input type="text" name="username" placeholder="Username" required autofocus>
            </div>
            <div class="input-group">
                <i class="fa fa-lock"></i>
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <button type="submit">LOGIN</button>
            <div class="links">
                <a href="#">Forgot Username / Password?</a>
                <a href="#">Create your Account &rarr;</a>
            </div>
        </form>
    </div>
</body>
</html>