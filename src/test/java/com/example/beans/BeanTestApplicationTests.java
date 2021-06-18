package com.example.beans;

import com.example.bean.BeanTestApplication;
import com.example.bean.repository.DemoRepository;
import com.example.bean.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ActiveProfiles("test")
@SpringBootTest(classes = BeanTestApplication.class)
@ContextConfiguration(initializers = BeanTestApplicationTests.Initializer.class)
@Testcontainers
public class BeanTestApplicationTests {
    @Autowired
    private DemoService demoService;
    @Autowired
    private DemoRepository demoRepository;

    @Container
    private static final SharedPostgresqlContainer POSTGRE_SQL_CONTAINER = SharedPostgresqlContainer.getInstance()
        .withInitScript("default-schema.sql");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=" + POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                "spring.datasource.username=" + POSTGRE_SQL_CONTAINER.getUsername(),
                "spring.datasource.password=" + POSTGRE_SQL_CONTAINER.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    void test() {
        //given
        final var entities = demoRepository.findAll();

        //then
        assertThat(entities).hasSize(6);
        assertEquals("beanForBeanInjectionWithoutAutowiredNumberOne", demoService.getFirstBeanValue());
        assertEquals("beanForBeanInjectionWithoutAutowiredNumberTwo", demoService.getSecondBeanValue());
        assertThat(demoService.getDemoEntities()).containsAll(entities);
    }

}
