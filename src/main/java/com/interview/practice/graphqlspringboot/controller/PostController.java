package com.interview.practice.graphqlspringboot.controller;

import com.interview.practice.graphqlspringboot.dto.PostDto;
import com.interview.practice.graphqlspringboot.dto.UserDto;
import com.interview.practice.graphqlspringboot.service.PostService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
}
