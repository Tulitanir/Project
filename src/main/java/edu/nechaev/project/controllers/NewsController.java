package edu.nechaev.project.controllers;

import edu.nechaev.project.dto.*;
import edu.nechaev.project.services.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    private NewsService newsServiceImpl;
    @Operation(summary = "Получить все новости")
    @GetMapping("/getAll")
    public ResponseEntity<Iterable<News>> getNews() {
        return ResponseEntity.ok(newsServiceImpl.getNews());
    }

    @Operation(summary = "Добавить новость")
    @PostMapping("/addNews")
    @ApiResponse(responseCode = "200", description = "Новость добавлена")
    @ApiResponse(responseCode = "400", description = "Произошла ошибка при попытке добавления")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<News> addNews(@RequestBody
                                            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Новость для добавления")
                                            UpdateNewsRequest updateNewsRequest) {
        return ResponseEntity.ok(newsServiceImpl.addNews(updateNewsRequest));
    }

    @GetMapping("/getComments")
    public ResponseEntity<Iterable<Comment>> getCommentsByNewsId(@RequestParam long id) {
        return ResponseEntity.ok(newsServiceImpl.getCommentsByNewsId(id));
    }

    @DeleteMapping("/deleteNews")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Boolean> deleteNews(@RequestParam long id) {
        return ResponseEntity.ok(newsServiceImpl.deleteNews(id));
    }

    @PostMapping("/addComment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Iterable<Comment>> addComment(@RequestBody CommentPost commentPost) {
        return ResponseEntity.ok().body(newsServiceImpl.addComment(commentPost));
    }

    @DeleteMapping("/deleteComment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> deleteComment(@RequestParam long id) {
        return ResponseEntity.ok(newsServiceImpl.deleteComment(id));
    }

    @PutMapping("/updateComment")
    @Operation(summary = "Обновить комментарий")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest) {
        return ResponseEntity.ok(newsServiceImpl.updateComment(updateCommentRequest.getId(), updateCommentRequest.getText()));
    }

    @PutMapping("/updateNews")
    @Operation(summary = "Обновить новость")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<News> updateNews(@RequestBody UpdateNewsRequest updateCommentRequest) {
        return ResponseEntity.ok(newsServiceImpl.updateNews(updateCommentRequest.getId(), updateCommentRequest.getTitle(), updateCommentRequest.getText()));
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handle(AccessDeniedException runtimeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(runtimeException.getLocalizedMessage());
    }
}
