package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;

import com.sensor.security.MainUser;
import com.sensor.security.service.IUserService;
import com.sensor.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sensor.dao.ICommentDao;
import com.sensor.dao.IProductDao;
import com.sensor.security.dao.IUserDao;
import com.sensor.exception.GeneralException;
import com.sensor.entity.Comment;
import com.sensor.entity.Product;
import com.sensor.security.entity.User;
import com.sensor.service.ICommentService;


@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private ICommentDao commentDao;

	@Autowired
	private IProductService productService;
	
	@Autowired
	private IUserService userService;


	@Override
	public List<Comment> getAllComments() {
		return commentDao.getAllComments();
	}

	@Override
	public Comment getCommentById(Long commentId) {
		return  commentDao.getCommentById(commentId).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el comentario: " + commentId));
	}
	
	@Override
	public List<Comment> getAllCommentsForAProductById(Long productId) {
		Product product = productService.getEnabledProductById(productId).getProduct();
		return commentDao.getAllCommentsForAProduct(product);
	}


	@Override
	public void saveComment(Comment comment, Long productId) {
		MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Product product = productService.getEnabledProductById(productId).getProduct();
		User user = userService.getUserByEmail(mu.getUsername());
		comment.setUser(user);
		comment.setProduct(product);
		commentDao.saveComment(comment);
	}

	@Override
	public void deleteCommentById(Long commentId) {
		this.getCommentById(commentId);
		commentDao.deleteCommentById(commentId);
	}



}
