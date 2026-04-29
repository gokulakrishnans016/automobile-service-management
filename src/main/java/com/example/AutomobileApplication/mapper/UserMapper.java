package com.example.AutomobileApplication.mapper;

import com.example.AutomobileApplication.dto.UserRequestDTO;
import com.example.AutomobileApplication.dto.UserResponseDTO;
import com.example.AutomobileApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // DTO TO ENTITY
    public User toEntity(UserRequestDTO dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setGender(dto.getGender());

        if (dto.getRole() == null || dto.getRole().isEmpty())
        {
            user.setRole("USER");
        }
        else
        {
            user.setRole(dto.getRole());
        }

        user.setActive(true);

        return user;
    }

    //  ENTITY TO DTO
    public UserResponseDTO toDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setGender(user.getGender());
        dto.setRole(user.getRole());
        dto.setActive(user.isActive());

        return dto;
    }
}