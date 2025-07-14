<%-- 
    Document   : list.jsp
    Created on : Jul 14, 2025, 11:46:15 AM
    Author     : saiki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Department work schedule</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h2>Department employee work schedule/permission</h2>
        <table border="1">
            <tr>
                <th>Employee</th>
                <th>Title</th>
                <th>Reason</th>
                <th>From date</th>
                <th>To date</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${requestScope.rfls}" var="r">
                <tr> 
                    <td>${r.createdby.username}</td> 
                    <td>${r.title}</td> 
                    <td>${r.reason}</td> 
                    <td>${r.from}</td> 
                    <td>${r.to}</td> 
                    <td> 
                        <c:choose> 
                            <c:when test="${r.status == 0}">Waiting for approval</c:when> 
                            <c:when test="${r.status == 1}">Approved</c:when> 
                            <c:otherwise>Rejected</c:otherwise> 
                        </c:choose> 
                    </td> 
                </tr> 
            </c:forEach> 
        </table>
    </body>
</html>