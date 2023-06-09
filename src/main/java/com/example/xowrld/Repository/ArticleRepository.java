package com.example.xowrld.Repository;

import com.example.xowrld.Model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    void delete(Optional<Article> byId);
}
