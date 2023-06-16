package edu.nechaev.project.services;

import edu.nechaev.project.dto.Comment;
import edu.nechaev.project.dto.CommentPost;
import edu.nechaev.project.dto.Member;
import edu.nechaev.project.dto.News;
import edu.nechaev.project.repositories.CommentPostRepository;
import edu.nechaev.project.repositories.CommentRepository;
import edu.nechaev.project.repositories.NewsRepository;
import edu.nechaev.project.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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

    public Iterable<Comment> getCommentsByNewsId(int id) {
        return commentRepository.findByNewsId(id);
    }

    public boolean deleteComment(int id) {
        CommentPost commentPost = commentPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Комментарий не существует"));
        String email = memberService.findById(commentPost.getMemberId()).getEmail();
        if (Utils.isCurrentUser(email) || Utils.hasRole("admin")) {
            commentPostRepository.deleteById(id);
            return true;
        }
        throw new AccessDeniedException("Пользователь не может удалить комментарий");
    }

    public Iterable<Comment> addComment(CommentPost commentPost) {
        commentPost.setTime(new Timestamp(System.currentTimeMillis()));
        commentPostRepository.save(commentPost);
        return getCommentsByNewsId(commentPost.getNewsId());
    }

    public boolean updateComment(int id, String text) {
        CommentPost commentPost = commentPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Комментарий не существует"));
        String email = memberService.findById(commentPost.getMemberId()).getEmail();
        if (Utils.isCurrentUser(email) || Utils.hasRole("admin")) {
            return commentPostRepository.updateCommentById(id, text, new Timestamp(System.currentTimeMillis()), true) != 0;
        }
        throw new AccessDeniedException("Пользователь не может удалить комментарий");
    }
}
