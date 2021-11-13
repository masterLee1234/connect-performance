package com.performance.connect.service;

import com.performance.connect.domain.Performance;
import com.performance.connect.repository.PerformanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        performanceService.post(performance);

        Performance performance1 = new Performance();
        performance1.setSchool("교하고등학교");
        performance1.setGrade(2);
        performance1.setCls(4);
        performance1.setSubject("국어");
        performance1.setDue("2021-12-10");
        performance1.setTitle("수행2");
        performance1.setDesc("책읽기");
        performanceService.post(performance1);

        Performance performance2 = new Performance();
        performance2.setSchool("교하고등학교");
        performance2.setGrade(2);
        performance2.setCls(4);
        performance2.setSubject("국어");
        performance2.setDue("2021-12-10");
        performance2.setTitle("수행3");
        performance2.setDesc("책읽기");
        performanceService.post(performance2);

        List<Performance> performances = new ArrayList<>();
        performances.add(performance);
        performances.add(performance1);
        performances.add(performance2);

        //when
        List<Performance> findPerformances = performanceService.findMine("교하고등학교", 2, 4);

        //then
        List<String> performancesTitle = new ArrayList();
        performancesTitle.add(performances.get(0).getTitle());
        performancesTitle.add(performances.get(1).getTitle());
        performancesTitle.add(performances.get(2).getTitle());

        List<String> findPerformancesTitle = new ArrayList();
        findPerformancesTitle.add(findPerformances.get(0).getTitle());
        findPerformancesTitle.add(findPerformances.get(1).getTitle());
        findPerformancesTitle.add(findPerformances.get(2).getTitle());

        Collections.sort(performancesTitle);
        Collections.sort(findPerformancesTitle);

        System.out.println(performancesTitle.toString());
        System.out.println(findPerformancesTitle.toString());
        assertEquals(performancesTitle, findPerformancesTitle);
    }

    @Test
    void 삭제() {
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
        String delPerformanceTitle = performanceService.deleteOne(saveId);
        assertThat("'수행1'이 삭제 되었습니다.").isEqualTo(delPerformanceTitle);
    }


    @Test
    void 수정() {
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
        Performance updatedPerformance = performanceRepository.updateById(saveId, "2021-12-11", "수행2", "서평쓰기").get();
        assertThat("2021-12-11 00:00:00").isEqualTo(updatedPerformance.getDue());
        assertThat("수행2").isEqualTo(updatedPerformance.getTitle());
        assertThat("서평쓰기").isEqualTo(updatedPerformance.getDesc());
    }
}
