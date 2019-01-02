package com.zxks.controller;

import com.zxks.common.Const;
import com.zxks.common.ServerResponse;
import com.zxks.pojo.ExamPaper;
import com.zxks.pojo.ExamParameter;
import com.zxks.pojo.User;
import com.zxks.service.ExamService;
import com.zxks.vo.PaperResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/exam/")
public class ExamController {

    @Resource
    private ExamService examService;

    @RequestMapping(value = "get_parameter_list.do", method = RequestMethod.POST)
    public ServerResponse<List<ExamParameter>> getParameterList() {

        return examService.getParameterList();
    }

    @RequestMapping(value = "create_paper.do", method = RequestMethod.POST)
    public ServerResponse<String> createPaper(String codeSubject, HttpSession session) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }

        ServerResponse<String> response = examService.createPaper(user.getIdCard(), codeSubject);

        if (!response.isSuccess()) {
            return response;
        }

        ServerResponse<ExamPaper> examPaperServerResponse = examService.getPaper(user.getIdCard());
        session.setAttribute(Const.CURRENT_EXAM, examPaperServerResponse.getData());

        return response;
    }

    @RequestMapping(value = "get_paper.do", method = RequestMethod.POST)
    public ServerResponse<ExamPaper> getPaper(HttpSession session) {

        ExamPaper examPaper = (ExamPaper) session.getAttribute(Const.CURRENT_EXAM);
        if (examPaper == null) {
            return ServerResponse.createByErrorMessage("没有试卷");
        } else if (examPaper.getScoreExam() != null) {
            //如已有分数，则表示已完成考试
            return ServerResponse.createBySuccess("已完成考试\n分数：" + examPaper.getScoreExam(), examPaper);
        }

        return ServerResponse.createBySuccess("获取试卷成功", examPaper);

    }

    @RequestMapping(value = "submit_paper.do", method = RequestMethod.POST)
    public ServerResponse<String> submitPaper(String[] answers, HttpSession session) {

        ExamPaper examPaper = (ExamPaper) session.getAttribute(Const.CURRENT_EXAM);
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (examPaper == null) {
            return ServerResponse.createByErrorMessage("服务器没有试卷");
        }

        return examService.submitPaper(examPaper, answers);
    }

    @RequestMapping(value = "revise_paper.do", method = RequestMethod.POST)
    public ServerResponse<ExamPaper> revisePaper(HttpSession session) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }

        ServerResponse<ExamPaper> response = examService.revisePaper(user.getIdCard());
        session.setAttribute(Const.CURRENT_EXAM, response.getData());
        return response;
    }

    @RequestMapping(value = "get_paper_result.do", method = RequestMethod.POST)
    public ServerResponse<PaperResultVo> getPaperResult(HttpSession session) {

        ExamPaper examPaper = (ExamPaper) session.getAttribute(Const.CURRENT_EXAM);
        if (examPaper == null) {
            return ServerResponse.createByErrorMessage("没有阅卷结果");
        }
        return examService.getPaperResult(examPaper);
    }

}
