package com.user.userservice.controller;

import com.user.userservice.mapper.Mapper;
import com.user.userservice.model.Users;
import com.user.userservice.model.dto.AuthRequest;
import com.user.userservice.model.dto.UserDTO;
import com.user.userservice.repository.UserRepository;
import com.user.userservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final Mapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    @Autowired
//    private AuthenticationManager authenticationManager;

    public UserController(UserService userService, Mapper mapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authorizationHeader);
        List<Users> users = userService.getAllUsers();
        return new ResponseEntity<>(mapper.convertToDTOList(users), HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        Users user = userService.getUserById(id);
        return new ResponseEntity<>(mapper.convertToDto(user), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody Users users) {
        try {
            userService.createUser(users);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest user) {
        Optional<Users> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.ok(existingUser.get());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        try {
            Users user = mapper.convertToEntity(userDTO);
            userService.updateUser(id, user);
            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
