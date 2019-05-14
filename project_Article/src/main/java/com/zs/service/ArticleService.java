package com.zs.service;

import com.zs.dao.ArticleDao;
import com.zs.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Resource
    private RedisTemplate<String, Article> redisTemplate ;

    @Transactional
    public void reviewArticle(String id){
        articleDao.reviewArticle(id);
    }

    @Transactional
    public void updateLikes(String id){
        articleDao.updateLikes(id);
        //查询article
        //redisTemplate.opsForValue().set("article_"+id, article ,1, TimeUnit.HOURS );
        redisTemplate.delete("article_"+id);
    }

    @Transactional
    public Article findByArticleId(String id){
        //先从缓冲中查，如果有直接返回
        /*
              Book:1     book_1
                key:ariticle_id          aritcle_1
                value:article对象
         */
        Article article =  redisTemplate.opsForValue().get("article_"+id);
        //如果没的，从数据库中查询
        if(article == null){
            article = articleDao.findByArticleId(id);
            redisTemplate.opsForValue().set("article_"+id,article ,1, TimeUnit.HOURS );
        }
        return article;
    }




}
