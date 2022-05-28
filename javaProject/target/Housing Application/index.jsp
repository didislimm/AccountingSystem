<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
</head>
<body>
<link rel="stylesheet" href="<c:url value="/css/button.css"/>">
    <h1 align="center">Menu</h1>
        <button class="button" onclick="document.location='/jsp/create-house-1.jsp'" >Create House</button>
        <button class="button" onclick="document.location='/jsp/output-house-1.jsp'">To know information about house</button>
        <button class="button" onclick="document.location='/jsp/remove-house-1.jsp'">Remove house</button>
        <button class="button">Compare two houses</button>
        <button class="button">To know values of free flat in house</button>
        <button class="button">To check status of flat</button>
</form>
</body>
</html>
