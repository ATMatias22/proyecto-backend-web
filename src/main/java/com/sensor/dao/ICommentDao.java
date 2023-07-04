package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.Comment;
import com.sensor.entity.Product;

public interface ICommentDao {
	
	
	List<Comment> getAllComments();

	List<Comment> getAllCommentsForAProduct(Product product);

	Optional<Comment> getCommentById(Long commentId);
	
	void saveComment(Comment comment);
	
	void deleteCommentById(Long commentId);
	

}
