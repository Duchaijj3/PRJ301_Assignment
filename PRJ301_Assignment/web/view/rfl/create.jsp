<%-- 
    Document   : create
    Created on : Jun 30, 2025, 10:28:52 AM
    Author     : sonnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new Request for Leave</title>
    </head>
    <body>
        <form action="create" method="POST">
            Title: <input type="text" name="title"/> <br/>
            From:<input type="date" name="from"/> <br/>
            To:<input type="date" name="to" /> <br/>
            Reason: <input type="text" name="reason"/> <br/>
            <input type="submit" value="Request"/>
        </form>
    </body>
</html>
