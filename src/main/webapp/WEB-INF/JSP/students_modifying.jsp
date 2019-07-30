<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Модификация студента</title>
    <link rel="stylesheet" href="CSS/students_style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="/JavaScript/function.js"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker();
            $("#format").on("change", function () {
                $("#datepicker").datepicker("option", "dateFormat", $(this).val());
            });
        });
    </script>
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
    <p>Для модфикации занчения, введите новые значение и нажмите кнопку "Применить"</p>
</form>
<div class="mod_form">
    <form method="post" id="form_check" action="/students_modifying">
        <input type="hidden" name="id" value="${id}">
        <p style="margin-left: 267px;">Фамилия <input name="modifiedLastName" value="${selectedStudent.lastName}" id="lastNameCheck"></p>
        <p style="margin-left: 293px;">Имя <input name="modifiedFirstName" value="${selectedStudent.firstName}"  id="firstNameCheck"></p>
        <p style="margin-left: 275px;">Группа <input name="modifiedGroup" value="${selectedStudent.group}" id="groupCheck"></p>
        <p style="margin-left: 216px;">Дата поступления <input name="modifiedDate" id="datepicker" autocomplete="off"
                                                               value="${selectedStudent.date}"></p>
    </form>
    <p style="margin-left: 318px;"><input class="btn_submit" type="submit" value="Применить" onclick="validStudentForm()"></p>
    <p class="error"></p>
</div>
</body>

</html>
