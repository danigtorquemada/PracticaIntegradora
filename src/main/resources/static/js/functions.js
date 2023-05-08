function showPasswords() {
    var password = document.getElementById("password");
    var password2 = document.getElementById('confirmPwd');

    password.type = password.type == "text" ? "password" : "text";
    password2.type = password2.type == "text" ? "password" : "text";
}

function showPwd() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

function forgotPwd() {
    var user = $("#currentUser").text()
    alert("Se va a consultar la clave del usuario " + user)
    $.ajax
    ({
        url: "/user/password_user",
        type: "get",
        data: "user=" + user
    }).done(function (data) {
        alert(data);
    }).fail(function (e, textStatus) {
        alert("Request failed: " + textStatus)
    });
}

function lockUserByList(userId) {
    var lockDateElement = document.getElementById('lockDate_' + userId)

    $.ajax
    ({
        url: "/user/lock",
        type: "get",
        data: "userId=" + userId
    }).done(function (data) {
        lockDateElement.textContent = data
    }).fail(function (e, textStatus) {
        alert("Request failed: " + textStatus)
    });
}

function unlockUserByList(userId) {
    var lockDateElement = document.getElementById('lockDate_' + userId)

    $.ajax
    ({
        url: "/user/unlock",
        type: "get",
        data: "userId=" + userId
    }).done(function (data) {
        lockDateElement.textContent = data
    }).fail(function (e, textStatus) {
        alert("Request failed: " + textStatus)
    });
}

function lockUser(userId) {
    $.ajax
    ({
        url: "/user/lock",
        type: "get",
        data: "userId=" + userId
    }).done(function (data) {
        alert(data)
    }).fail(function (e, textStatus) {
        alert("Request failed: " + textStatus)
    });
}

function unlockUser(userId) {
    $.ajax
    ({
        url: "/user/unlock",
        type: "get",
        data: "userId=" + userId
    }).done(function (data) {
        alert(data);
    }).fail(function (e, textStatus) {
        alert("Request failed: " + textStatus)
    });
}