package com.sd.assignment1.mappers;

import com.sd.assignment1.dto.UserDTO;
import com.sd.assignment1.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public UserDTO user_TO_userDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());

        return userDTO;
    }
    public User userDTO_TO_user(UserDTO userDTO){
        User user = new User();
        //user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());

        return user;
    }
}
