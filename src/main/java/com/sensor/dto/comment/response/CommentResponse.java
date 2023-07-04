package com.sensor.dto.comment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

	private String email;
	
	private String comment;
	
	private String created;

	private String updated;
	
}
