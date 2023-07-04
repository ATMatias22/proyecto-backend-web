package com.sensor.service;

import java.util.List;

import com.sensor.entity.Comment;

public interface ICommentService {

	List<Comment> getAllComments();

	Comment getCommentById(Long commentId);
	
	List<Comment> getAllCommentsForAProductById(Long productId);

	void saveComment(Comment comment, Long productId);

	void deleteCommentById(Long commentId);
	
}
