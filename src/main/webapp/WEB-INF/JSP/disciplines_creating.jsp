<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Создание дисциплины</title>
    <link rel="stylesheet" href="CSS/students_style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/JavaScript/function.js"></script>
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
    <p>Для того, чтобы создать новую дисциплину заполните все поля и нажмите кнопку "Создать"</p>
</form>
<div class="mod_form" style="padding-top: 20px" >
    <form method="post" id="form_check" action="/disciplines_creating">
    <p style="margin-left: 230px;">Название <input type="text" name="disciplineName" id="input_check"></p>
    </form>
    <p style="margin-left: 284px;"><input class="btn_submit" type="submit" value="Создать" onclick="valid()"></p>
    <p class="error"></p>
</div>
</body>

</html>
