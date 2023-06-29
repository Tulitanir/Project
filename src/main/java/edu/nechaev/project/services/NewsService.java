package edu.nechaev.project.services;

import edu.nechaev.project.dto.Comment;
import edu.nechaev.project.dto.CommentPost;
import edu.nechaev.project.dto.News;
import edu.nechaev.project.dto.UpdateNewsRequest;

public interface NewsService {
    Iterable<News> getNews();

    Iterable<Comment> getCommentsByNewsId(long id);

    boolean deleteComment(long id);

    boolean deleteNews(long id);

    News addNews(UpdateNewsRequest news);

    Iterable<Comment> addComment(CommentPost commentPost);

    boolean updateComment(long id, String text);

    News updateNews(long id, String title, String text);
}
