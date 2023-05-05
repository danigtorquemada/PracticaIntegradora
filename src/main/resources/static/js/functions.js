function showPasswords(){
    var password = document.getElementById("password");
    var password2 = document.getElementById('confirmPwd');

    password.type = password.type == "text" ? "password" : "text";
    password2.type = password2.type == "text" ? "password" : "text";
}