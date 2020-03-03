package com.wupeng.demo.service.serviceImpl;

import com.wupeng.demo.mongoDBEntity.Article;
import com.wupeng.demo.repository.ActicleRepository;
import com.wupeng.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ActicleRepository acticleRepository;

    @Override
    public List<Article> findPaeg(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        return acticleRepository.findAll();
    }

    @Override
    public void saveArtcleInfo(Article article) {
        acticleRepository.save(article);
    }

    @Override
    public void saveArtcleList(List<Article> list) {
        acticleRepository.insert(list);
    }

    @Override
    public void deleteArticleById(String id) {
        acticleRepository.deleteById(id);
    }
}
