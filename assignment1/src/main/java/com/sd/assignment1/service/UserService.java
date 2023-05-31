package com.sd.assignment1.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.sd.assignment1.dal.UserRepo;
import com.sd.assignment1.dto.UserDTO;
import com.sd.assignment1.mappers.UserMapper;
import com.sd.assignment1.model.User;
import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;


    public UserDTO createUser(UserDTO userDTO){
        User user = userMapper.userDTO_TO_user(userDTO);
        String encPass = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encPass);
        User createdUser = userRepo.save(user);
        return userMapper.user_TO_userDTO(createdUser);
    }
    public boolean authPass(String username, String password){
        Optional<User> optionalUser = userRepo.findByUsername(username);
        User user = optionalUser.orElseThrow(()-> new NoSuchElementException("User not found"));
        return passwordEncoder.matches(password, user.getPassword());
    }
    public UserDTO authO(String username, String password){
        Optional<User> optionalUser = userRepo.findByUsername(username);
        User user = optionalUser.orElseThrow(()-> new NoSuchElementException("User not found"));
        if(passwordEncoder.matches(password, user.getPassword())){
            userMapper.user_TO_userDTO(user);

        }else throw new NoSuchElementException("Invalid password"); //todo change exception
        return null;
    }


    public UserDTO updateUser(Long id, UserDTO userDTO){
        User userHelper = userRepo.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        userHelper.setUsername(userDTO.getUsername());
        userHelper.setRole(userDTO.getRole());
        //String encPass = passwordEncoder.encode(userDTO.getPassword());
//        userHelper.setPassword(encPass);
        userHelper.setPassword(userDTO.getPassword());

        User updatedUser = userRepo.save(userHelper);
        return userMapper.user_TO_userDTO(updatedUser);
    }

    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

    public UserDTO getUserById(Long id){
        User user = userRepo.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        return userMapper.user_TO_userDTO(user);
    }
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = userMapper.user_TO_userDTO(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

}
