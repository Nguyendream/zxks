$(document).ready(function(){
    var idCard = $("#idCardId");
    var username = $("#usernameId");
    var password = $("#passwordId");
    var password1 = $("#passwordId1");
    var sex = $("#sexId");
    var age = $("#ageId");

    var idCardPattern = /^\d{12}$/;
    var usernamePattern = /^.{4,20}$/;
    var passwordPattern = /^\w{6,20}$/;
    var agePattern = /^\d{0,2}$/;

    $.check = {
        idCard: function () {
            return idCardPattern.exec(idCard.val());
        },
        username: function () {
            return usernamePattern.exec(username.val());
        },
        password: function () {
            return passwordPattern.exec(password.val());
        },
        age: function () {
            return agePattern.exec(age.val());
        }
    }

    idCard.blur(function () {
        if (!$.check.idCard()) {
            idCard.attr("title", "准考证号为12位的数字");
            idCard.tooltip("show");
            idCard.parent().attr("class", "form-group has-error");
        } else {
            idCard.tooltip("destroy");
            idCard.parent().attr("class", "form-group");
        }
    });
    username.blur(function () {
        if (!$.check.username()) {
            username.attr("title", "用户名为4-20位任意字符");
            username.tooltip("show");
            username.parent().attr("class", "form-group has-error");
        } else {
            username.tooltip("destroy");
            username.parent().attr("class", "form-group");
        }
    });
    password.blur(function () {
        if (!$.check.password()) {
            password.attr("title", "密码为6-20位由字母、数字或者下划线组成的字符");
            password.tooltip("show");
            password.parent().attr("class", "form-group has-error");
        } else {
            password.tooltip("destroy");
            password.parent().attr("class", "form-group");
        }
    });
    password1.blur(function () {
        if (password1.val() != password.val()) {
            password1.attr("title", "确认密码错误");
            password1.tooltip("show");
            password1.parent().attr("class", "form-group has-error");
        } else {
            password1.tooltip("destroy");
            password1.parent().attr("class", "form-group");
        }
    })
    age.blur(function () {
        if (!$.check.age()) {
            age.attr("title", "别闹");
            age.tooltip("show");
            age.parent().attr("class", "form-group has-error");
        } else {
            age.tooltip("destroy");
            age.parent().attr("class", "form-group");
        }
    })


	$("#register").click(function(){
	    if (!$.check.username() || !$.check.password() || !$.check.idCard() || !$.check.age()) {
            alert("注册信息有误");
        } else {
	        $.ajax({
                type: "post",
                url: "user/register.do",
                timeout: "5000",
                data: $("#loginForm").serialize(),
                async: true,
                error: function (textStatus) {
                    alert("textStatus: " + textStatus);
                },
                success: function (data) {
                    if (data.status == 0) {
                        alert(data.msg);
                        location.href = "/login";
                    } else {
                        alert(data.msg);
                        $("#passwordId").val("");
                        $("#passwordId1").val("");
                    }
                }
            });
        }
	});
});