package com.example.xowrld.Controller;

import com.example.xowrld.Model.Article;
import com.example.xowrld.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/newarticle")
    public String newarticle(Model model){
        model.addAttribute("article", new Article());

        return "article/newarticle";
    }

    @PostMapping("/newarticle")
    public String savenewArticle(Article article, @RequestParam("arphoto")MultipartFile multipartFile) throws IOException {
        article.setPhotoName(multipartFile.getName());
        article.setPhotoType(multipartFile.getContentType());

        article.setPhotoData(multipartFile.getBytes());
        articleRepository.save(article);

        return "redirect:/home";
    }

    @GetMapping("/arphoto/{id}")
    public @ResponseBody byte[] getphoto(@PathVariable("id") Long id){
         Article article = articleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

         return article.getPhotoData();
    }

    @GetMapping("/read/article/{id}")
    public String viewarticle(Model model, @PathVariable("id") Long id){
        Optional<Article> article = articleRepository.findById(id);

        if(article.isPresent()){
            model.addAttribute("article", article.get());
            return "article/spectatearticle";
        }

        return "redirect:/home";
    }

}
