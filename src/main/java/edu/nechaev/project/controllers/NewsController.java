package edu.nechaev.project.controllers;

import edu.nechaev.project.dto.*;
import edu.nechaev.project.services.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
public class NewsController {
    private NewsService newsService;
    @GetMapping("/getAll")
    public ResponseEntity<Iterable<News>> getNews() {
        return ResponseEntity.ok(newsService.getNews());
    }

    @PostMapping("/addNews")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<News> addNews(@RequestBody UpdateNewsRequest updateNewsRequest) {
        return ResponseEntity.ok(newsService.addNews(updateNewsRequest));
    }

    @GetMapping("/getComments")
    public ResponseEntity<Iterable<Comment>> getCommentsByNewsId(@RequestParam long id) {
        return ResponseEntity.ok(newsService.getCommentsByNewsId(id));
    }

    @DeleteMapping("/deleteNews")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Boolean> deleteNews(@RequestParam long id) {
        return ResponseEntity.ok(newsService.deleteNews(id));
    }

    @PostMapping("/addComment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Iterable<Comment>> addComment(@RequestBody CommentPost commentPost) {
        return ResponseEntity.ok().body(newsService.addComment(commentPost));
    }

    @DeleteMapping("/deleteComment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> deleteComment(@RequestParam long id) {
        return ResponseEntity.ok(newsService.deleteComment(id));
    }

    @PutMapping("/updateComment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest) {
        return ResponseEntity.ok(newsService.updateComment(updateCommentRequest.getId(), updateCommentRequest.getText()));
    }

    @PutMapping("/updateNews")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<News> updateNews(@RequestBody UpdateNewsRequest updateCommentRequest) {
        return ResponseEntity.ok(newsService.updateNews(updateCommentRequest.getId(), updateCommentRequest.getTitle(), updateCommentRequest.getText()));
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handle(AccessDeniedException runtimeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(runtimeException.getLocalizedMessage());
    }
}
