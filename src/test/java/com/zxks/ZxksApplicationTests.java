package com.zxks;

import com.zxks.service.ExamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZxksApplicationTests {

    @Resource
    private ExamService examService;

    @Test
    public void contextLoads() {

        System.out.println(examService.createPaper("201605021804", "101").getMsg());
        System.out.println(examService.getPaper("201605021804").getData().toString());


    }

    @Test
    public void revisePaperTest() {

        examService.revisePaper("201605021805");
    }

}

