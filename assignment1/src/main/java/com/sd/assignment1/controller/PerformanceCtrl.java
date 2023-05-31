package com.sd.assignment1.controller;

import com.sd.assignment1.dto.PerformanceDTO;
import com.sd.assignment1.dto.UserDTO;
import com.sd.assignment1.service.PerformanceService;
import com.sd.assignment1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performances")
public class PerformanceCtrl {
    @Autowired
    UserService userService;
    //@Autowired
    PerformanceService performanceService;
    @PostMapping("/performances/{username}/{password}")
    public ResponseEntity<PerformanceDTO> createPerformance(
            @PathVariable String username,
            @PathVariable String password,
            @RequestBody PerformanceDTO performanceDTO) {

        UserDTO user = userService.authO(username, password);
        if (user == null || !user.getRole().equals("Administrator")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else if (!user.getRole().equals("Administrator")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else{
            PerformanceDTO createdPerformance = performanceService.createPerformance(performanceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPerformance);
        }
    }

    @PutMapping("/performances/{username}/{password}/{id}")
    public ResponseEntity<PerformanceDTO> updatePerformance(
            @PathVariable String username,
            @PathVariable String password,
            @PathVariable Long id,
            @RequestBody PerformanceDTO performanceDTO) {

        UserDTO user = userService.authO(username, password);
        if (user == null || !user.getRole().equals("Administrator")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else if (!user.getRole().equals("Administrator")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else{
            PerformanceDTO updatedPerformance = performanceService.updatePerformance(id, performanceDTO);
            return ResponseEntity.ok(updatedPerformance);
        }
    }

    @DeleteMapping("/performances/{username}/{password}/{id}")
    public ResponseEntity<PerformanceDTO> deletePerformance(
            @PathVariable String username,
            @PathVariable String password,
            @PathVariable Long id){
        UserDTO user = userService.authO(username, password);
        if (user == null || !user.getRole().equals("Administrator")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else if (!user.getRole().equals("Administrator")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else{
            performanceService.deletePerformance(id);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/performances/{username}/{password}/{id}")
    public ResponseEntity<PerformanceDTO> getPerformance(
            @PathVariable String username,
            @PathVariable String password,
            @PathVariable Long id){
        UserDTO user = userService.authO(username, password);
        if (user == null || !user.getRole().equals("Administrator")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else if (!user.getRole().equals("Administrator")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else{
            PerformanceDTO performanceDTO = performanceService.getPerformanceById(id);
            return ResponseEntity.ok(performanceDTO);
        }
    }
    @GetMapping("/performances/{username}/{password}/all")
    public ResponseEntity<List<PerformanceDTO>> getAllPerformances(
            @PathVariable String username,
            @PathVariable String password){
        UserDTO user = userService.authO(username, password);
        if (user == null || !user.getRole().equals("Administrator")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else if (!user.getRole().equals("Administrator")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else{
            List<PerformanceDTO> performanceDTOs = performanceService.getAllPerformances();
            return ResponseEntity.ok(performanceDTOs);
        }
    }
}
