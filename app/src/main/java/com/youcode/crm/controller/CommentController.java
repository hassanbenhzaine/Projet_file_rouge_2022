package com.youcode.crm.controller;

import com.youcode.crm.entity.Comment;
import com.youcode.crm.entity.dto.CommentDTO;
import com.youcode.crm.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.youcode.crm.controller.ApiMapping.COMMENTS_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENTS_REST_URL)
public class CommentController {
    private final CommentService commentService;

    @GetMapping(produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<CommentDTO>> getAllComments(Pageable pageable){
        return status(HttpStatus.OK).body(commentService.getAllComments(pageable));
    }

    @GetMapping(path = "/{id}", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<CommentDTO>> getAllCommentsByPostId(@PathVariable final Long id, Pageable pageable){
        return status(HttpStatus.OK).body(commentService.getAllCommentsById(id, pageable));
    }

    @PostMapping(path = "/{id}", produces=APPLICATION_JSON_VALUE, consumes=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommentDTO> addNewCommentToPost(@PathVariable final Long id, @RequestBody final CommentDTO comment, Principal principal){
        return status(HttpStatus.OK).body(commentService.addNewCommentToPost(id, comment, principal));
    }

    @PutMapping(produces=APPLICATION_JSON_VALUE, consumes=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommentDTO> editComment(@RequestBody final CommentDTO comment, Principal principal){
        return status(HttpStatus.OK).body(commentService.editComment(comment, principal));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public void deleteCommentInPostById(@PathVariable final Long id, Principal principal) {
        commentService.deleteCommentInPostById(id, principal);
    }
}
