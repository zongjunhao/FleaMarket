function check_user_name() {
    if ($("#user_name").val() == "") {
        $("#user_name").css("border-color", "#F05A5A");
        $("#user_tip").css("color", "#F05A5A");
        return false;
    } else {
        $("#user_name").css("border-color", "#C7C7C7");
        $("#user_tip").css("color", "#FFFFFF");
        return true;
    }
}

function user_name_recovery() {
    $("#user_name").css("border-color", "#2984EF");
    $("#user_tip").css("color", "#FFFFFF");
}


function check_user_pwd() {
    if (($("#user_password").val() == "")) {
        $("#user_password").css("border-color", "#F05A5A");
        $("#user_pwd_tip").css("color", "#F05A5A");
        return false;
    } else {
        $("#user_password").css("border-color", "#C7C7C7");
        $("#user_pwd_tip").css("color", "#FFFFFF");
        return true;
    }
}

function user_pwd_recovery() {
    $("#user_password").css("border-color", "#2984EF");
    $("#user_pwd_tip").css("color", "#FFFFFF");
}

function check_user_code() {
    if (($("#user_inputRandomCode").val() == "")) {
        $("#user_inputRandomCode").css("border-color", "#F05A5A");
        $("#user_code_tip").css("color", "#F05A5A");
        return false;
    } else {
        $("#user_inputRandomCode").css("border-color", "#C7C7C7");
        $("#user_code_tip").css("color", "#FFFFFF");
        return true;
    }
}

function user_code_recovery() {
    $("#user_inputRandomCode").css("border-color", "#2984EF");
    $("#user_code_tip").css("color", "#FFFFFF");
}


function check_admin_name() {
    if (($("#admin_name").val() == "")) {
        $("#admin_name").css("border-color", "#F05A5A");
        $("#admin_tip").css("color", "#F05A5A");
        return false;
    } else {
        $("#admin_name").css("border-color", "#C7C7C7");
        $("#admin_tip").css("color", "#FFFFFF");
        return true;
    }
}

function admin_name_recovery() {
    $("#admin_name").css("border-color", "#2984EF");
    $("#admin_tip").css("color", "#FFFFFF");
}


function check_admin_pwd() {
    if (($("#admin_password").val() == "")) {
        $("#admin_password").css("border-color", "#F05A5A");
        $("#admin_pwd_tip").css("color", "#F05A5A");
        return false;
    } else {
        $("#admin_password").css("border-color", "#C7C7C7");
        $("#admin_pwd_tip").css("color", "#FFFFFF");
        return true;
    }
}

function admin_pwd_recovery() {
    $("#admin_password").css("border-color", "#2984EF");
    $("#admin_pwd_tip").css("color", "#FFFFFF");
}

function check_admin_code() {
    if (($("#admin_inputRandomCode").val() == "")) {
        $("#admin_inputRandomCode").css("border-color", "#F05A5A");
        $("#admin_code_tip").css("color", "#F05A5A");
        return false;
    } else {
        $("#admin_inputRandomCode").css("border-color", "#C7C7C7");
        $("#admin_code_tip").css("color", "#FFFFFF");
        return true;
    }
}

function admin_code_recovery() {
    $("#admin_inputRandomCode").css("border-color", "#2984EF");
    $("#admin_code_tip").css("color", "#FFFFFF");
}


function user_click() {
    $("#admin_nav").removeClass("curr");
    $("#user_nav").addClass("curr");
    $("#admin_tab").removeClass("active");
    $("#user_tab").addClass("active");
}

function admin_click() {
    $("#user_nav").removeClass("curr");
    $("#admin_nav").addClass("curr");
    $("#user_tab").removeClass("active");
    $("#admin_tab").addClass("active");
}

function user_login() {
    var flag = check_user_name();
    flag = check_user_pwd() && flag;
    flag = check_user_code() && flag;
    if (flag) {
        $.ajax({
            type: "POST",
            url: "/user/login",
            data: {
                u_stuid: $("#user_name").val(),
                u_pwd: $("#user_password").val(),
                inputRandomCode: $("#user_inputRandomCode").val()
            },
            success: function (json) {
                console.log(json);
                if (json.resultCode == "4000") {
                    //登录成功
                    //alert(json.resultDesc);
                    $.session.set("u_id", json.data.u_id);
                    $.session.set("u_name", json.data.u_name);
                    $.session.set("u_gender", json.data.u_gender);
                    $.session.set("u_phone", json.data.u_phone);
                    $.session.set("u_stuid", json.data.u_stuid);
                    $.session.set("u_qq", json.data.u_qq);
                    $.session.set("u_pwd", json.data.u_pwd);
                    $.session.set("role", "2");
                    console.log($.session.get("role"));
                    window.location.href = "/pages/index.html";
                } else {
                    //登录失败
                    //alert(json.resultDesc);
                    $("#user_code_tip").text(json.resultDesc);
                    $("#user_code_tip").css("color", "#F05A5A");
                    $("#user_pic").attr('src', getRootPath() + '/user/pic?x=' + Math.random());
                }
            },
            error: function (jqXHR) {
                $("#user_code_tip").text("您所请求的页面有异常");
                $("#user_code_tip").css("color", "#F05A5A");
            }
        });
    }
}

function admin_login() {
    var flag = check_admin_name();
    flag = check_admin_pwd() && flag;
    flag = check_admin_code() && flag;
    if (flag) {
        $.ajax({
            type: "POST",
            url: "/admin/login",
            data: {
                account: $("#admin_name").val(),
                password: $("#admin_password").val(),
                inputRandomCode: $("#admin_inputRandomCode").val()
            },
            success: function (json) {
                console.log(json);
                if (json.resultCode == "4000") {
                    //登录成功
                    //alert(json.resultDesc);
                    $.session.set("role", "1");
                    $.session.set("a_id", json.data.a_id);
                    $.session.set("a_account", json.data.a_account);
                    $.session.set("a_pwd", json.data.a_pwd);
                    window.location.href = "/pages/index.html";
                } else {
                    //登录失败
                    //alert(json.resultDesc);
                    $("#admin_code_tip").text(json.resultDesc);
                    $("#admin_code_tip").css("color", "#F05A5A");
                    $("#admin_pic").attr('src', getRootPath() + '/admin/pic?x=' + Math.random());
                }
            },
            error: function (jqXHR) {
                $("#admin_code_tip").text("您所请求的页面有异常");
                $("#admin_code_tip").css("color", "#F05A5A");
            }
        });
    }
}

function refreshUserCode() {
    $("#user_pic").attr('src', getRootPath() + '/user/pic?x=' + Math.random());
}

function refreshAdminCode() {
    $("#admin_pic").attr('src', getRootPath() + '/admin/pic?x=' + Math.random());
}

function getRootPath() {
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    console.log(prePath);
    return (prePath);
}