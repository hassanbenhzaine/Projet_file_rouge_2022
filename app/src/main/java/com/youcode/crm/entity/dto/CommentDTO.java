package com.youcode.crm.entity.dto;

import com.youcode.crm.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private Long postId;
    private String createdAt;
    private String authorFirstName;
    private String authorLastName;
    private String content;
}
