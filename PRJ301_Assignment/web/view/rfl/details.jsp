<%-- 
    Document   : details
    Created on : Jul 7, 2025, 10:35:53 PM
    Author     : saiki
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết đơn xin nghỉ</title>
        <style>
            body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
            .container { max-width: 600px; margin: 0 auto; background-color: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); }
            .detail { margin-bottom: 15px; }
            .detail label { font-weight: bold; }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Chi tiết đơn xin nghỉ</h2>
            <div class="detail"><label>ID:</label> ${rfl.id}</div>
            <div class="detail"><label>Title:</label> ${rfl.title}</div>
            <div class="detail"><label>Employee:</label> ${rfl.createdby.username}</div>
            <div class="detail"><label>Reason:</label> ${rfl.reason}</div>
            <div class="detail"><label>From:</label> ${rfl.from}</div>
            <div class="detail"><label>To:</label> ${rfl.to}</div>
            <div class="detail"><label>Status:</label> ${rfl.status eq 0 ? "Waiting" : rfl.status eq 1 ? "Approved" : "Rejected"}</div>
            <div class="detail"><label>Created By:</label> ${rfl.createdby.username}</div>
            <div class="detail"><label>Processed By:</label> <c:if test="${rfl.processedby ne null}">${rfl.processedby.username}</c:if></div>
            <a href="list">Back to List</a>
        </div>
    </body>
</html>