package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.DAO.CommentRepository;
import com.sensor.DAO.ProductRepository;
import com.sensor.DAO.UserRepository;
import com.sensor.dto.CommentDTO;
import com.sensor.exception.BlogAppException;
import com.sensor.mapper.CommentMapper;
import com.sensor.persistence.entity.Comment;
import com.sensor.persistence.entity.Product;
import com.sensor.persistence.entity.User;
import com.sensor.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<CommentDTO> getAll() {
		return commentRepository.getAll().stream().map((comment) -> commentMapper.toCommentDTO(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDTO getComment(Long commentId) {
		Optional<Comment> opt = commentRepository.getComment(commentId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el comentario: " + commentId);
		}
		return commentMapper.toCommentDTO(opt.get());
	}
	
	@Override
	public List<CommentDTO> getAllCommentsForAProduct(Long productId) {
		
		Optional<Product> product = productRepository.getProductEnabled(productId);

		if (product.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND,
					"No existe el producto con id : " + productId);
		}
		
		return commentRepository.getAllCommentsForAProduct(productId).stream().map((comment)-> commentMapper.toCommentDTO(comment)).collect(Collectors.toList());
	}


	@Override
	public void save(CommentDTO commentDTO) {
		Optional<Product> product = productRepository.getProductEnabled(commentDTO.getIdProduct());
		
		if (product.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND,
					"No existe el producto con id : " + commentDTO.getIdProduct());
		}
		User user = userRepository.getUserByEmail(commentDTO.getEmail()).get();
		
		commentDTO.setIdUser(user.getUserId());
		
		commentRepository.save(commentMapper.toComment(commentDTO));

	}

	@Override
	public void delete(Long commentId) {
		Optional<Comment> opt = commentRepository.getComment(commentId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el comentario con id : " + commentId);
		}

		commentRepository.delete(commentId);
	}



}
