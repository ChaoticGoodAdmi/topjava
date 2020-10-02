<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<form method="post" action="meals">
    <input type="hidden" name="pageName" value="limit"/>
    <label>New calories limit:
        <input type="number" name="limit" value="<%=request.getAttribute("limit")%>" required>
    </label>
    <br>
    <button type="submit">Save</button>
    <button type="button" onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
