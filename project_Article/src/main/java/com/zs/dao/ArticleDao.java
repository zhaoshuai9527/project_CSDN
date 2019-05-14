package com.zs.dao;


import com.zs.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleDao extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {

    //审核文章 :修改|删除：   @Modifying+@Query
    @Modifying
    @Query("update Article set state = '1' where id =?1 ")
    public void reviewArticle(String id);


    @Modifying
    @Query("update Article set likes = likes+1  where id =?1 ")
    public void updateLikes(String id);


    @Query(nativeQuery = true, value = "select * from tb_article where id = ?")
    public Article findByArticleId(String id);

}