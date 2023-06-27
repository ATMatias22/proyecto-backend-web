package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.Comment;

public interface ICommentDao {
	
	
	List<Comment> getAll();

	List<Comment> getAllCommentsForAProduct(Long productId);

	Optional<Comment> getComment(Long commentId);
	
	Comment save (Comment comment);
	
	void delete (Long commentId);
	

}
