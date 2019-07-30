function deleteDiscipline() {
    var items = $("input[type=checkbox]:checked")
    if (items.length == 0) {
        alert("Пожалуйста, выберите, как минимум, одну дисциплину!");
        return;
    }
    var result = confirm("Вы уверены, что хотите удалить?");
    if (result){
        var idsSelected = "";
        for (var i = 0; i < items.length; ++i) {
            idsSelected = idsSelected + $(items[i]).attr("value") + ",";
        }
        $("#idsDisc").val(idsSelected);
        $('#delete-discipline-form').submit();
    }
}

function modifyDiscipline() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        alert("Пожалуйста, выберите одну дисциплину!");
        return;
    }

    if (items.length > 1) {
        alert("Пожалуйста, выберите только одну дисциплину!");
        return;
    }

    var idSelected = $(items[0]).attr("value");
    $("#modifyId").val(idSelected);
    $('#discipline-modify-form').submit();
}
function deleteStudent() {
    var items = $("input[type=checkbox]:checked")
    if (items.length == 0) {
        alert("Пожалуйста, выберите, как минимум, одного студента!");
        return;
    }
    var result = confirm("Вы уверены, что хотите удалить?");
    if (result) {
        var idsSelected = "";
        for (var i = 0; i < items.length; ++i) {
            idsSelected = idsSelected + $(items[i]).attr("value") + ",";
        }
        $("#idsStudent").val(idsSelected);
        $('#delete-student-form').submit();
    }

}
function modifyStudent() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        alert("Пожалуйста, выберите одного студента!");
        return;
    }

    if (items.length > 1) {
        alert("Пожалуйста, выберите только одного студента!");
        return;
    }

    var idSelected = $(items[0]).attr("value");
    $("#modifyId").val(idSelected);
    $('#student-modify-form').submit();
}
function studentProgressView() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        alert("Пожалуйста, выберите одного студента!");
        return;
    }

    if (items.length > 1) {
        alert("Пожалуйста, выберите только одного студента!");
        return;
    }

    var idSelected = $(items[0]).attr("value");
    $("#progressId").val(idSelected);
    $('#student-progress-form').submit();
}
function valid(){
    var state = $("#input_check").val();
    if(!state){
        return $(".error").html("Поля не должны быть пустыми!");
    }
    $("#form_check").submit();
}

function termValid(){
    var state = $("#input_check").val();
    if(!state){
        return $(".error").html("Поля не должны быть пустыми!");
    }
    state = state.trim();
    var number_exist = state.search(/\D/);
    if (number_exist!=-1){
        return $(".error").html("Введите число");
    }
    $("#form_check").submit();
}
function validStudentForm(){
    var stateLastName = $("#lastNameCheck").val();
    var stateFirstName = $("#firstNameCheck").val();
    var stateGroup = $("#groupCheck").val();
    var stateDate = $("#datepicker").val();
    if((!stateLastName)||(!stateFirstName)|| (!stateGroup)|| (!stateDate) ){
        return $(".error").html("Поля не должны быть пустыми!");
    }
    $("#form_check").submit();
}
function deleteConfirm() {
    var result = confirm("Вы уверены, что хотите удалить?");
    if (result){
        $("#deleteSubmit").attr("form", "confirmDeleteForm");
    }
}


