package com.interview.practice.graphqlspringboot.controller;

import com.interview.practice.graphqlspringboot.dto.PostDto;
import com.interview.practice.graphqlspringboot.service.PostService;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @MutationMapping("createPost")
    public PostDto createPost(@Argument("postDto") PostDto postDto) {
        return postService.createPost(postDto);
    }

    @MutationMapping("updatePost")
    public PostDto updatePost(@Argument("postDto") PostDto postDto) {
        return postService.updatePost(postDto);
    }

    @QueryMapping("getPostByUserId")
    public List<PostDto> getPostByUserId(@Argument Long userId) {
        return postService.getPostByUserId(userId);
    }

    @QueryMapping("deletePost")
    public String deletePost(@Argument Long id) {
        return postService.deletePost(id);
    }

    @QueryMapping("getAllPost")
    public List<PostDto> getAllPost() {
        return postService.getAllPost();
    }
}
