<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
    <link rel="stylesheet" href="CSS/students_style.css">
</head>

<body>
<header>
    <%@include file="header.jsp" %>
</header>
<div class="back">
    <a href="/">На главную</a>
    <a class="goback" href="#" onclick="history.back();">Назад</a>
</div>
<form class="modify_title">
    <c:choose>
        <c:when test="${isLogin eq 1}">
            <p>Пользователь: ${username}</p>
        </c:when>
        <c:otherwise>
            <p>Для входа заполните все поля и нажмите кнопку "Войти".</p>
        </c:otherwise>
    </c:choose>
</form>
<div class="mod_form">
    <c:choose>
        <c:when test="${isLogin eq 1}">
            <form action="/login" method="post">
                <p style="margin-left: 232px;"><input class="btn_submit" name="logout" type="submit" value="Выйти"></p>
            </form>
        </c:when>
        <c:otherwise>
            <form action="/login" method="post">
                <p style="margin-left: 290px">Логин <input name="username"></p>
                <p style="margin-left: 283px">Пароль <input name="pass" type="password"></p>
                <p style="margin-left: 329px;"><input class="btn_submit" type="submit" value="Войти"></p>
            </form>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>