package com.example.xowrld.Repository;

import com.example.xowrld.Model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

   public List<Article> findAllByOrderByIdDesc();
}
