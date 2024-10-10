//package com.user.userservice.config;
//
//import com.user.userservice.model.Users;
//import com.user.userservice.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//@Component
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Users> credential = repository.findByEmail(username);
//        if (credential.isEmpty()) {
//            throw new UsernameNotFoundException("User not found with email: " + username);
//        }
//        return new CustomUserDetails(credential.get());
//    }
//
//}
