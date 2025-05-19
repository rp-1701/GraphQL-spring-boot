package com.interview.practice.graphqlspringboot.controller;

import com.interview.practice.graphqlspringboot.dto.UserDto;
import com.interview.practice.graphqlspringboot.service.UserService;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @MutationMapping("createUser")
    public UserDto createUser(@Argument("userDto") UserDto userDto) {
        return userService.createUser(userDto);
    }

    @MutationMapping("updateUser")
    public UserDto updateUser(@Argument("userDto") UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @QueryMapping
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }

    @QueryMapping
    public UserDto getUserById(@Argument("id") Long id) {
        return userService.getUserById(id);
    }
}
