$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: window.open() + "/get",
        contentType: "aplication/json",
        dataType: 'json',
        async: false,
        success: function (response) {
            $(function () {
                var teacherInfo = JSON.parse(response);
                console.log(teacherInfo);
                document.getElementById("teacherLogin").append(teacherInfo.login);
            })
        }
    })
});