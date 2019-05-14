package com.zs.service;

import com.zs.dao.CommentDao;
import com.zs.entity.Comment;
import org.hibernate.Criteria;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    //redisTemplate ;




    public List<Comment> findAll(){
        return commentDao.findAll() ;
    }

    public Comment findById(String id){
        return commentDao.findById(id).get() ;
    }

    public void addComment(Comment comment){//aaa 100
        commentDao.save(comment) ;
    }

    public void updateComment(Comment comment){
        commentDao.save(comment) ;
    }

    public void deleteCommentById(String id){
        commentDao.deleteById(id);
    }

    public Page<Comment> findByParentId(String parentId , int start , int pageSize){
        PageRequest pageRequest = PageRequest.of(start - 1, pageSize);
        return commentDao.findByParentId(parentId   , pageRequest ) ;
    }




}

