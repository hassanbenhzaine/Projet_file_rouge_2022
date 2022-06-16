package com.youcode.crm.mapper;

import com.youcode.crm.entity.Comment;
import com.youcode.crm.entity.Post;
import com.youcode.crm.entity.dto.CommentDTO;
import com.youcode.crm.entity.dto.PostDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mappings({
        @Mapping(target = "authorFirstName", source = "author.firstName"),
        @Mapping(target = "authorLastName", source = "author.lastName")
    })
    PostDTO mapPostToDTO(Post post);

    @InheritInverseConfiguration
    Post mapPostDtoToPost(PostDTO postDTO);
}
