<%-- 
    Document   : agenda.jsp
    Created on : Jun 15, 2025, 10:24:58 PM
    Author     : saiki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agenda</title>
    <style>
        .working { background-color: green; }
        .on-leave { background-color: red; }
    </style>
</head>
<body>
    <h2>Agenda</h2>
    <form action="AgendaController" method="GET">
        <label>From Date:</label>
        <input type="date" name="fromDate" required>
        <label>To Date:</label>
        <input type="date" name="toDate" required>
        <input type="submit" value="View Agenda">
    </form>
    <c:if test="${not empty agenda}">
        <table border="1">
            <tr>
                <th>Nhân sự</th>
                <c:forEach items="${requestScope.dates}" var="date">
                    <th>${date}</th>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.agenda}" var="entry">
                <tr>
                    <td>${entry.key}</td>
                    <c:forEach items="${entry.value}" var="status">
                        <td class="${status ? 'working' : 'on-leave'}"></td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>