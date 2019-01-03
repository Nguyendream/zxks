$(document).ready(function(){
    var idCard = $("#idCardId");
    var username = $("#usernameId");
    var sex = $("#sexId");
    var age = $("#ageId");

    var idCardPattern = /^\d{12}$/;
    var usernamePattern = /^.{2,20}$/;
    var agePattern = /^\d{0,2}$/;

    var dataPost = new Array();

    $.check = {
        idCard: function () {
            return idCardPattern.exec(idCard.val());
        },
        username: function () {
            return usernamePattern.exec(username.val());
        },
        age: function () {
            return agePattern.exec(age.val());
        }
    }

    username.blur(function () {
        if (!$.check.username()) {
            username.attr("title", "用户名为2-20位任意字符");
            username.tooltip("show");
            username.parent().attr("class", "form-group has-error");
        } else {
            username.tooltip("destroy");
            username.parent().attr("class", "form-group");
        }
    });

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

    //获取用户信息
    $.ajax({
        type: "post",
        url: "user/get_user_info.do",
        timeout: "5000",
        success: function (data) {
            if (data.status == 0) {
               dataPost.idCard = data.data.idCard;
               dataPost.username = data.data.username;
               dataPost.sex = data.data.sex;
               dataPost.age = data.data.age;

               idCard.val(dataPost.idCard);
               username.val(dataPost.username);
               sex.val(dataPost.sex);
               age.val(dataPost.age);
            } else {
                alert(data.msg);
                return false;
            }
        }
    });

	$("#updateId").click(function(){
	    if (!$.check.username() || !$.check.idCard() || !$.check.age()) {
            alert("注册信息有误");
        } else {
	        $.ajax({
                type: "post",
                url: "user/update_user_info.do",
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
                    }
                }
            });
        }
	});

	$("#cancelId").click(function () {
        location.href = "/";
    });
});