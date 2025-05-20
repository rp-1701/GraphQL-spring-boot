package com.interview.practice.graphqlspringboot.service;

import com.interview.practice.graphqlspringboot.dto.PostDto;
import com.interview.practice.graphqlspringboot.entity.Post;
import com.interview.practice.graphqlspringboot.entity.User;
import com.interview.practice.graphqlspringboot.repository.PostRepository;
import com.interview.practice.graphqlspringboot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostDto createPost(PostDto postDto) {
        User user = userRepository.findById(postDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + postDto.getUserId()));

        // Create and save the post
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(user);

        Post savedPost = postRepository.save(post);

        // Convert to DTO for response
        return convertToDto(savedPost);
    }

    private PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUserId(post.getUser().getId());
        return postDto;
    }
}
