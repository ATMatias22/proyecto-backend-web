package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.dao.ICommentDao;
import com.sensor.dao.IProductDao;
import com.sensor.security.dao.IUserDao;
import com.sensor.dto.comment.request.CommentDTO;
import com.sensor.exception.GeneralException;
import com.sensor.mapper.CommentMapper;
import com.sensor.entity.Comment;
import com.sensor.entity.Product;
import com.sensor.security.entity.User;
import com.sensor.service.ICommentService;


@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private ICommentDao commentDao;

	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<CommentDTO> getAll() {
		return commentDao.getAll().stream().map((comment) -> commentMapper.toCommentDTO(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDTO getComment(Long commentId) {
		Optional<Comment> opt = commentDao.getComment(commentId);

		if (opt.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el comentario: " + commentId);
		}
		return commentMapper.toCommentDTO(opt.get());
	}
	
	@Override
	public List<CommentDTO> getAllCommentsForAProduct(Long productId) {
		
		Optional<Product> product = productDao.getProductEnabled(productId);

		if (product.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND,
					"No existe el producto con id : " + productId);
		}
		
		return commentDao.getAllCommentsForAProduct(productId).stream().map((comment)-> commentMapper.toCommentDTO(comment)).collect(Collectors.toList());
	}


	@Override
	public void save(CommentDTO commentDTO) {
		Optional<Product> product = productDao.getProductEnabled(commentDTO.getIdProduct());
		
		if (product.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND,
					"No existe el producto con id : " + commentDTO.getIdProduct());
		}
		User user = userDao.getUserByEmail(commentDTO.getEmail()).get();
		
		commentDTO.setIdUser(user.getUserId());
		
		commentDao.save(commentMapper.toComment(commentDTO));

	}

	@Override
	public void delete(Long commentId) {
		Optional<Comment> opt = commentDao.getComment(commentId);

		if (opt.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el comentario con id : " + commentId);
		}

		commentDao.delete(commentId);
	}



}
