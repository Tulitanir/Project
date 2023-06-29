package edu.nechaev.project.services;

import edu.nechaev.project.dto.*;
import edu.nechaev.project.repositories.CommentPostRepository;
import edu.nechaev.project.repositories.CommentRepository;
import edu.nechaev.project.repositories.NewsRepository;
import edu.nechaev.project.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import static java.awt.SystemColor.text;

@Service
@AllArgsConstructor
public class NewsService {
    private NewsRepository newsRepository;
    private CommentRepository commentRepository;
    private CommentPostRepository commentPostRepository;
    private MemberService memberService;

    public Iterable<News> getNews() {
        return newsRepository.findAll();
    }

    public Iterable<Comment> getCommentsByNewsId(long id) {
        return commentRepository.findByNewsId(id);
    }

    public boolean deleteComment(long id) {
        CommentPost commentPost = commentPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Комментарий не существует"));
        String email = memberService.findById(commentPost.getMemberId()).getEmail();
        if (Utils.isCurrentUser(email) || Utils.hasRole("admin")) {
            commentPostRepository.deleteById(id);
            return true;
        }
        throw new AccessDeniedException("Пользователь не может удалить комментарий");
    }

    public boolean deleteNews(long id) {
        newsRepository.deleteById(id);
        return true;
    }

    public News addNews(UpdateNewsRequest news) {
        News news1 = new News();
        news1.setDate(new Timestamp(System.currentTimeMillis()));
        news1.setText(news.getText());
        news1.setTitle(news.getTitle());
        news1.setChanged(false);
        return newsRepository.save(news1);
    }

    public Iterable<Comment> addComment(CommentPost commentPost) {
        commentPost.setTime(new Timestamp(System.currentTimeMillis()));
        commentPostRepository.save(commentPost);
        return getCommentsByNewsId(commentPost.getNewsId());
    }

    public boolean updateComment(long id, String text) {
        CommentPost commentPost = commentPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Комментарий не существует"));
        String email = memberService.findById(commentPost.getMemberId()).getEmail();
        if (Utils.isCurrentUser(email)) {
            return commentPostRepository.updateCommentById(id, text, new Timestamp(System.currentTimeMillis()), true) != 0;
        }
        throw new AccessDeniedException("Пользователь не может удалить комментарий");
    }

    public News updateNews(long id, String title, String text) {
        News news = new News();
        news.setId(id);
        news.setTitle(title);
        news.setText(text);
        news.setChanged(true);
        news.setDate(new Timestamp(System.currentTimeMillis()));
        return newsRepository.save(news);
    }
}
