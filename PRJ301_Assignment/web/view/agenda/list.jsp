<%-- 
    Document   : list.jsp
    Created on : Jul 14, 2025, 11:46:15 AM
    Author     : saiki
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head> 
        <title>Department leave schedule</title> 
        <style>
            table {
                border-collapse: collapse;
                margin: 20px 0;
            }
            th, td {
                padding: 8px 14px;
                text-align: center;
                border: 1px solid #333;
            }
            th {
                background: #e8e8e8;
            }
            tr:nth-child(even) {
                background: #f6f6f6;
            }
            .btn {
                padding: 6px 16px;
            }
        </style>
    </head>
    <body>
        <h2>Holiday Schedule from ${from} to ${to}</h2>

        <!-- Date Range Selection Form -->
        <form method="get" action="">
            From Date: <input type="date" name="from" value="${from}">
            To Date: <input type="date" name="to" value="${to}">
            <button type="submit" class="btn">View</button>
        </form>

        <!-- Show message if no data -->
        <c:choose>
            <c:when test="${empty rfls}">
                <p>There are no leave requests during this period.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Employee Name</th>
                        <th>Reason</th>
                        <th>From Date</th>
                        <th>To Date</th>
                        <th>Status thai</th> 
                        <th>Details</th> 
                    </tr> 
                    <c:forEach var="r" items="${rfls}"> 
                        <tr> 
                            <td>${r.createdby.username}</td> 
                            <td>${r.reason}</td> 
                            <td>${r.from}</td> 
                            <td>${r.to}</td> 
                            <td> 
                                <c:choose> 
                                    <c:when test="${r.status == 1}">Rest (Approved)</c:when> 
                                    <c:otherwise>Unknown</c:otherwise> 
                                </c:choose> 
                            </td> 
                            <td> 
                                <a href="../rfl/details?id=${r.id}" target="_blank">View</a> 
                            </td> 
                        </tr> 
                    </c:forEach> 
                </table> 
            </c:otherwise>
        </c:choose>
    </body>
</html>