<%@ page import="com.mironov.repository.impl.HouseRepositoryImpl" %>
<%@ page import="com.mironov.services.HouseService" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<style>
    * {
        box-sizing: border-box;
    }

    body {
        background-color: #f1f1f1;
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
<body>
<link rel="stylesheet" href="css/button.css">
<h1>Existing house:<%out.print(HouseService.getInstance(HouseRepositoryImpl.getInstance()).getAllNumberOfHouses());%></h1>
<form id="regForm" action="${pageContext.request.contextPath}/controller?command=check_values">
    <input type="hidden" name="command" value="check_values">
    <h1>First Step:</h1>
    <div class="tab">
        <p>Input number of House:</p>
        <input name="numberOfHouse" type="number" max="500" min="1" required>
        <p>Input value of floors</p>
        <input name="valueOfFloors" type="number" max="200" min="1" required>
        <p>Choose value of flats in floor:</p>
        <input max="10"  min="2" name="valueOfFlats" id="inputId" type="range" oninput="outputId.value = inputId.value" required>
        <output name="value" id="outputId">6</output>
        <input type="submit" class="button" value="Next">
    </div>
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

