package com.zs.controller;

import com.zs.entity.Comment;
import com.zs.entity.PageResult;
import com.zs.entity.Result;
import com.zs.entity.StatusCode;
import com.zs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //缓存
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",commentService.findAll());
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",commentService.findById(id)) ;
    }


    @GetMapping("{parentId}/{start}/{pageSize}")
    public Result findOne(@PathVariable String parentId, @PathVariable int start, @PathVariable int pageSize){
        Page<Comment> pageInfo = commentService.findByParentId(parentId, start, pageSize);
        PageResult<Comment> pageResult = new PageResult<>(pageInfo.getTotalElements(), pageInfo.getContent());

        return new Result(true,StatusCode.OK,"查询成功",pageResult) ;
    }
    @PostMapping
    public Result addComment(@RequestBody Comment comment){
        commentService.addComment(comment);
        return new Result(true,StatusCode.OK,"增加成功") ;
    }

    @PutMapping("{id}")
    public Result updateComment(@RequestBody Comment comment , @PathVariable String id){
        comment.set_id(id);
        commentService.updateComment(comment);
        return new Result(true,StatusCode.OK,"修改成功") ;
    }
    /*
        查缓存：    如果是1，说明已点赞：直接返回失败
                  如果不是1，说明没点赞： 更新数据 likes+1，存入缓存
     */


//    @PutMapping("likes/{id}")
//    public Result updateLikes(@PathVariable String id){
//        String userId="9527";//假定用户userId是9527
//
//        //key:likes_用户id_文章id
//        //value: 1 （1表示已经点过赞）
//        if(redisTemplate.opsForValue().get("likes_"+userId+"_"+id) !=null){
//            return new Result(false, StatusCode.ERROR,"不能重复点赞");
//        }
//        commentService.updateLikes(id);
//
//
//        redisTemplate.opsForValue().set("likes_"+userId+"_"+id,"1");
//
//        return new Result(true,StatusCode.OK,"点赞成功") ;
//    }

    @DeleteMapping("{id}")
    public Result deleteCommentById(@PathVariable String id){
        commentService.deleteCommentById(id);
        return new Result(true,StatusCode.OK,"删除成功") ;
    }
}
