<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        tr.exceed {
            color: red;
        }
        tr.ok {
            color: green;
        }
    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table style="margin-left: auto; margin-right: auto">
    <thead>
    <tr role="row">
        <th>Дата</th>
        <th>Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr class="${meal.exceed ? 'exceed' : 'ok'}">
            <td>
                <div style="text-align: left">
                        ${fn:substringBefore(meal.dateTime, "T")}
                </div>
            </td>
            <td>
                <div style="text-align: left">
                        ${fn:substringAfter(meal.dateTime, "T")}
                </div>
            </td>

            <td width="300">${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <a href="<c:url value='/mealsEditable?action=edit&id=${meal.id}'/>">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="<c:url value='/mealsEditable?action=delete&id=${meal.id}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
