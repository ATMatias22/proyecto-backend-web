package com.sensor.DAO.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.DAO.CommentRepository;
import com.sensor.persistence.entity.Comment;
import com.sensor.repository.CommentCrudRepository;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

	@Autowired
	private CommentCrudRepository commentCrudRepository;

	@Override
	public List<Comment> getAll() {
		return (List<Comment>) commentCrudRepository.findAll();
	}

	@Override
	public Optional<Comment> getComment(Long commentId) {
		return commentCrudRepository.findById(commentId);
	}

	@Override
	public Comment save(Comment comment) {
		return commentCrudRepository.save(comment);
	}

	@Override
	public void delete(Long commentId) {
		commentCrudRepository.deleteById(commentId);
	}

	@Override
	public List<Comment> getAllCommentsForAProduct(Long productId) {
		return commentCrudRepository.findByProductId(productId);
	}

}
