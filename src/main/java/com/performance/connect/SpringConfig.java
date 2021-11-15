package com.performance.connect;

import com.performance.connect.repository.JdbcTemplatePerformanceRepository;
import com.performance.connect.repository.PerformanceRepository;
import com.performance.connect.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PerformanceService performanceService() {
        return new PerformanceService(performanceRepository());
    }

    @Bean
    public PerformanceRepository performanceRepository() {
        return new JdbcTemplatePerformanceRepository(dataSource);
    }
}
