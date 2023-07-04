package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.Comment;

public interface ICommentDao {
	
	
	List<Comment> getAllComments();

	List<Comment> getAllCommentsForAProduct(Long productId);

	Optional<Comment> getCommentById(Long commentId);
	
	void saveComment(Comment comment);
	
	void deleteCommentById(Long commentId);
	

}
