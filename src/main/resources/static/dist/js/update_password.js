$(document).ready(function(){
    var idCard = $("#idCardId");
    var oldPassword = $("#oldPasswordId");
    var newPassword = $("#newPasswordId");
    var password1 = $("#passwordId1");

    var idCardPattern = /^\d{12}$/;
    var passwordPattern = /^\w{6,20}$/;

    $.check = {
        idCard: function () {
            return idCardPattern.exec(idCard.val());
        },
        oldPassword: function () {
            return passwordPattern.exec(oldPassword.val());
        },
        newPassword: function () {
            return passwordPattern.exec(newPassword.val());
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
    oldPassword.blur(function () {
        if (!$.check.oldPassword()) {
            oldPassword.attr("title", "密码为6-20位由字母、数字或者下划线组成的字符");
            oldPassword.tooltip("show");
            oldPassword.parent().attr("class", "form-group has-error");
        } else {
            oldPassword.tooltip("destroy");
            oldPassword.parent().attr("class", "form-group");
        }
    });
    newPassword.blur(function () {
        if (!$.check.newPassword()) {
            newPassword.attr("title", "密码为6-20位由字母、数字或者下划线组成的字符");
            newPassword.tooltip("show");
            newPassword.parent().attr("class", "form-group has-error");
        } else {
            newPassword.tooltip("destroy");
            newPassword.parent().attr("class", "form-group");
        }
    });
    password1.blur(function () {
        if (password1.val() != newPassword.val()) {
            password1.attr("title", "确认密码错误");
            password1.tooltip("show");
            password1.parent().attr("class", "form-group has-error");
        } else {
            password1.tooltip("destroy");
            password1.parent().attr("class", "form-group");
        }
    })


	$("#updateId").click(function(){
	    if (!$.check.oldPassword() || !$.check.newPassword() || !$.check.idCard()
            || (password1.val() != newPassword.val())) {
            alert("输入信息有误");
        } else {
	        $.ajax({
                type: "post",
                url: "user/update_password.do",
                timeout: "5000",
                data: $("#loginForm").serialize(),
                async: true,
                error: function (textStatus) {
                    alert("textStatus: " + textStatus);
                },
                success: function (data) {
                    if (data.status == 0) {
                        alert(data.msg);
                        location.href = "/";
                    } else {
                        alert(data.msg);
                        $("input").val("");
                    }
                }
            });
        }
	});

    $("#cancelId").click(function () {
        location.href = "/";
    })
});