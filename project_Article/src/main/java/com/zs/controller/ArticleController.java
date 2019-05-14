package com.zs.controller;

import com.zs.entity.Result;
import com.zs.entity.StatusCode;
import com.zs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService ;

    //get  put  delete  post
    @PutMapping("reviewArticle/{id}")
    public Result reviewArticle(@PathVariable String id){
        articleService.reviewArticle(id);
        return new Result(true, StatusCode.OK,"审核成功！");
    }

    @PutMapping("updateLikes/{id}")
    public Result updateLikes(@PathVariable String id){
        articleService.updateLikes(id);
        return new Result(true, StatusCode.OK,"点赞成功！");
    }

    //查询  redis


    @GetMapping("findByArticleId/{id}")
    public Result findByArticleId(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功！",articleService.findByArticleId(id));
    }
}
