<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mironov.model.House" %>
<html>
<head>
</head>
<body>
<link rel="stylesheet" href="css/button.css">
        ${sessionScope.house.toString()}
        <input class="button" onclick="document.location='/index.jsp'" value="Menu">
</body>
</html>
