package com.sensor.DAO;

import java.util.List;
import java.util.Optional;

import com.sensor.persistence.entity.Comment;

public interface CommentRepository {
	
	
	List<Comment> getAll();

	List<Comment> getAllCommentsForAProduct(Long productId);

	Optional<Comment> getComment(Long commentId);
	
	Comment save (Comment comment);
	
	void delete (Long commentId);
	

}
