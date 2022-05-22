package com.example.dayschedulerbackend.controller;

import com.example.dayschedulerbackend.domain.dto.UserDto;
import com.example.dayschedulerbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/by-user-id/{userId}")
    public UserDto getByUserId(@PathVariable("userId") UUID userId) {
        return userService.getById(userId);
    }

    @GetMapping("/by-email/{email}")
    public UserDto getUserByEmail(@PathVariable("email") String email) {
        return userService.getByEmail(email);
    }

    @PostMapping("/create")
    public void createUser(@RequestBody UserDto userDto) {
        userService.create(userDto);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody UserDto userDto) {
        userService.update(userDto);
    }

    @DeleteMapping("/delete-by-user-id/{userId}")
    public void deleteByUserId(@PathVariable("userId") UUID userId) {
        userService.deleteById(userId);
    }
}
