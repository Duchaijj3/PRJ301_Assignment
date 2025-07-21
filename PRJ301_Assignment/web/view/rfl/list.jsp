<%-- 
    Document   : list
    Created on : Jun 25, 2025, 9:17:38 AM
    Author     : p14s
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Request for Leave of the Subordinates</title>
    </head>
    <body>
        <table border="1px">
            <tr>
                <td>Id</td>
                <td>Title</td>
                <td>Reason</td>
                <td>From</td>
                <td>To</td>
                <td>Status</td>
                <td>Created By</td>
                <td>Process By</td>
                <td></td>
            </tr>
            <c:forEach items="${requestScope.rfls}" var="r">
                <tr>
                    <td>${r.id}</td>
                    <td>${r.title}</td>
                    <td>${r.reason}</td>
                    <td>${r.from}</td>
                    <td>${r.to}</td>
                    <td>${r.status eq 0?"Waiting":r.status eq 1?"Approved":"Rejected"}</td>
                    <td>${r.createdby.username}</td>
                    <td><c:if test="${r.processedby ne null}">
                            ${r.processedby.username}
                    </c:if></td>
                    <td>
                        <a href="process?id=${r.id}&decision=yes">Yes</a>
                        <a href="process?id=${r.id}&decision=yes">No</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
