<%@ page import="ru.javawebinar.topjava.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="request"/>
    <form method="post" action="users">
        <input type="hidden" name="id" value="${user.id}">
        <dl>
            <dt>Username:</dt>
            <dd><input type="text" value="${user.name}" name="name"></dd>
        </dl>
        <dl>
            <dt>E-mail:</dt>
            <dd><input type="text" value="${user.email}" size=40 name="email"></dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd><input type="text" value="${user.password}" name="password"></dd>
        </dl>
        <c:set var="roleAdmin" value="<%=Role.ROLE_ADMIN%>"/>
        <dl>
            <dt>Admin?</dt>
            <dd><input type="checkbox" name="admin" <c:if test="${user.roles.contains(roleAdmin)}">checked</c:if> ></dd>
        </dl>
        ${user.roles.contains(roleAdmin)}
<!--        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" value="${user.caloriesPerDay}" name="calories"></dd>
        </dl>
-->
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>