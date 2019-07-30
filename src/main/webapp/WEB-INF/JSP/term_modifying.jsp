<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Модификация семестра</title>
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
<div class="modify_title">
    <p>Для модификации семестра отредактируйте данные и нажмите кнопку "Применить"</p>
</div>
<div class="mod_form" style="padding-top: 20px">
    <form method="post" id="form_check" action="/term_modifying">
        <p style="margin-left: 230px;">Длительность (в неделях) <input type="text" name="modifiedDuration" id="input_check" value="${selectedTermDuration}"></p>
        <input type="hidden" name="id" value="${selectedTerm.id}">
        <select class="opt_progress1" name="modifiedDisciplinesId" multiple style="margin-left: 373px; width: 173px">
            <c:forEach items="${allDisciplines}" var="disciplines">
                <option value="${disciplines.id}">${disciplines.discipline}</option>
            </c:forEach>
        </select>
    </form>
    <p style="margin-left: 374px;"><input class="btn_submit" type="submit" value="Применить" onclick="termValid()"></p>
    <p class="error"></p>
</div>
</body>

</html>
