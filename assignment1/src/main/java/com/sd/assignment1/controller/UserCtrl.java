package com.sd.assignment1.controller;

import com.sd.assignment1.dal.UserRepo;
import com.sd.assignment1.dto.UserDTO;
import com.sd.assignment1.model.User;
import com.sd.assignment1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserCtrl {
    //@Autowired
    UserService userService;
    //@Autowired
    //PasswordEncoder passwordEncoder;

    public UserCtrl(UserService userService){ //, PasswordEncoder passwordEncoder){
        this.userService = userService;
        //this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOs = userService.getAllUsers();
        return ResponseEntity.ok(userDTOs);
    }

    @PostMapping("/{username}/{password}/{role}")
    //@PutMapping("/{username}/{password}/{role}")
    public ResponseEntity<UserDTO> createUser(@PathVariable String username, @PathVariable String password, @PathVariable String role){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setRole(role);
//        String encPass = passwordEncoder.encode(password);
//        userDTO.setPassword(encPass);
        userDTO.setPassword(password);
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("{id}/{username}/{password}/{role}")
    public ResponseEntity<UserDTO> createUser(@PathVariable Long id, @PathVariable String username, @PathVariable String password, @PathVariable String role){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setRole(role);
//        String encPass = passwordEncoder.encode(password);
//        userDTO.setPassword(encPass);
        userDTO.setPassword(password);
        UserDTO createdUser = userService.updateUser(id, userDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdUser);
    }

}
