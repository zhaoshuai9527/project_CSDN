package com.zs.controller;

import com.zs.entity.Question;
import com.zs.entity.Result;
import com.zs.entity.StatusCode;
import com.zs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService ;

    @GetMapping(value="quesionlist/{labeid}/{start}/{pagesize}")
    public Result findNewQuestionsByLabelId(@PathVariable String labeid, @PathVariable int start, @PathVariable int pagesize){
        Page<Question> quesionsPage = questionService.findNewQuestionsByLabelId(labeid, start, pagesize);
        return new Result(true, StatusCode.OK,"查询成功",quesionsPage  );
    }
    @GetMapping(value="hotquesionlist/{labeid}/{start}/{pagesize}")
    public Result findHotQuestionsByLabelId(@PathVariable String labeid, @PathVariable int start, @PathVariable int pagesize){
        Page<Question> quesionsPage = questionService.findHotQuestionsByLabelId(labeid, start, pagesize);
        return new Result(true, StatusCode.OK,"查询成功",quesionsPage  );
    }

    @GetMapping(value="waitquesionlist/{labeid}/{start}/{pagesize}")
    public Result findWaitQuestionsByLabelId(@PathVariable String labeid, @PathVariable int start, @PathVariable int pagesize){
        Page<Question> quesionsPage = questionService.findWaitQuestionsByLabelId(labeid, start, pagesize);
        return new Result(true, StatusCode.OK,"查询成功",quesionsPage  );
    }
}
