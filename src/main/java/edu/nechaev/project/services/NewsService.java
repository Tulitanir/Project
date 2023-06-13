package edu.nechaev.project.services;

import edu.nechaev.project.models.Comment;
import edu.nechaev.project.models.News;
import edu.nechaev.project.repositories.CommentRepository;
import edu.nechaev.project.repositories.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsService {
    private NewsRepository newsRepository;
    private CommentRepository commentRepository;

    public Iterable<News> getNews() {
        return newsRepository.findAll();
    }

    public Iterable<Comment> getCommentsByNewsId(int id) {
        return commentRepository.findByNewsId(id);
    }
}
