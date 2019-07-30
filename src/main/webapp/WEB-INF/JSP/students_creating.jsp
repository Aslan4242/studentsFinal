<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Создание студента</title>
    <link rel="stylesheet" href="CSS/students_style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="/JavaScript/function.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
            $( "#format" ).on( "change", function() {
                $( "#datepicker" ).datepicker( "option", "dateFormat", $( this ).val() );
            });
        } );
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
    <p>Для создания студента заполните все поля и нажмите кнопку "Создать"</p>
</form>
<div class="mod_form" >
    <form method="post" id="form_check" action="/students_creating">
        <p style="margin-left: 290px;">Фамилия <input type="text" name="lastName" id="lastNameCheck"></p>
        <p style="margin-left: 316px;">Имя  <input type="text" name="firstName" id="firstNameCheck"></p>
        <p style="margin-left: 299px;">Группа <input type="text" name="group" id="groupCheck"></p>
        <p style="margin-left: 240px;">Дата поступления <input type="text" name="date" id="datepicker" autocomplete="off"></p>
    </form>
    <p style="margin-left: 342px;"><input class="btn_submit" type="submit" value="Создать" onclick="validStudentForm()"></p>
    <p class="error"></p>
</div>

</body>

</html>

