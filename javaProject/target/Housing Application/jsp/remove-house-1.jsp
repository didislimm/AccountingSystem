<%@ page import="com.mironov.repository.impl.HouseRepositoryImpl" %>
<%@ page import="com.mironov.services.HouseService" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
    * {
        box-sizing: border-box;
    }

    body {
        background-color: #f1f1f1;
    }

    #regForm {
        background-color: #ffffff;
        margin: 100px auto;
        font-family: Raleway;
        padding: 40px;
        width: 70%;
        min-width: 300px;
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

    .tab {
        display: none;
    }
</style>
<html>
<head>
</head>
<body>
<h1>Existing house:<%out.print(HouseService.getInstance(HouseRepositoryImpl.getInstance()).getAllNumberOfHouses()); %></h1>
<form id = regForm action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="remove_house">
    <p>Input number of House:</p>
    <input name="numberOfHouse" type="number" max="500" min="1" required>
    <input type="submit" value="Remove" >
</form>
</body>


<script>
    var currentTab = 0;
    showTab(currentTab);

    function showTab(n) {
        var x = document.getElementsByClassName("tab");
        x[n].style.display = "block";
    }
    function showHouse(){
    }
</script>
</html>
