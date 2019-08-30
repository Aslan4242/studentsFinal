<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="http-equiv" content="Content-type: text/html; charset=UTF-8">
    <title>Модификация дисциплины</title>
    <link rel="stylesheet" href="CSS/students_style.css">
    <script type="text/javascript" charset="UTF-8" src="/JavaScript/function.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
<header>
    <%@include file="header.jsp" %>
</header>
<div class="back">
    <a href="/">На главную</a>
    <a class="goback" href="#" onclick="history.back();">Назад</a>
</div>
<div class="modify_title">
    <p>Для того, чтобы модифицировать дисциплину заполните все поля и нажмите кнопку "Применить"</p>
</div>
<div class="mod_form" style="padding-top: 20px">
    <form method="post" action="/disciplines_modifying" id="form_check">
        <input type="hidden" name="id" value="${id}">
        <p style="margin-left: 230px;">Название <input name="modifiedDiscipline" id="input_check" value="${disciplineName}"></p>
    </form>
    <p style="margin-left: 284px;"><input class="btn_submit" type="submit" value="Применить" onclick="valid()"></p>
    <p class="error"></p>
</div>
</body>

</html>
