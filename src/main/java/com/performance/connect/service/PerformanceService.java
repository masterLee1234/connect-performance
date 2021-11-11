package com.performance.connect.service;

import com.performance.connect.domain.Performance;
import com.performance.connect.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    public String post(Performance performance) {
        validateDuplicatePerformance(performance);
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        performance.setId(UUID.randomUUID().toString());
        performance.setDate(formatter.format(today));
        performance.setUpdated(formatter.format(today));
        performanceRepository.save(performance);
        return performance.getId();
    }

    public Optional<Performance> findOne(String performanceId) {
        return performanceRepository.findById(performanceId);
    }

    public List<Performance> findMine(String school, int grade, int cls){
        return performanceRepository.findByUserData(school, grade, cls);
    }

    private void validateDuplicatePerformance(Performance performance) {
        performanceRepository.findByTitle(performance.getTitle())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 수행평가입니다.");
                });
    }
}
