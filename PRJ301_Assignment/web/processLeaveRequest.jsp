<%-- 
    Document   : processLeaveRequest
    Created on : Jun 15, 2025, 10:24:38 PM
    Author     : saiki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Process Leave Request</title>
</head>
<body>
    <h2>Process Leave Request</h2>
    <form action="ProcessLeaveRequestController" method="POST">
        <input type="hidden" name="requestID" value="${requestScope.leaveRequest.requestID}">
        <label>Created By:</label>
        <input type="text" value="${requestScope.leaveRequest.userID}" disabled><br><br>
        <label>Title:</label>
        <input type="text" value="${requestScope.leaveRequest.title}" disabled><br><br>
        <label>From Date:</label>
        <input type="date" value="${requestScope.leaveRequest.fromDate}" disabled><br><br>
        <label>To Date:</label>
        <input type="date" value="${requestScope.leaveRequest.toDate}" disabled><br><br>
        <label>Reason:</label>
        <textarea disabled>${requestScope.leaveRequest.reason}</textarea><br><br>
        <label>Process Reason:</label>
        <textarea name="processReason"></textarea><br><br>
        <input type="submit" name="action" value="Approve">
        <input type="submit" name="action" value="Reject">
    </form>
    <br>
    <a href="ListLeaveRequestController">Back to List</a>
</body>
</html>