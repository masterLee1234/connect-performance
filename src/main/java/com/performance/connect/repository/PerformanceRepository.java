package com.performance.connect.repository;

import com.performance.connect.domain.Performance;

import java.util.List;
import java.util.Optional;

public interface PerformanceRepository {
    Performance save(Performance performance);
    Optional<Performance> findById(String id);
    Optional<Performance> findByTitle(String Title);
    List<Performance> findAll();

}
