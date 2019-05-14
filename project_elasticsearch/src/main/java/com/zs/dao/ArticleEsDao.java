package com.zs.dao;

import com.zs.entity.ArticleEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleEsDao extends ElasticsearchRepository<ArticleEs,String> {

    public Page<ArticleEs> findByTitleOrDescription(String title, String description, Pageable pageable);
}
