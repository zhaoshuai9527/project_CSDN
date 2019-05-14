package com.zs.controller;

import com.zs.entity.Result;
import com.zs.entity.StatusCode;
import com.zs.service.BlackListService;
import com.zs.service.FriendService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BlackListService blackListService;


    //添加好友
    @PostMapping("addFriend/{friendId}")
    public Result addFriend(@PathVariable String friendId) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "用户未登录，请先登录");
        }
        //claims.getId()-->userId
        int addFriend = friendService.addFriend(claims.getId(), friendId);
        if (addFriend == 0) {
            return new Result(false, StatusCode.ERROR, "不能添加自己");
        } else if (addFriend == 1) {
            return new Result(false, StatusCode.MESSAGEREPEAT, "不能重复添加");
        } else {
            return new Result(true, StatusCode.OK, "添加好友成功");
        }
    }

    //查看星标状态  0:不是星标    1：是星标
    @GetMapping("selectIsStar/{friendId}")
    public Result selectIsStar(@PathVariable String friendId) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "用户未登录，请先登录");
        }
        int selectIsStar = friendService.selectIsStar(claims.getId(), friendId);
        if (selectIsStar == 0) {
            return new Result(true, StatusCode.OK, "当前不是星标好友");
        } else
            return new Result(true, StatusCode.OK, "当前是星标好友");
    }

    //设置星标

    @PutMapping(value="markStar/{friendId}")
    public Result markStar(@PathVariable String friendId ){

        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足！");
        }

        //是否已经加了星标
        if(friendService.isMarkedByFriendId(claims.getId(),friendId)){
            //如果是，取消星标
            friendService.markStart(  claims.getId(),friendId,"0");
            return new Result(true, StatusCode.OK,"取消星标成功！");
        }
        friendService.markStart(  claims.getId(),friendId,"1");
        return new Result(true, StatusCode.OK, "设为星标成功！");
    }


    //加入黑名单
    //0 不能将自己拉入黑名单   1  已经在黑名单中      2 拉黑成功
    @PostMapping(value="addBlackList/{friendId}")
    public Result addBlackList(@PathVariable String friendId ){
        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足！");
        }
        int result =  blackListService.addBlackList(claims.getId(),friendId);
        if(result ==0 ) {
            return new Result(false, StatusCode.ERROR, "不能增加自己");
        }
        if( result == 1) {
            return new Result(false, StatusCode.ERROR, "已经在黑名单中，不能重复添加！");
        }
        return new Result(true, StatusCode.OK, "加入黑名单成功");
    }


    //删除好友
    //typeId 0普通删除   1删除并拉黑
    @DeleteMapping(value="deleteFriend/{friendId}/{typeId}")
    public Result deleteFriend(@PathVariable String friendId, @PathVariable String typeId){
        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"无权访问");
        }
        friendService.deleteFriend(claims.getId(), friendId,typeId);
        return new Result(true, StatusCode.OK, "删除成功");
    }



    //移除黑名单
    @DeleteMapping(value="deleteFriendFromBlackList/{friendId}")
    public Result deleteFriendFromBlackList(@PathVariable String friendId){
        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"无权访问");
        }
        blackListService.deleteBlackListFriend(claims.getId(),friendId);
        return new Result(true, StatusCode.OK, "移除黑名单成功！");
    }

}
