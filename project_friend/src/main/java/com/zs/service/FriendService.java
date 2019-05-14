package com.zs.service;

import com.zs.client.UserClient;
import com.zs.dao.BlackListDao;
import com.zs.dao.FriendDao;
import com.zs.entity.BlackList;
import com.zs.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private BlackListDao blackListDao;
    @Autowired
    private UserClient userClient;


    //新增:返回值 0：不能自己加自己   1:不能重复添加   2：成功
    public int addFriend(String userId, String friendId) {
        if (userId == friendId) {
            return 0;
        } else if (friendDao.selectIsFriend(userId, friendId) == 1) {
            return 1;
        } else {
            Friend friend = new Friend(userId, friendId, "0");//0代表不是星标
            friendDao.save(friend);
            return 2;
        }
    }

    //查看星标状态  0:不是星标    1：是星标
    public int selectIsStar(String userId, String friendId) {
        return friendDao.selectIsStar(userId, friendId);
    }

    //设置星标好友 0:不是星标    1：是星标
    public void markStart(String userId, String friendId, String isStar) {
      friendDao.updateIsStar(userId, friendId, isStar);
    }
    //判断是否加有星标 true 代表加有星标     false代表没加星标
    public boolean isMarkedByFriendId(String userId,String friendId){
        return  friendDao.isMarkedByFriendId(userId,friendId)>0 ?true :false   ;
    }

    //加入黑名单
    //type:0 单纯的删除     1删除+加黑 (如果在好友列表中删除某一个人，则此人删除前必然 不在黑名单中)
    public void deleteFriend(String userId,String friendId ,String typeId){
        //删除
        friendDao.deleteFriend(userId, friendId);

        //处理远程user项目中的粉丝问题
        userClient.updateFans(userId,-1) ;

        //并加黑
        if("1".equals(typeId)) {
            BlackList blackList = new BlackList(userId,friendId);
            blackListDao.save (blackList);//加黑
        }
    }
}

