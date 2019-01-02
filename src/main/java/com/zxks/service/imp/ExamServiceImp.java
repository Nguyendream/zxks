package com.zxks.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxks.common.Const;
import com.zxks.common.ServerResponse;
import com.zxks.dao.ExamLibraryMapper;
import com.zxks.dao.ExamPaperMapper;
import com.zxks.dao.ExamParameterMapper;
import com.zxks.pojo.ExamLibrary;
import com.zxks.pojo.ExamPaper;
import com.zxks.pojo.ExamParameter;
import com.zxks.service.ExamService;
import com.zxks.util.JsonTool;
import com.zxks.vo.QuestionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("examService")
public class ExamServiceImp implements ExamService {

    @Resource
    private ExamLibraryMapper examLibraryMapper;

    @Resource
    private ExamPaperMapper examPaperMapper;

    @Resource
    private ExamParameterMapper examParameterMapper;

    @Override
    public ServerResponse<ExamParameter> getParameter(String subCode) {

        ExamParameter examParameter = examParameterMapper.selectByPrimaryKey(subCode);
        if (examParameter == null) {
            return ServerResponse.createByErrorMessage("没有该科目的考试参数");
        }
        return ServerResponse.createBySuccess("获取参数成功",examParameter);
    }

    @Override
    public ServerResponse<List<ExamParameter>> getParameterList() {

        List<ExamParameter> examParameters = examParameterMapper.selectList();
        if (examParameters == null) {
            return ServerResponse.createByErrorMessage("没有查询结果");
        }
        return ServerResponse.createBySuccess("获取科目列表成功", examParameters);
    }

    @Override
    public ServerResponse<String> changeParameter(ExamParameter examParameter) {

        ServerResponse<ExamParameter> serverResponse = this.getParameter(examParameter.getCodeSubject());
        ServerResponse<String> response;

        int resultCount;
        if (serverResponse.isSuccess()) {
            resultCount = examParameterMapper.updateByPrimaryKeySelective(examParameter);
            if (resultCount == 0) {
                response = ServerResponse.createByErrorMessage("修改参数失败");
            } else {
                response = ServerResponse.createBySuccessMessage("修改参数成功");
            }
        } else {
            resultCount = examParameterMapper.insert(examParameter);
            if (resultCount == 0) {
                response = ServerResponse.createByErrorMessage("新增参数失败");
            } else {
                response = ServerResponse.createBySuccessMessage("新增参数成功");
            }
        }

        return response;
    }

    @Override
    public ServerResponse<String> createPaper(String idCard, String subCode) {

        int resultCount = examPaperMapper.checkPaper(idCard);
        if (resultCount != 0) {
            return ServerResponse.createBySuccessMessage("已有试卷");
        }

        //todo 只取题目id，而不是所有该科目的题目
        List<ExamLibrary> examLibraryList = examLibraryMapper.selectBySubCode(subCode);
        List<QuestionVo> dataPaper = new ArrayList<>();
        ExamParameter examParameter = examParameterMapper.selectByPrimaryKey(subCode);

        if (examParameter == null) {
            return ServerResponse.createByErrorCodeMessage(Const.ExamErrorCode.NULL,"不存在该科目");
        }

        //从题库随机选题，根据科目参数
        Random rand = new Random();
        int rnd;
        if (examLibraryList.size() < examParameter.getAmountQuestion()) {
            return ServerResponse.createByErrorCodeMessage(Const.ExamErrorCode.PARAMETER_ERROR, "参数错误，题库题目不足");
        }
        for (int i = 0; i < examParameter.getAmountQuestion(); i++) {
            rnd = rand.nextInt(examLibraryList.size());
            QuestionVo questionVo = new QuestionVo();
            questionVo.setIdQuestion(examLibraryList.get(rnd).getIdQuestion());
            questionVo.setCodeSubject(examLibraryList.get(rnd).getCodeSubject());
            questionVo.setQuestion(examLibraryList.get(rnd).getQuestion());
            questionVo.setTrueAnswer(examLibraryList.get(rnd).getAnswer());
            dataPaper.add(questionVo);
            examLibraryList.remove(rnd);
        }

        //对象转json
        String data = JsonTool.toJson(dataPaper);

        ExamPaper examPaper = new ExamPaper();
        examPaper.setIdCard(idCard);
        examPaper.setCodeSubject(subCode);
        examPaper.setDataPaper(data);

        resultCount = examPaperMapper.insert(examPaper);
        if (resultCount == 0) {
            return ServerResponse.createByErrorCodeMessage(Const.ExamErrorCode.CREATE_ERROR,"试卷创建失败");
        }
        return ServerResponse.createBySuccessMessage("试卷创建成功");
    }

    @Override
    public ServerResponse<ExamPaper> getPaper(String idCard) {

        return ServerResponse.createBySuccess("获取试卷成功", examPaperMapper.selectByIdCard(idCard));
    }

    @Override
    public ServerResponse<String> submitPaper(ExamPaper examPaper, String[] answers) {


        String json = examPaper.getDataPaper();
        String dataPaper;
        //json转对象
        List<QuestionVo> questionVoList = (List<QuestionVo>) JsonTool.fromJson(json, List.class, QuestionVo.class);
        for (int i = 0; i < answers.length; i++) {
            questionVoList.get(i).setAnswer(answers[i]);
        }

        dataPaper = JsonTool.toJson(questionVoList);
        examPaper.setDataPaper(dataPaper);
        int resultCount = examPaperMapper.updateByIdCardSelective(examPaper);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("试卷提交失败");
        }
        return ServerResponse.createBySuccessMessage("试卷提交成功");
    }

    @Override
    public ServerResponse<ExamPaper> revisePaper(String idCard) {

        ExamPaper examPaper = examPaperMapper.selectByIdCard(idCard);
        ExamParameter examParameter = examParameterMapper.selectByPrimaryKey(examPaper.getCodeSubject());
        String json = examPaper.getDataPaper();
        int scoreTotal = examParameter.getScoreTotal();
        int amount = examParameter.getAmountQuestion();
        int score = 0;
        int scoreOne = scoreTotal/amount;
        String answer;
        String trueAnswer;

        //Json转对象
        List<QuestionVo> questionVoList = (List<QuestionVo>) JsonTool.fromJson(json, List.class, QuestionVo.class);

        if (questionVoList == null) {
            return ServerResponse.createByErrorMessage("试卷批改失败");
        }

        for (int i = 0; i < amount; i++) {
            answer = questionVoList.get(i).getAnswer();
            trueAnswer = questionVoList.get(i).getTrueAnswer();
            if (answer != null && answer.equals(trueAnswer)) {
                score += scoreOne;
            }
        }

        examPaper.setScoreExam(score);
        int resultCount = examPaperMapper.updateByIdCardSelective(examPaper);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("试卷批改失败");
        }

        return ServerResponse.createBySuccess("试卷批改成功，分数：" + score, examPaper);
    }
}
