package com.zs.dao;

import com.zs.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentDao extends JpaRepository<Comment,String>, JpaSpecificationExecutor<Comment> {
    public Page<Comment> findByParentId(String parentId, Pageable pageable);
    //自定义一个功能
    //修改里面的
}
