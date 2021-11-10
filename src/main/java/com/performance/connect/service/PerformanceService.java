package com.performance.connect.service;

import com.performance.connect.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {
    private PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }
}
