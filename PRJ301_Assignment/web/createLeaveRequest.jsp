<%-- 
    Document   : createLeaveRequest
    Created on : Jun 15, 2025, 10:24:04 PM
    Author     : saiki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Leave Request</title>
</head>
<body>
    <h2>Create Leave Request</h2>
    <form action="CreateLeaveRequestController" method="POST">
        <label>Title:</label>
        <input type="text" name="title" required><br><br>
        <label>From Date:</label>
        <input type="date" name="fromDate" required><br><br>
        <label>To Date:</label>
        <input type="date" name="toDate" required><br><br>
        <label>Reason:</label>
        <textarea name="reason" required></textarea><br><br>
        <input type="submit" value="Submit Request">
    </form>
    <br>
    <a href="ListLeaveRequestController">Back to List</a>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
</body>
</html>