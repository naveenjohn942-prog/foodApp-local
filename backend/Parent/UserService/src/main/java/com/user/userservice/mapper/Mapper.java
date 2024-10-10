package com.user.userservice.mapper;

import com.user.userservice.model.Users;
import com.user.userservice.model.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Mapper {
    public UserDTO convertToDto(Users users){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(users.getUserId());
        userDTO.setFirstName(users.getFirstName());
        userDTO.setLastName(users.getLastName());
        userDTO.setEmail(users.getEmail());
        userDTO.setPassword(users.getPassword());
        userDTO.setRole(users.getRole());

        return userDTO;
    }

    public Users convertToEntity(UserDTO userDTO){
        Users users = new Users();
        users.setUserId(userDTO.getId());
        users.setFirstName(userDTO.getFirstName());
        users.setLastName(userDTO.getLastName());
        users.setEmail(userDTO.getEmail());
        users.setPassword(userDTO.getPassword());
        users.setRole(userDTO.getRole());

        return users;
    }

    public List<UserDTO> convertToDTOList(List<Users> users)
    {
        List<UserDTO> userDtoList = new ArrayList<>();
        users.stream().forEach(p -> {
            userDtoList.add(convertToDto(p));
        });
        return userDtoList;
    }
}
