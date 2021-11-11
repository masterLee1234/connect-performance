package com.performance.connect.service;

import com.performance.connect.domain.Performance;
import com.performance.connect.repository.PerformanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class PerformanceServiceTest {
    @Autowired PerformanceService performanceService;
    @Autowired PerformanceRepository performanceRepository;

    @Test
    void 생성(){
        //given
        Performance performance = new Performance();
        performance.setSchool("교하고등학교");
        performance.setGrade(2);
        performance.setCls(4);
        performance.setSubject("국어");
        performance.setDue("2021-12-10");
        performance.setTitle("수행1");
        performance.setDesc("책읽기");

        //when
        String saveId = performanceService.post(performance);

        //then
        Performance findPerformance = performanceService.findOne(saveId).get();
        assertThat(performance.getTitle()).isEqualTo(findPerformance.getTitle());
    }

    @Test
    void 중복_수행_예외(){
        //given
        Performance performance1 = new Performance();
        performance1.setSchool("교하고등학교");
        performance1.setGrade(2);
        performance1.setCls(4);
        performance1.setSubject("국어");
        performance1.setDue("2021-12-10");
        performance1.setTitle("수행1");
        performance1.setDesc("책읽기");

        Performance performance2 = new Performance();
        performance2.setSchool("교하고등학교");
        performance2.setGrade(2);
        performance2.setCls(4);
        performance2.setSubject("국어");
        performance2.setDue("2021-12-10");
        performance2.setTitle("수행1");
        performance2.setDesc("책읽기");

        //when
        performanceService.post(performance1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> performanceService.post(performance2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 수행평가입니다.");
    }

    @Test
    void 찾기_데이터_이용(){
        //given
        Performance performance = new Performance();
        performance.setSchool("교하고등학교");
        performance.setGrade(2);
        performance.setCls(4);
        performance.setSubject("국어");
        performance.setDue("2021-12-10");
        performance.setTitle("수행1");
        performance.setDesc("책읽기");

        Performance performance1 = new Performance();
        performance1.setSchool("교하고등학교");
        performance1.setGrade(2);
        performance1.setCls(4);
        performance1.setSubject("국어");
        performance1.setDue("2021-12-10");
        performance1.setTitle("수행2");
        performance1.setDesc("책읽기");

        Performance performance2 = new Performance();
        performance2.setSchool("교하고등학교");
        performance2.setGrade(2);
        performance2.setCls(4);
        performance2.setSubject("국어");
        performance2.setDue("2021-12-10");
        performance2.setTitle("수행3");
        performance2.setDesc("책읽기");

        performanceService.post(performance);
        performanceService.post(performance1);
        performanceService.post(performance2);

        List<Performance> performances = new ArrayList<Performance>();
        performances.add(performance);
        performances.add(performance1);
        performances.add(performance2);

        //when
        List<Performance> findPerformances = performanceService.findMine("교하고등학교", 2, 4);

        //then
        String performance_title_sum = "";
        String find_performance_title_sum = "";

        int i = 0;
        for (i = 0; i<3; i++) {
            performance_title_sum += performances.get(i).getTitle();
        }

        for (i = 0; i<3; i++) {
            find_performance_title_sum += findPerformances.get(i).getTitle();
        }
        assertThat(performance_title_sum).isEqualTo(find_performance_title_sum);
    }
}
