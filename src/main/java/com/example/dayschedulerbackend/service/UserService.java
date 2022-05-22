package com.example.dayschedulerbackend.service;

import com.example.dayschedulerbackend.domain.dto.UserDto;
import com.example.dayschedulerbackend.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService {
    UserDto getById(UUID userId);
    User getEntityById(UUID userId);
    UserDto getByEmail(String email);
    void create(UserDto userDto);
    void update(UserDto userDto);
    void deleteById(UUID userId);
}
