package com.interview.practice.graphqlspringboot.service;

import com.interview.practice.graphqlspringboot.dto.PostDto;
import com.interview.practice.graphqlspringboot.entity.Post;
import com.interview.practice.graphqlspringboot.entity.User;
import com.interview.practice.graphqlspringboot.exception.ResourceNotFoundException;
import com.interview.practice.graphqlspringboot.repository.PostRepository;
import com.interview.practice.graphqlspringboot.repository.UserRepository;
import java.util.List;
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
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", postDto.getUserId().toString()));

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(user);

        Post savedPost = postRepository.save(post);
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

    public PostDto updatePost(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postDto.getId().toString()));

        if (postDto.getTitle() != null) {
            post.setTitle(postDto.getTitle());
        }
        
        if (postDto.getContent() != null) {
            post.setContent(postDto.getContent());
        }

        if (postDto.getUserId() != null && !post.getUser().getId().equals(postDto.getUserId())) {
            User user = userRepository.findById(postDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", postDto.getUserId().toString()));
            post.setUser(user);
        }

        Post updatedPost = postRepository.save(post);
        return convertToDto(updatedPost);
    }

    public List<PostDto> getPostByUserId(Long userId) {
        // First check if user exists
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "id", userId.toString());
        }
        
        return postRepository.findPostByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Posts", "userId", userId.toString()))
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public String deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post", "id", id.toString());
        }
        
        postRepository.deleteById(id);
        return String.format("Post with id %d deleted successfully", id);
    }

    public List<PostDto> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }
}
