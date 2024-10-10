package com.user.cartservice.service;

import com.user.cartservice.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="UserService", url = "http://localhost:8080")
public interface UserServiceFeignClient {
    @GetMapping("/user/get/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id);
}
