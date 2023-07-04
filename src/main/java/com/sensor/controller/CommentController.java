package com.sensor.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sensor.dto.comment.request.CommentRequest;
import com.sensor.dto.comment.response.CommentResponse;
import com.sensor.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.service.ICommentService;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentController {

	@Autowired
	private ICommentService commentService;
	@Autowired
	private CommentMapper commentMapper;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<CommentResponse>> getAllComments() {
		return new ResponseEntity<>(this.commentService.getAllComments().stream().map(comment -> this.commentMapper.toCommentResponse(comment)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{commentId}")
	public ResponseEntity<CommentResponse> getCommentById(@PathVariable("commentId") Long commentId) {
		return new ResponseEntity<>(this.commentMapper.toCommentResponse(this.commentService.getCommentById(commentId)), HttpStatus.OK);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<List<CommentResponse>> getCommentsForAProduct(@PathVariable("productId") Long productId) {
		return new ResponseEntity<>(this.commentService.getAllCommentsForAProductById(productId).stream().map(comment -> this.commentMapper.toCommentResponse(comment)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/productos/{productoId}")
	public ResponseEntity<Void> saveComment(@PathVariable("productId") Long productId, @RequestBody CommentRequest commentRequest) {
		commentService.saveComment(this.commentMapper.toCommentEntity(commentRequest), productId );
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> deleteCommentById(@PathVariable("commentId") Long commentId) {
		commentService.deleteCommentById(commentId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
