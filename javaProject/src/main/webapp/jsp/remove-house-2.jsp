<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<link rel="stylesheet" href="css/button.css">
<h2><b>Removed House:</b></h2><p>
${sessionScope.house.toString()}
<input class="button" onclick="document.location='/index.jsp'" value="Menu">
</body>
</html>
