package com.example.AutomobileApplication.service;

import com.example.AutomobileApplication.dto.UserRequestDTO;
import com.example.AutomobileApplication.dto.UserResponseDTO;
import com.example.AutomobileApplication.entity.User;
import com.example.AutomobileApplication.exception.ResourceNotFoundException;
import com.example.AutomobileApplication.mapper.UserMapper;
import com.example.AutomobileApplication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    // REGISTER
    public UserResponseDTO register(UserRequestDTO dto) {

        User user = userMapper.toEntity(dto);
        return userMapper.toDTO(userRepository.save(user));
    }

    // GET ALL
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return userMapper.toDTO(user);
    }

    // DELETE
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // LOGIN SUPPORT
    public User findByUsernameOrEmail(String input) {
        return userRepository.findByUsername(input)
                .orElseGet(() -> userRepository.findByEmail(input)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }

    // ROLE CHECK
    public boolean isAdmin(User user) {
        return "ADMIN".equalsIgnoreCase(user.getRole());
    }

    public boolean isUser(User user) {
        return "USER".equalsIgnoreCase(user.getRole());
    }
}