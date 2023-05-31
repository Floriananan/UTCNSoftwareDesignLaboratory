package com.sd.assignment1.dal;

import com.sd.assignment1.model.Performance;
import com.sd.assignment1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepo extends JpaRepository<Performance, Long> {
}
