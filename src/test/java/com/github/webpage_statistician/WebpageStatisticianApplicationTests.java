package com.github.webpage_statistician;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.github.webpage_statistician.domain.StatisticianController;

@SpringBootTest(args = { "https://spring.io" })
@TestPropertySource("/test_application.properties")
class WebpageStatisticianApplicationTests {

    @Autowired
    private StatisticianController statisticianController;

    @Test
    void contextLoads() throws Exception {
        assertThat(statisticianController).isNotNull();
    }
}