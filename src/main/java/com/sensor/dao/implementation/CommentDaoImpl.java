package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.dao.ICommentDao;
import com.sensor.entity.Comment;
import com.sensor.repository.ICommentRepository;

@Repository
public class CommentDaoImpl implements ICommentDao {

	@Autowired
	private ICommentRepository commentRepository;

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
