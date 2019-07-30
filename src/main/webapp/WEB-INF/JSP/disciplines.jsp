<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Список дисциплин</title>
    <link rel="stylesheet" href="CSS/students_style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/JavaScript/function.js"></script>
</head>

<body>
<header>
    <%@include file="header.jsp" %>
</header>
<div>
    <div class="back">
        <a href="/">На главную</a>
    </div>
    <form class="modify_title" style="padding-left: 63px">
        <p>Список дисциплин </p>
    </form>
</div>

<table align="left" class="main">
    <tr>
        <th class="td1"></th>
        <th>Наименование дисциплины</th>
    </tr>
    <c:forEach items="${allDisciplines}" var="disciplines">
        <tr>
            <td class="td1"><input value="${disciplines.id}" type="checkbox"></td>
            <td class="td2">${disciplines.discipline}</td>
        </tr>
    </c:forEach>
</table>

<c:if test="${role eq 1}">
    <div class="form_btn">
        <form action="/disciplines_creating">
            <input class="opt_progress2" style="width: 270px; padding: 3px" type="submit" value="Создать дисциплину...">
        </form>
    </div>
    <div class="form_btn">
        <form action="/disciplines_modifying" method="get" id="discipline-modify-form">
            <input type="hidden" name="id" id="modifyId">
        </form>
        <input class="opt_progress2"  style="width: 270px; padding: 3px" type="submit" value="Модифицировать выбранную дисциплину..." onclick="modifyDiscipline()">

    </div>
    <div class="form_btn" >
        <form action="/disciplines" method="post" id="delete-discipline-form">
            <input type="hidden" name="ids" id="idsDisc">
        </form>
        <input  class="opt_progress2" style="width: 270px; padding: 3px" type="submit" value="Удалить выбранную дисциплину..." onclick="deleteDiscipline()">
    </div>
</c:if>


</body>

</html>

