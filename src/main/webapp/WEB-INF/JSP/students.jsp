<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Список всех студентов</title>
    <link rel="stylesheet" href="CSS\students_style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/JavaScript/function.js"></script>
</head>

<body>
<header>
    <%@include file="header.jsp" %>
</header>
<div class="back">
    <a href="/">На главную</a>
</div>
<div class="btn_form1">
    <div style="display: inline-block">
        <form action="/student_progress" method="get" id="student-progress-form">
            <input type="hidden" name="idS" id="progressId">
        </form>
        <input type="submit" class="btn1" value="Просмотреть успеваемость выбранных студентов"
               onclick="studentProgressView()">
    </div>
    <div style="display: inline-block">
        <c:if test="${role eq 1}">
            <form action="/students_creating">
                <input type="submit" class="btn2" value="Создать студента...">
            </form>
        </c:if>
    </div>
</div>
<div class="btn_form2">
    <c:if test="${role eq 1}">
        <div style="display: inline-block">
            <form action="/students_modifying" method="get" id="student-modify-form">
                <input type="hidden" name="id" id="modifyId">
            </form>
            <input type="submit" class="btn1" value="Модифицировать выбранного студента" onclick="modifyStudent()">

        </div>
        <div style="display: inline-block">
            <form action="/students" method="post" id="delete-student-form">
                <input type="hidden" name="ids" id="idsStudent">
            </form>
            <input type="submit" class="btn2" value="Удалить выбранных студентов" onclick="deleteStudent()">
        </div>
    </c:if>
</div>
<table class="main">
    <caption>Список студентов</caption>
    <tr>
        <th class="td1"></th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Группа</th>
        <th>Дата поступления</th>
    </tr>
    <c:forEach items="${allStudents}" var="student">
        <tr>
            <td class="td1"><input type="checkbox" value="${student.id}"></td>
            <td>${student.lastName}</td>
            <td>${student.firstName}</td>
            <td>${student.group}</td>
            <td>${student.date}</td>
        </tr>
    </c:forEach>
</table>
</body>

</html>


