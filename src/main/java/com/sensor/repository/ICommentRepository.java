package com.sensor.repository;

import java.util.List;

import com.sensor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.sensor.entity.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findByProduct(Product product);

}
