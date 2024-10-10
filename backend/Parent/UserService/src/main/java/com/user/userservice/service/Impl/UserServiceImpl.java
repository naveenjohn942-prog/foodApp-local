package com.user.userservice.service.Impl;

import com.user.userservice.model.Users;
import com.user.userservice.repository.UserRepository;
import com.user.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
//    @Autowired
//    private JwtService jwtService;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void createUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(Integer id, Users user) {
        Users newUser = getUserById(id);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());
        userRepository.save(newUser);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
//
//    @Override
//    public String generateToken(String username) {
//        return jwtService.generateToken(username);
//    }
//    @Override
//    public void validateToken(String token) {
//        jwtService.validateToken(token);
//    }
}
