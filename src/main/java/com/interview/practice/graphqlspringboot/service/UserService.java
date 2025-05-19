package com.interview.practice.graphqlspringboot.service;

import com.interview.practice.graphqlspringboot.dto.PostDto;
import com.interview.practice.graphqlspringboot.dto.UserDto;
import com.interview.practice.graphqlspringboot.entity.Post;
import com.interview.practice.graphqlspringboot.entity.User;
import com.interview.practice.graphqlspringboot.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());

        convertToPost(userDto, user);

        return user;
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        
        if (user.getPosts() != null) {
            List<PostDto> postDtos = user.getPosts().stream()
                .map(post -> {
                    PostDto postDto = new PostDto();
                    postDto.setId(post.getId());
                    postDto.setTitle(post.getTitle());
                    postDto.setContent(post.getContent());
                    return postDto;
                })
                .collect(Collectors.toList());
            userDto.setPosts(postDtos);
        }
        
        return userDto;
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userDto.getId()));

        if (userDto.getFirstname() != null) {
            user.setFirstname(userDto.getFirstname());
        }
        if (userDto.getLastname() != null) {
            user.setLastname(userDto.getLastname());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        convertToPost(userDto, user);

        User updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }

    private void convertToPost(UserDto userDto, User user) {
        if (userDto.getPosts() != null) {
            List<Post> posts = userDto.getPosts().stream()
                .map(postDto -> {
                    Post post = new Post();
                    post.setId(postDto.getId());
                    post.setTitle(postDto.getTitle());
                    post.setContent(postDto.getContent());
                    post.setUser(user);
                    return post;
                })
                .collect(Collectors.toList());
            user.setPosts(posts);
        }
    }
}
