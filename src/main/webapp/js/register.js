function submit() {
    console.log("submit");
    if (checkStuid() && checkName() && checkQq() && checkPassword() && checkConfirmPassword()) {
        $.ajax({
            type: "POST",
            url: "/user/register",
            data: {
                u_stuid: $(".u_stuid").val(),
                u_name: $(".u_name").val(),
                u_qq: $(".u_qq").val(),
                u_pwd: $(".u_password").val()
            },
            success: function (json) {
                if (json.resultCode == "5001") {
                    window.location.href = "/pages/login.html";
                } else {
                    // alert("您所请求的页面有异常");
                    console.log(json.resultCode)
                    console.log("状态码错误")
                    $("#submit_tip").text(json.resultDesc)
                }
            },
            error: function (jqXHR) {
                // alert("您所请求的页面有异常");
                console.log("错误")
                $("#submit_tip").text("您所请求的页面有异常")
            }
        })
    }
}

$(function () {
    $(".u_stuid").on("blur", function () {
        checkStuid()
    });
    $(".u_name").on("blur", function () {
        checkName()
    });

    $(".u_qq").on("blur", function () {
        checkQq()
    });

    $(".u_password").on("blur", function () {
        checkPassword()
    });

    $(".confirm-password").on("blur", function () {
        checkConfirmPassword()
    });
});

function checkStuid() {
    if (($(".u_stuid").val() == "")) {
        //console.log("empty")
        $(".icon-stuid").removeClass("icon-tongguo").addClass("icon-jujue");
        return false;
    } else {
        //console.log("not")
        $(".icon-stuid").removeClass("icon-jujue").addClass("icon-tongguo");
        return true;
    }
}

function checkName() {
    if (($(".u_name").val() == "")) {
        //console.log("empty")
        $(".icon-name").removeClass("icon-tongguo").addClass("icon-jujue");
        return false;
    } else {
        //console.log("not")
        $(".icon-name").removeClass("icon-jujue").addClass("icon-tongguo");
        return true;
    }
}

function checkQq() {
    if (($(".u_qq").val() == "")) {
        //console.log("empty")
        $(".icon-qq").removeClass("icon-tongguo").addClass("icon-jujue");
        return false;
    } else {
        //console.log("not")
        $(".icon-qq").removeClass("icon-jujue").addClass("icon-tongguo");
        return true;
    }
}

function checkPassword() {
    if (($(".u_password").val() == "")) {
        //console.log("empty")
        $(".icon-password").removeClass("icon-tongguo").addClass("icon-jujue");
        return false;
    } else {
        //console.log("not")
        $(".icon-password").removeClass("icon-jujue").addClass("icon-tongguo");
        return true;
    }
}

function checkConfirmPassword() {
    if ($(".u_password").val() == "" || $(".u_password").val() != $(".confirm-password").val()) {
        $(".icon-confirm-password").removeClass("icon-tongguo").addClass("icon-jujue");
        return false;
    } else {
        $(".icon-confirm-password").removeClass("icon-jujue").addClass("icon-tongguo");
        return true;
    }
}