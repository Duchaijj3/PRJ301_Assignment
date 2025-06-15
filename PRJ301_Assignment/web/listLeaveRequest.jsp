<%-- 
    Document   : listLeaveRequest
    Created on : Jun 15, 2025, 10:24:22 PM
    Author     : saiki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of Leave Requests</title>
</head>
<body>
    <h2>List of Leave Requests</h2>
    <a href="createLeaveRequest.jsp">Create New Request</a><br><br>
    <table border="1">
        <tr>
            <th>Title</th>
            <th>From</th>
            <th>To</th>
            <th>Created By</th>
            <th>Status</th>
            <th>Processed By</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${requestScope.leaveRequests}" var="r">
            <tr>
                <td>${r.title}</td>
                <td>${r.fromDate}</td>
                <td>${r.toDate}</td>
                <td>${r.userID}</td>
                <td>${r.status}</td>
                <td>${r.processedBy}</td>
                <td>
                    <c:if test="${r.status == 'Inprogress' && sessionScope.user.managerID != null}">
                        <a href="ProcessLeaveRequestController?id=${r.requestID}">Process</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>