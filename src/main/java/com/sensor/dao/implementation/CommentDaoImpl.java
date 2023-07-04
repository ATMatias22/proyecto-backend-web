package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.Product;
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
	public List<Comment> getAllComments() {
		return (List<Comment>) commentRepository.findAll();
	}

	@Override
	public Optional<Comment> getCommentById(Long commentId) {
		return commentRepository.findById(commentId);
	}

	@Override
	public void saveComment(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	public void deleteCommentById(Long commentId) {
		commentRepository.deleteById(commentId);
	}

	@Override
	public List<Comment> getAllCommentsForAProduct(Product product) {
		return commentRepository.findByProduct(product);
	}

}
