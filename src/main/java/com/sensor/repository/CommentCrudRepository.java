package com.sensor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sensor.entity.Comment;

public interface CommentCrudRepository extends CrudRepository<Comment, Long>{
	
	public List<Comment> findByProductId(Long productId);

}
