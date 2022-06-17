package com.youcode.crm.controller;

import com.youcode.crm.entity.Post;
import com.youcode.crm.entity.dto.PostDTO;
import com.youcode.crm.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import static com.youcode.crm.controller.ApiMapping.POSTS_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(POSTS_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostController {
    private final PostService postService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<PostDTO>> getAllPosts(Pageable pageable){
        return status(HttpStatus.OK).body(postService.getAllPosts(pageable));
    }

    @PostMapping(consumes=APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<PostDTO> addNewPost(@RequestBody final PostDTO post, Principal principal) {
        return status(HttpStatus.CREATED).body(postService.addNewPost(post, principal));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public void deletePostById(@PathVariable final Long id, Principal principal) {
        postService.deletePostById(id, principal);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<PostDTO> editPostContent(@RequestBody final PostDTO post, Principal principal){
        return status(HttpStatus.OK).body(postService.editPostContent(post, principal));
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<PostDTO> getPostById(@PathVariable final Long id) {
        return status(HttpStatus.OK).body(postService.getPostById(id));
    }

    @GetMapping(path = "/author", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Set<PostDTO>> getPostsByAuthorFirstNameAndLastName(
            @RequestParam final String firstName,
            @RequestParam final String lastName,
            Pageable pageable){
        return status(HttpStatus.OK).body(postService.findPostsByAuthorFirstnameAndLastname(firstName, lastName, pageable));
    }
}
