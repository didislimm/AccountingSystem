<%@ page import="com.mironov.repository.impl.HouseRepositoryImpl" %>
<%@ page import="com.mironov.services.HouseService" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
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

    /* Hide all steps by default: */
    .tab {
        display: none;
    }

    button {
        background-color: #04AA6D;
        color: #ffffff;
        border: none;
        padding: 10px 20px;
        font-size: 17px;
        font-family: Raleway;
        cursor: pointer;
    }

    button:hover {
        opacity: 0.8;
    }

</style>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Existing house:<%out.print(HouseService.getInstance(HouseRepositoryImpl.getInstance()).getAllNumberOfHouses()); %></h1>
    <form id = regForm action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="output_house">
        <p>Input number of House:</p>
            <input name="numberOfHouse" type="number" max="500" min="1" required>
            <input type="submit" value="Next" name="Next">
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
