<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Успеваемость студента</title>
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
    <p>Отображена успеваемость для следующего студента: </p>
</form>
<table class="main">
    <tr style="width: 196px;height: 25px; vertical-align: middle;">
        <th style="width: 196px;height: 25px; vertical-align: middle;">Фамилия</th>
        <th style="width: 196px;height: 25px; vertical-align: middle;">Имя</th>
        <th style="width: 196px;height: 25px; vertical-align: middle;">Группа</th>
        <th style="width: 196px;height: 25px; vertical-align: middle;">Дата поступления</th>
    </tr>
    <tr>
        <td style="width: 196px;height: 25px; vertical-align: middle;">${selectedStudent.lastName}</td>
        <td style="width: 196px;height: 25px; vertical-align: middle;">${selectedStudent.firstName}</td>
        <td style="width: 196px;height: 25px; vertical-align: middle;">${selectedStudent.group}</td>
        <td style="width: 196px;height: 25px; vertical-align: middle;">${selectedStudent.date}</td>


    </tr>
</table>
<table style="float: left" class="main">
    <tr>
        <th class="tb_progress1">Дисциплина</th>
        <th class="tb_progress2" style="width: 100px">Оценка</th>
    </tr>
    <c:forEach var="entry" items="${disciplinesAndMarkByTerm}">
        <tr>
            <td class="tb_progress1">${entry.key.discipline}</td>
            <td class="tb_progress2" style="width: 100px">${entry.value}</td>
        </tr>
    </c:forEach>

</table>
<div class="form_term">
    <form method="get" action="/student_progress">
        <input type="hidden" name="idS" value="${idS}">
        <p style="padding: 10px">
            Выбрать семестр
            <select name="term_select" class="opt_progress1">
                <option selected hidden value="${firstTerm.id}">${firstTerm.term}</option>
                <c:forEach items="${studentsTerm}" var="term">
                    <option value="${term.id}">${term.term}</option>
                </c:forEach>
            </select>
            <input class="opt_progress2" type="submit" value="выбрать">
        </p>
    </form>

    <p style="padding: 10px">
        Средняя оценка за семестр: ${avgMark}
    </p>

</div>
</body>

</html>

