package com.zs.service;

import com.zs.dao.ArticleEsDao;
import com.zs.entity.ArticleEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ArticleEsService {
    @Autowired
    private ArticleEsDao articleEsDao;
    @Resource
    private RedisTemplate<String, ArticleEs> redisTemplate;
    //查询byId + redis(缓存)
    public ArticleEs findById(String id){
        return  articleEsDao.findById(id).get();
    }

   public void saveArticleEs(ArticleEs articleEs){
       articleEsDao.save(articleEs);
   }
   //更新
   public void updateArticleEs(ArticleEs articleEs){
        articleEsDao.save(articleEs);
   }

   public void deleteArticleEsById(String id){
        articleEsDao.deleteById(id);
   }

   public Page<ArticleEs> findByTitleOrDescription(String keywords, int start, int pageSize){

       PageRequest pageRequest = PageRequest.of(start , pageSize);
       return  articleEsDao.findByTitleOrDescription(keywords,keywords,pageRequest);

   }
}
