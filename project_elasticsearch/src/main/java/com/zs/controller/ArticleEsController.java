package com.zs.controller;

import com.zs.entity.ArticleEs;
import com.zs.entity.PageResult;
import com.zs.entity.Result;
import com.zs.entity.StatusCode;
import com.zs.service.ArticleEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("articleEs")
public class ArticleEsController {
    @Autowired
    private ArticleEsService articleEsService;



    @GetMapping("{id}")
    public Result findById(@PathVariable String id) {
        articleEsService.findById(id);
        return new Result(true, StatusCode.OK, "Es缓存查询成功");
    }

    @PostMapping
    public Result saveArticleEs(@RequestBody ArticleEs articleEs) {
        articleEsService.saveArticleEs(articleEs);
        return new Result(true, StatusCode.OK, "Es增加成功");
    }

    @PutMapping("{id}")
    public Result updateArticleEs(@RequestBody ArticleEs articleEs, @PathVariable String id) {
        articleEs.setId(id);
        articleEsService.updateArticleEs(articleEs);
        return new Result(true, StatusCode.OK, "Es更新成功");
    }

    @DeleteMapping("{id}")
    public Result deleteArticleEsById(@PathVariable String id){
        articleEsService.deleteArticleEsById(id);
        return new Result(true,StatusCode.OK,"Es删除成功");
    }

    //根据全title或description查询
    @GetMapping("findByTitleOrDescription/{keywords}/{start}/{pageSize}")
    public Result findByTitleOrDescription(@PathVariable String keywords, @PathVariable int start, @PathVariable int pageSize){
        Page<ArticleEs> articleEsPage = articleEsService.findByTitleOrDescription(keywords,start-1,pageSize);
        return new Result(true,StatusCode.OK,"根据title或者description查到相关内容",new PageResult<ArticleEs>(articleEsPage.getTotalElements(),articleEsPage.getContent()));
    }
}
