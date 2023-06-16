package edu.nechaev.project.controllers;

import edu.nechaev.project.dto.Comment;
import edu.nechaev.project.dto.CommentPost;
import edu.nechaev.project.dto.News;
import edu.nechaev.project.dto.UpdateCommentRequest;
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

    @GetMapping("/getComments")
    public ResponseEntity<Iterable<Comment>> getCommentsByNewsId(@RequestParam int id) {
        return ResponseEntity.ok(newsService.getCommentsByNewsId(id));
    }

    @PostMapping("/addComment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Iterable<Comment>> addComment(@RequestBody CommentPost commentPost) {
        return ResponseEntity.ok().body(newsService.addComment(commentPost));
    }

    @DeleteMapping("/deleteComment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> deleteComment(@RequestParam int id) {
        return ResponseEntity.ok(newsService.deleteComment(id));
    }

    @PatchMapping("/updateComment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest) {
        return ResponseEntity.ok(newsService.updateComment(updateCommentRequest.getId(), updateCommentRequest.getText()));
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handle(AccessDeniedException runtimeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(runtimeException.getLocalizedMessage());
    }
}
