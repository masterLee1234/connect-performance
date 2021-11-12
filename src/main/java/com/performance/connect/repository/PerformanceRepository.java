package com.performance.connect.repository;

import com.performance.connect.domain.Performance;

import java.util.List;
import java.util.Optional;

public interface PerformanceRepository {
    Performance save(Performance performance);
    Optional<Performance> findById(String id);
    List<Performance> findByUserData(String school, int grade, int cls);
    Optional<Performance> findByTitle(String title);
    String deleteById(String id);
    List<Performance> findAll();

}
