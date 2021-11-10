package com.performance.connect.repository;

import com.performance.connect.domain.Performance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcTemplatePerformanceRepository implements PerformanceRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePerformanceRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Performance save(Performance performance) {
        return null;
    }

    @Override
    public Optional<Performance> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Performance> findAll() {
        return null;
    }

    private RowMapper<Performance> performanceRowMapper() {
        return (rs, rowNum) -> {
            Performance performance = new Performance();
            performance.setId(rs.getString("id"));
            performance.setSchool(rs.getString("school"));
            performance.setGrade(rs.getInt("grade"));
            performance.setCls(rs.getInt("cls"));
            performance.setSubject(rs.getString("subject"));
            performance.setDate(rs.getString("created"));
            performance.setUpdated(rs.getString("updated"));
            performance.setDue(rs.getString("due"));
            performance.setTitle(rs.getString("title"));
            performance.setDesc(rs.getString("desc"));
            return performance;
        };
    }
}
