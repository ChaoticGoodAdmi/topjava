package ru.javawebinar.topjava.repository.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcUserRepositoryTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private JdbcMealRepository repository;
    private final Meal testMeal = new Meal(LocalDateTime.of(2020, 10, 15, 12, 0), "Test", 1000);

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
        Meal meal = repository.get(100002, 100001);
        assertEquals(meal, meal);
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void getAll() {
    }
}