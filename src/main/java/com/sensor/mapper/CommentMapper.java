package com.sensor.mapper;

import com.sensor.dto.comment.request.CommentRequest;
import com.sensor.dto.comment.response.CommentResponse;
import com.sensor.utils.date.StringToLocalDateTimeAndViceVersa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sensor.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Autowired
    protected StringToLocalDateTimeAndViceVersa stdv;

    @Mappings({
            @Mapping(source = "comment.user.email", target = "email"),
            @Mapping(source = "comment.message", target = "comment"),
            @Mapping(target = "created", expression = "java(stdv.getString(comment.getCreatedDate()))"),
            @Mapping(target = "updated", expression = "java(stdv.getString(comment.getUpdatedDate()))")
    })
    public abstract CommentResponse toCommentResponse(Comment comment);

    @Mappings({
            @Mapping(source = "commentRequest.comment", target = "message"),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "commentId", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    public abstract Comment toCommentEntity(CommentRequest commentRequest);
}
