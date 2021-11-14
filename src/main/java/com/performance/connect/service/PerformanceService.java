package com.performance.connect.service;

import com.performance.connect.domain.Performance;
import com.performance.connect.repository.PerformanceRepository;

import java.text.SimpleDateFormat;
import java.util.*;

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

    public List<Performance> findMine(String school, int grade, int cls) {
        return performanceRepository.findByUserData(school, grade, cls);
    }

    public String deleteOne(String id) {
        return performanceRepository.deleteById(id);
    }


    public Optional<Performance> update(String id, String due, String title, String desc) {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return performanceRepository.updateById(id, due, title, desc, formatter.format(today));
    }

    private void validateDuplicatePerformance(Performance performance) {
        performanceRepository.findByTitle(performance.getTitle())
                .ifPresent(m -> {
                    if (Objects.equals(m.getSubject(), performance.getSubject()) && Objects.equals(m.getSchool(), performance.getSchool()) && m.getGrade() == performance.getGrade() && m.getCls() == performance.getCls()) {
                        throw new IllegalStateException("이미 존재하는 수행평가입니다.");
                    }
                });
    }
}
