function selectFirst(name){
    var radioButtons = document.getElementsByName(name);

    if(radioButtons.length > 0) radioButtons[0].checked = true;
}

function deselectRadios(name){
    var radioButtons = document.getElementsByName(name);

    for(var i = 0; i < radioButtons.length; i++){
        if(radioButtons[i].type == "radio"){
            radioButtons[i].checked = false;
        }
    }
}

function selectCheckboxes(name, value){
    var arrayCheckbox = document.getElementsByName(name);

    for(var i = 0; i < arrayCheckbox.length; i++){
        if(arrayCheckbox[i].type == "checkbox") {
            arrayCheckbox[i].checked = value;
        }
    }
}

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
    confirm("Se va a consultar la clave del usuario " + user)
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

function confirmOperation(message){
     return confirm(message);
}

function confirmForm(message){
    if(confirm(message))
        $("#form").submit()
}

function lockUserByList(userId) {
    var daysLock = prompt("Introduzca los dias de bloqueo.")

    if(daysLock && confirmOperation("Se va a bloquear al usuario " + userId)){
        $.ajax
        ({
            url: "/user/lock",
            type: "get",
            data: "userId=" + userId +  "&daysLock=" + daysLock,
        }).done(function (data) {
            location.reload()
            alert("User has been locked until " + data)
        }).fail(function (e, textStatus) {
            alert("Request failed: " + textStatus)
        });
    }
}

function unlockUserByList(userId) {
    if(confirmOperation("Se va a desbloquear al usuario " + userId)){
        $.ajax
        ({
            url: "/user/unlock",
            type: "get",
            data: "userId=" + userId
        }).done(function (data) {
            if(data == true){
                location.reload()
                alert("User has been unlocked");
            }
        }).fail(function (e, textStatus) {
            alert("Request failed: " + textStatus)
        });
    }
}

function removeUserByList(userId) {
    if(confirmOperation("Se va a borrar al usuario " + userId)){
        $.ajax
        ({
            url: "/user/remove",
            type: "get",
            data: "userId=" + userId
        }).done(function (data) {
            alert("User has been removed");
            location.reload()
        }).fail(function (e, textStatus) {
            alert("Request failed: " + textStatus)
        });
    }
}

function recoverUserByList(userId) {
    if(confirmOperation("Se va a recuperar al usuario " + userId)){
        $.ajax
        ({
            url: "/user/recover",
            type: "get",
            data: "userId=" + userId
        }).done(function (data) {
            alert("User has been recoved")
            location.reload()
        }).fail(function (e, textStatus) {
            alert("Request failed: " + textStatus)
        });
    }
}

function removeAllPromotions() {
    if(confirmOperation("Se van a borrar todas las promociones vacias")){
        $.ajax
        ({
            url: "/promotion/deleteAll",
            type: "DELETE"
        }).done(function (data) {
            alert("Borrado con exito.")
            location.reload()
        }).fail(function (e, textStatus) {
            alert("Request failed: " + textStatus)
        });
    }
}

function updateOrderState(){
    var newState = $("#newState").val()
    var orderId = $("#orderId").val()

    if(confirmOperation("Se va actualizar el estado del pedido")){
        $.ajax
        ({
            url: "/order/updateState",
            type: "PUT",
            data: "orderId=" + orderId + "&newState=" + newState
        }).done(function (data) {
            alert("Estado actualizado con exito.")
            location.reload()
        }).fail(function (e, textStatus) {
            alert("Request failed")
        });
    }

}

function enableUpdateButton(){
    const newState = $("#newState").val()
    const currentValue = $("#currentState").val()

    const button = $("#updateStateButton").prop('disabled', (newState == currentValue))
}

function processWarning(warningId) {
    if(confirmOperation("Se va a procesar el aviso " + warningId)){
        $.ajax
        ({
            url: "/warning/process",
            type: "get",
            data: "id=" + warningId
        }).done(function (data) {
            alert("Warning has been processed")
            location.reload()
        }).fail(function (e, textStatus) {
            alert("Request failed: " + textStatus)
        });
    }
}

/*
function lockUser(userId) {
    $.ajax
    ({
        url: "/user/lock",
        type: "get",
        data: "userId=" + userId
    }).done(function (data) {
        alert("User has been locked until " + data)
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
        alert("User has been unlocked");
    }).fail(function (e, textStatus) {
        alert("Request failed: " + textStatus)
    });
}*/