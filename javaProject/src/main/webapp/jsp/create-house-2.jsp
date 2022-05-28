<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<body>
<link rel="stylesheet" href="css/button.css">
<style>
    * {
        box-sizing: border-box;
    }
    h1 {
        text-align: center;
    }
    input {
        padding: 10px;
        width: 100%;
        font-size: 17px;
        font-family: Raleway;
        border: 1px solid #aaaaaa;
    }
</style>

<form id="regForm" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="create_house">
    <h1>Second Step:</h1>
    <c:forEach var="number" begin="1" end="${sessionScope.valueOfFlats}">
        Square of <c:out value="${number}"/> flat<p>
        <input type="number" min="12" max="200" step="0.1" name="square${number}" required>
    </c:forEach><p>
        <input type="submit" value="Create House" onclick="document.location='/index.jsp'">
</form>

    <script>
        var currentTab = 0;
        showTab(currentTab);

        function showTab(n) {
            var x = document.getElementsByClassName("tab");
            x[n].style.display = "block";
        }
    </script>
</body>
</html>

