<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
    <link rel="stylesheet" href="CSS\students_style.css">
</head>
<body>
<header>
    <%@include file="WEB-INF/JSP/header.jsp"%>
</header>
<div class="logo_wrap">
    <div class="nav">
        <a href="/students">Студенты</a>
        <a href="/disciplines">Дисциплины</a>
        <a href="/term">Семестры</a>
    </div>
</div>


</body>
</html>
