package com.zxks.service;

import com.zxks.common.ServerResponse;
import com.zxks.pojo.ExamPaper;
import com.zxks.pojo.ExamParameter;
import com.zxks.vo.PaperResultVo;
import com.zxks.vo.QuestionVo;

import java.util.List;

public interface ExamService {

    public ServerResponse<ExamParameter> getParameter(String subCode);

    public ServerResponse<List<ExamParameter>> getParameterList();

    public ServerResponse<String> changeParameter(ExamParameter examParameter);

    public ServerResponse<String> createPaper(String idCard, String subCode);

    public ServerResponse<ExamPaper> getPaper(String idCard);

    public ServerResponse<String> submitPaper(ExamPaper examPaper, String[] answers);

    public ServerResponse<ExamPaper> revisePaper(String idCard);

    public ServerResponse<PaperResultVo> getPaperResult(ExamPaper examPaper);
}
