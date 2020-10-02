<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<h2>Edit meal</h2>
<form method="post" action="meals">
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <input type="hidden" name="pageName" value="update"/>
    <input type="hidden" name="id" value="${meal.id}">
    <table>
        <tr>
            <td>DateTime:</td>
            <td><label>
                <input type="datetime-local" name="dateTime" value="${meal.dateTime}" required>
            </label></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><label>
                <input type="text" name="description" value="${meal.description}" required>
            </label></td>
        </tr>
        <tr>
            <td>Calories:</td>
            <td>
                <label>
                    <input type="number" min="0" name="calories" value="${meal.calories}" required>
                </label>
            </td>
        </tr>
    </table>
    <button type="submit">Save</button>
    <button type="button" onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
