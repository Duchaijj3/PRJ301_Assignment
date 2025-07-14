<%-- 
    Document   : process
    Created on : Jul 14, 2025, 1:38:59 AM
    Author     : saiki
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Approve/Reject Leave Request</title>
    </head>
    <body>
        <h2>Approve/Reject Leave Request</h2>
        <form method="post" action="manage">
            <input type="hidden" name="id" value="${r.id}" />
            <p>Title: ${r.title}</p>
            <p>Reason for Leave Request: ${r.reason}</p>
            <p>Time: ${r.from} - ${r.to}</p>
            <p>Created by: ${r.createdby.username}</p>
            <p>Current Status:
                <c:choose>
                    <c:when test="${r.status == 0}">Pending approval</c:when>
                    <c:when test="${r.status == 1}">Approved</c:when>
                    <c:otherwise>Rejected</c:otherwise>
                </c:choose>
            </p>
            <p>
                <label>Reason for approval/rejection:</label><br>
                <input type="text" name="processreason" required style="width:300px"/>
            </p>
            <button type="submit" name="decision" value="yes">Approved</button>
            <button type="submit" name="decision" value="no">Rejected</button>
            <a href="list">Return to list</a>
        </form>
    </body>
</html>