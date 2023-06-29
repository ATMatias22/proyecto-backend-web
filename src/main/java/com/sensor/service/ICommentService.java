package com.sensor.service;

import java.util.List;

import com.sensor.dto.comment.request.CommentDTO;

public interface ICommentService {

	List<CommentDTO> getAllComments();

	CommentDTO getCommentById(Long commentId);
	
	List<CommentDTO> getAllCommentsForAProduct(Long productId);

	void saveComment(CommentDTO commentDTO);

	void deleteCommentById(Long commentId);
	
}
