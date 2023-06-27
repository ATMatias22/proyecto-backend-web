package com.sensor.controller;

import java.util.List;

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

import com.sensor.dto.comment.request.CommentDTO;
import com.sensor.service.ICommentService;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

	@Autowired
	private ICommentService ICommentService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<CommentDTO>> getAll() {
		return new ResponseEntity<>(ICommentService.getAll(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{commentId}")
	public ResponseEntity<CommentDTO> getComment(@PathVariable("commentId") Long commentId) {
		return new ResponseEntity<>(ICommentService.getComment(commentId), HttpStatus.OK);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<List<CommentDTO>> getCommentsForAProduct(@PathVariable("productId") Long productId) {

		return new ResponseEntity<>(ICommentService.getAllCommentsForAProduct(productId), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity save(@RequestBody CommentDTO commentDTO) {
		ICommentService.save(commentDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{commentId}")
	public ResponseEntity delete(@PathVariable("commentId") Long commentId) {
		ICommentService.delete(commentId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
