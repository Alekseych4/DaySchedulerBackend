package com.example.dayschedulerbackend.converter;

import com.example.dayschedulerbackend.domain.dto.UserDto;
import com.example.dayschedulerbackend.domain.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto source) {
        return User.builder()
                .id(source.getId())
                .name(source.getName())
                .surname(source.getSurname())
                .email(source.getEmail())
                .idInService(source.getIdInService())
                .profileImageUrl(source.getProfileImageUrl())
                .build();
    }
}
