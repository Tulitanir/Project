package edu.nechaev.project.controllers;

import edu.nechaev.project.models.Comment;
import edu.nechaev.project.models.News;
import edu.nechaev.project.services.NewsService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
public class NewsController {
    private NewsService newsService;
    @GetMapping("/getAll")
    public ResponseEntity<Iterable<News>> getNews() {
        return ResponseEntity.ok(newsService.getNews());
    }

    @GetMapping("/getComments")
    public ResponseEntity<Iterable<Comment>> getCommentsByNewsId(@RequestParam int id) {
        return ResponseEntity.ok(newsService.getCommentsByNewsId(id));
    }
}
