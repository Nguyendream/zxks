var examParameter = new Array();// 科目参数列表
var amountQuestion;//题目数量
var codeSubject;//科目代码
var nameSubject;//科目名称
var scoreTotal;//总分
var timeExam;//时间（分）

var nMS;//倒计时时间（秒）
var nH;//时
var nM;//分
var nS;//秒
var timerRt;
function GetRTime(){
    //显示小时
    $("#hh").text(nH);
    //显示分钟
    $("#mm").text(nM);
    //显示秒钟
    $("#ss").text(nS);

    if (nMS > 0) {
        nMS = nMS-1;
        //小时
        nH = Math.floor(nMS/(60*60)) % 24;
        //分钟
        nM = Math.floor(nMS/(60)) % 60;
        //秒钟
        nS = Math.floor(nMS) % 60;
    } else {
        window.clearInterval(timerRt);
        $.func.submitPaper();
    }


}

//自定义函数
$.func = {
    getUserInfo: function () { //get user info
        $.ajax({
            type: "post",
            url: "user/get_user_info.do",
            success: function(data) {
                if (data.status==0) {
                    $("#username").html(data.data.username);
                } else {
                    alert("请登陆");
                    location.href = "/login";
                }
            }
        });
    },
    getParameterList: function () { //获取科目列表
        $.ajax({
            type: "post",
            url: "exam/get_parameter_list.do",
            success: function (data) {
                examParameter = data.data;
                for (var i = 0; i < examParameter.length; i++) {
                    $("#subjectId").append("<option value=\""
                        + examParameter[i].codeSubject + "\">"
                        + examParameter[i].nameSubject + "</option>");
                }
            }
        });
    },
    getPaper: function () {
        $.ajax({
            type: "post",
            url: "exam/get_paper.do",
            async: false,
            success: function (data) {
                if (data.status==0) {
                    $("#panel1").attr("class","panel panel-primary");
                    $("#subjectId").find("option[value=" + data.data.codeSubject + "]").attr("selected",true);

                    for (var i = 0; i < examParameter.length; i++) {
                        if (examParameter[i].codeSubject == data.data.codeSubject) {
                            amountQuestion = examParameter[i].amountQuestion;
                            codeSubject = examParameter[i].codeSubject;
                            nameSubject = examParameter[i].nameSubject;
                            scoreTotal = examParameter[i].scoreTotal;
                            timeExam = examParameter[i].timeExam;
                            nMS = timeExam*60;
                            break;
                        }
                    }

                    var paper = eval('(' + data.data.dataPaper + ')');
                    for (var i = 0; i < paper.length; i++) {
                        var question = paper[i].question;
                        //console.log(paper[i].question);
                        var arr = question.split("#");
                        //显示试卷
                        $("#examFormId").append(
                            "<div class=\"form-group\">" +
                            "<label>"+(i+1)+"."+arr[0]+"</label>" +
                            "<div class=\"radio\">" +
                            "<label class=\"radio-inline clearfix\">"+
                            "<input name=\"q"+(i+1)+"\" type=\"radio\" value=\"A\">"+arr[1]+
                            "</label>"+
                            "<label class=\"radio-inline clearfix\">"+
                            "<input name=\"q"+(i+1)+"\" type=\"radio\" value=\"B\">"+arr[2]+
                            "</label>"+
                            "<label class=\"radio-inline clearfix\">"+
                            "<input name=\"q"+(i+1)+"\" type=\"radio\" value=\"C\">"+arr[3]+
                            "</label>"+
                            "<label class=\"radio-inline clearfix\">"+
                            "<input name=\"q"+(i+1)+"\" type=\"radio\" value=\"D\">"+arr[4]+
                            "</label></div></div>"
                        );
                    }
                    //失效“开始考试”按钮
                    $("fieldset").attr("disabled","");
                    return 0;
                } else {
                    alert(data.msg);
                    return 1;
                }
            }

        });
    },
    createPaper: function () { //创建试卷
        $.ajax({
            type: "post",
            url: "exam/create_paper.do",
            data: $("#parameterId").serialize(),
            error: function(textStatus){
                alert("textStatus: " + textStatus);
                return;
            },
            success: function(data) {
                if (data.status==0) {
                    if ($.func.getPaper() == 0) {
                        alert(data.msg);
                    }
                    return 0;
                    /*for (var i = 0; i < data.data.length; i++) {
                        if (data.data[i].code_subject == $("#subjectId").val()) {
                            nMS = data.data[i].time_exam*60;
                            console.log(data.data[i].time_exam);
                            return;
                        }
                    }*/
                } else {
                    alert(data.msg);
                    return 1;
                }
            }
        });
    },
    revisePaper: function() {
        $.ajax({
            type: "post",
            url: "exam/revise_paper.do",
            success: function (data) {
                alert(data.msg);
            }
        });
    },
    submitPaper: function () {
        $("#subitId").addClass("disabled");
        var answers = new Array(amountQuestion);
        for (var i = 0; i < amountQuestion; i++) {
            answers[i] = $("input[name='q"+ (i+1) + "']:checked").val();
            if (answers[i] == null) {
                answers[i] = "";
            }
        }
        $.ajax({
            type: "post",
            url: "exam/submit_paper.do",
            traditional: true,
            data: {"answers": answers },
            error: function () {
                alert("试卷提交失败");
            },
            success: function (data) {
                $.func.revisePaper();

            }
        });
    },
    getPaperResult: function () {
        var dataPaper;
        var score;
        $.ajax({
            type: "post",
            url: "exam/get_paper.do",
            async: false,
            success: function (data) {
                dataPaper = data.data.dataPaper;
                score = data.data.scoreExam;
            }
        });
        $("#score").text(score);

    }
}

$(document).ready(function() {

    //获取用户信息与科目列表
    $.func.getUserInfo();
    $.func.getParameterList();

    //开始考试
    $("#start").click(function(){

        $.func.createPaper();

        timerRt = window.setInterval("GetRTime()", 1000);
        //$("#panel1").attr("class","panel panel-primary");
        //$("#start").attr("class","btn btn-primary disabled");
        //$("#subjectId").attr("class","form-control disabled");
        //$("fieldset").attr("disabled","");

    });

    //提交试卷
    $("#subitId").click(function () {
        $.func.submitPaper();

    });
});