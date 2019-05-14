package com.zs.dao;

import com.zs.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String>, JpaSpecificationExecutor<Friend> {

    //是否是 “好友关系”  ：返回1：是     返回0：不是好友
    @Query(nativeQuery = true, value = "select count(*) from tb_friend where user_id=? and friend_id = ?")
    public int selectIsFriend(String userId, String friendId);

    //查看星标状态  0:不是星标    1：是星标
    @Query(nativeQuery = true, value = "select is_star from tb_friend where user_id = ? and friend_id =?")
    public int selectIsStar(String userId, String friendId);


    //设置星标好友 0:不是星标    1：是星标
    //设置星标
    @Modifying
    @Query("update Friend f set f.isStar=?3 where f.userId=?1 and f.friendId=?2")
    public void updateIsStar(String userId, String friendId, String isStar) ;

    @Query(nativeQuery = true,value="select count(*) from tb_friend f where f.user_id=? and f.friend_id=? and is_star=1")
    public int isMarkedByFriendId(String userId, String friendId);
    //====================================================
    //加入黑名单
    //1、从tb_friend表中删除
    @Modifying
    @Query("delete from Friend f where f.userId=?1 and f.friendId=?2")
    public void deleteFriend(String userId, String friendId);


}