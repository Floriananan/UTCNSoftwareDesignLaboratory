package com.sd.assignment1.service;

import com.sd.assignment1.dal.PerformanceRepo;
import com.sd.assignment1.dal.UserRepo;
import com.sd.assignment1.dto.PerformanceDTO;
import com.sd.assignment1.dto.UserDTO;
import com.sd.assignment1.mappers.PerformanceMapper;
import com.sd.assignment1.mappers.UserMapper;
import com.sd.assignment1.model.Performance;
import com.sd.assignment1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class PerformanceService {

    PerformanceRepo performanceRepo;
    //@Autowired
    PerformanceMapper performanceMapper;

    public PerformanceDTO createPerformance(PerformanceDTO performanceDTO){
        Performance performance = performanceMapper.performanceDTO_TO_performance(performanceDTO);
        Performance createdPerformance = performanceRepo.save(performance);
        return performanceMapper.performance_TO_performanceDTO(createdPerformance);
    }
    public PerformanceDTO updatePerformance(Long id, PerformanceDTO performanceDTO){
        Performance performanceHelper = performanceRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Performance not found"));
        Performance performanceHelper2 = performanceMapper.performanceDTO_TO_performance(performanceDTO);
        performanceHelper2.setId(performanceHelper.getId());


        Performance updatedPerformance = performanceRepo.save(performanceHelper2);
        return performanceMapper.performance_TO_performanceDTO(updatedPerformance);
    }

    public void deletePerformance(Long id){
        performanceRepo.deleteById(id);
    }

    public PerformanceDTO getPerformanceById(Long id){
        Performance performance = performanceRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Performance not found"));
        return performanceMapper.performance_TO_performanceDTO(performance);
    }
    public List<PerformanceDTO> getAllPerformances() {
        List<Performance> performances = performanceRepo.findAll();
        List<PerformanceDTO> performanceDTOS = new ArrayList<>();
        for (Performance performance : performances) {
            PerformanceDTO userDTO = performanceMapper.performance_TO_performanceDTO(performance);
            performanceDTOS.add(userDTO);
        }
        return performanceDTOS;
    }
}
