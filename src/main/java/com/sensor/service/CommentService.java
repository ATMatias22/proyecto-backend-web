package com.sensor.service;

import java.util.List;

import com.sensor.dto.comment.request.CommentDTO;

public interface CommentService {

	List<CommentDTO> getAll();

	CommentDTO getComment(Long commentId);
	
	List<CommentDTO> getAllCommentsForAProduct(Long productId);

	void save(CommentDTO commentDTO);

	void delete(Long commentId);
	
}
