package com.example.dayschedulerbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private UUID id;
    private String name;
    private String surname;
    private String idInService;
    private String email;
    private String profileImageUrl;
}
