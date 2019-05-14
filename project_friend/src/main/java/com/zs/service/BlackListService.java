package com.zs.service;

import com.zs.dao.BlackListDao;
import com.zs.entity.BlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlackListService {
    @Autowired
    private BlackListDao blackListDao;


    //从黑名单中删除
    public void deleteBlackListFriend(String userId,String friendId){
        blackListDao.deleteBlackList(userId,friendId);
    }
    //增加黑名单
    public int  addBlackList(String userId,String friendId){
        //不能把自己加为黑名单
        if(userId.equals(friendId)){
            return 0 ;
        }
        //已经在黑名单
        if(blackListDao.selectIsInBlackList(userId,friendId) ==1){
            return 1 ;
        };
        //成功
        BlackList black = new BlackList(userId,friendId) ;
        blackListDao.save(black ) ;
        return 2 ;
    }
}
