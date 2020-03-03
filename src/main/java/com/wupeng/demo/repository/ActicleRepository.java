package com.wupeng.demo.repository;

import com.wupeng.demo.mongoDBEntity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActicleRepository  extends MongoRepository<Article,String> {

    void deleteArticleById(String id);
}
