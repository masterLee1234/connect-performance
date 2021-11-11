package com.performance.connect.repository;

import com.performance.connect.domain.Performance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;

public class JdbcTemplatePerformanceRepository implements PerformanceRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePerformanceRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Performance save(Performance performance) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("performance");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", performance.getId());
        parameters.put("school", performance.getSchool());
        parameters.put("grade", performance.getGrade());
        parameters.put("cls", performance.getCls());
        parameters.put("subject", performance.getSubject());
        parameters.put("created", performance.getDate());
        parameters.put("updated", performance.getUpdated());
        parameters.put("due", performance.getDue());
        parameters.put("title", performance.getTitle());
        parameters.put("desc", performance.getDesc());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));
        return performance;
    }

    @Override
    public Optional<Performance> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Performance> findByTitle(String title) {
        List<Performance> result = jdbcTemplate.query("select * from performance where title = ? ", performanceRowMapper(), title);
        return result.stream().findAny();
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
