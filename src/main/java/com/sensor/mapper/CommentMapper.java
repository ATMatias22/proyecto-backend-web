package com.sensor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sensor.dto.CommentDTO;
import com.sensor.persistence.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mappings({
            @Mapping(source = "commentId", target = "id"),
            @Mapping(source = "comment", target = "comment"),
            @Mapping(source = "userId", target = "idUser"),
            @Mapping(source = "productId", target = "idProduct"),
            @Mapping(source = "created", target = "created"),
            @Mapping(source = "updated", target = "updated"),
            @Mapping(source = "comment.user.email", target = "email")
    })
    CommentDTO toCommentDTO(Comment comment);

    @Mappings({
            @Mapping(source = "idProduct", target = "productId"),
            @Mapping(source = "comment", target = "comment"),
            @Mapping(source = "idUser", target = "userId"),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),
            @Mapping(target = "commentId", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    Comment toComment(CommentDTO commentDTO);
}
