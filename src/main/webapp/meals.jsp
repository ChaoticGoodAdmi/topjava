<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/meals.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="meals?id=&action=update">Add meal</a>
<br>
<br>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
    <c:forEach var="meal" items="${mealList}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd hh:mm" var="parsedDateTime"/>
        <tr id="${meal.excess ? 'excess' : 'not-excess'}">
            <td>${parsedDateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?id=${meal.id}&action=update">Update</a></td>
            <td><a href="meals?id=${meal.id}&action=delete">Delete</a></td>
        </tr>
    </c:forEach>
    <tr>
    </tr>
</table>
</body>
</html>