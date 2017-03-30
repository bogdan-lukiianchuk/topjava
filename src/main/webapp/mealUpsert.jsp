<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Edit or add a meal</title>
</head>
<body>
<div align="center">
    <form action="mealsEditable?action=save" method="post">
        <label for="date">Дата: </label>
        <input type="text" name="date" value="${date}" id="date"/><br>
        <label for="time">Время: </label>
        <input type="text" name="time" value="${time}" id="time"/><br>
        <label for="description">Описание: </label>
        <input type="text" name="description" value="${description}" id="description"/><br>
        <label for="calories">Калории: </label>
        <input type="text" name="calories" value="${calories}" id="calories"/><br>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
