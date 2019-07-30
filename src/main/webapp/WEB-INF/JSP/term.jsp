<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Семестры</title>
    <link rel="stylesheet" href="CSS/students_style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
</div>
<div style="padding-left: 216px">
    <form  action="/term" method="post">
        <p style="padding: 10px">
            Выбрать семестр
            <select name="term_select" class="opt_progress1" >
                <option selected hidden value="${selectedTerm.id}">${selectedTerm.term}</option>
                <c:forEach items="${allTerms}" var="terms">
                    <option value="${terms.id}">${terms.term}</option>
                </c:forEach>
            </select>
            <input class="opt_progress2" name="submit" type="submit" value="выбрать">
        </p>

        <p style="padding-left: 10px">
            Длительность семестра: ${selectedTerm.duration}
        </p>
    </form>
</div>
<table align="left" class="main" style="    width: 280px;">
    <caption>Список дисциплин семестра</caption>
    <tr>
        <th>Наименование дисциплины</th>
    </tr>
    <c:forEach items="${allDisciplinesByTerm}" var="discipline">
        <tr>
            <td class="td2">${discipline.discipline}</td>
        </tr>
    </c:forEach>

</table>

<c:if test="${role eq 1}">
    <div style="padding-top: 38px">
        <form class="form_btn" action="/term_creating">
            <input class="opt_progress2" style="width: 270px; padding: 3px" type="submit" value="Создать семестр...">
        </form>
        <form action="/term_modifying" method="get">
            <input type="hidden" name="idModifyTerm" value="${selectedTerm.id}">
            <div class="form_btn">
                <input class="opt_progress2" style="width: 270px; padding: 3px" type="submit"
                       value="Модифицировать текущий семестр...">
            </div>
        </form>
        <div class="form_btn" style="align-self: flex-end">
            <form action="/term" method="post" id="confirmDeleteForm">
                <input type="hidden" name="idToDelete" value="${selectedTerm.id}">
            </form>
            <input class="opt_progress2" style="width: 270px; padding: 3px" type="submit" id="deleteSubmit" value="Удалить текущий семестр" onclick="deleteConfirm()">
        </div>
    </div>
</c:if>
</body>
</html>

