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

    idCard.change(function () {
        if (!$.check.idCard()) {
            alert("准考证号为12位的数字");
        }
    });
    username.change(function () {
        if (!$.check.username()) {
            alert("用户名为4-20位任意字符");
        }
    });
    password.change(function () {
        if (!$.check.password()) {
            alert("密码为6-20位由字母、数字或者下划线组成的字符");
        }
    });
    password1.change(function () {
        if (password.val() != password1.val()) {
            alert("确认密码错误");
        }
    })
    age.change(function () {
        if (!$.check.age()) {
            alert("别闹");
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