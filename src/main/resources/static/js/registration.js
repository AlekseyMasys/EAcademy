function getRegistration() {
    var login = $("input#login").val();
    var fio = $("input#fio").val();
    var email = $("input#email").val();
    var role = $("input#role").val();
    var password = $("input#password").val();

    alert(login + " " + password);

    $.ajax({
        type: "POST",
        url: "/registration_user",
        contentType:"application/json",
        dataType: 'json',
        async: false,
        data: "{\"login\": \"" + login + "\", \"password\": \"" + password + "\", \"email\": \"" +  email +" \", \"fio\": \"" + fio + "\", \"role\": \"" + role + "\"}",
        success: function () {
            alert('Registration done!!');
        }
    });
}