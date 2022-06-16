package com.youcode.crm.mapper;

import com.youcode.crm.entity.Comment;
import com.youcode.crm.entity.dto.CommentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "postId", source = "post.id"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "authorFirstName", source = "author.firstName"),
            @Mapping(target = "authorLastName", source = "author.lastName"),
            @Mapping(target = "content", source = "content")
    })
    CommentDTO mapCommentToDto(Comment comment);

    @InheritInverseConfiguration
    Comment mapCommentDtotoComment(CommentDTO commentDTO);
}
