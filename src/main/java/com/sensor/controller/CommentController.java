package com.sensor.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sensor.dto.comment.request.CommentRequest;
import com.sensor.dto.comment.response.CommentResponse;
import com.sensor.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentController {

	@Autowired
	private ICommentService commentService;
	@Autowired
	private CommentMapper commentMapper;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CommentResponse>> getAllComments() {
		return new ResponseEntity<>(this.commentService.getAllComments().stream().map(comment -> this.commentMapper.toCommentResponse(comment)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/{commentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CommentResponse> getCommentById(@PathVariable("commentId") Long commentId) {
		return new ResponseEntity<>(this.commentMapper.toCommentResponse(this.commentService.getCommentById(commentId)), HttpStatus.OK);
	}

	@GetMapping(value = "/products/{productId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CommentResponse>> getCommentsForAProduct(@PathVariable("productId") Long productId) {
		return new ResponseEntity<>(this.commentService.getAllCommentsForAProductById(productId).stream().map(comment -> this.commentMapper.toCommentResponse(comment)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/products/{productId}", consumes = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> saveComment(@PathVariable("productId") Long productId, @RequestBody @Valid CommentRequest commentRequest) {
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
