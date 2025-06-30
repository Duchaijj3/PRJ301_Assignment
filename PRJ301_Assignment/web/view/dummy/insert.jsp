<%-- 
    Document   : insert
    Created on : Jun 16, 2025, 11:02:55 AM
    Author     : p14s
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert new dummy</title>
    </head>
    <body>
        <form action="insert" method="POST">
            Name: <input type="text" name="name"/>
            Created by:${sessionScope.account.displayname} <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
