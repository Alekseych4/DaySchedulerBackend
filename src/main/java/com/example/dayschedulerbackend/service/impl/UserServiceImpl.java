package com.example.dayschedulerbackend.service.impl;

import com.example.dayschedulerbackend.domain.dto.UserDto;
import com.example.dayschedulerbackend.domain.entity.User;
import com.example.dayschedulerbackend.repository.UserRepository;
import com.example.dayschedulerbackend.service.UserService;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConversionService conversionService;

    @Override
    public UserDto getById(UUID userId) {
        if (userId != null) {
            var user = userRepository.findById(userId)
                    .orElse(null);
            if (user != null) {
                return conversionService.convert(user, UserDto.class);
            }
        }

        return null;
    }

    @Override
    public User getEntityById(UUID userId) {
        if (userId != null) {
            return userRepository.findById(userId).orElse(null);
        }

        return null;
    }

    @Override
    public UserDto getByEmail(String email) {
        if (StringUtils.isNoneBlank(email)) {
            var user = userRepository.findByEmail(email).orElse(null);

            if (user != null) {
                return conversionService.convert(user, UserDto.class);
            }
        }

        return null;
    }

    @Override
    public void create(UserDto userDto) {
        var user = conversionService.convert(userDto, User.class);
        userRepository.save(user);
    }

    @Override
    public void update(UserDto userDto) {
        var updatedUser = conversionService.convert(userDto, User.class);

        if (updatedUser.getId() != null) {
            userRepository.findById(updatedUser.getId())
                    .ifPresent(user -> {
                        user.setName(updatedUser.getName());
                        user.setSurname(updatedUser.getSurname());
                        user.setEmail(updatedUser.getEmail());
                        user.setIdInService(updatedUser.getIdInService());
                        user.setProfileImageUrl(updatedUser.getProfileImageUrl());

                        userRepository.save(user);
                    });
        }
    }

    @Override
    public void deleteById(UUID userId) {
        userRepository.deleteById(userId);
    }
}
