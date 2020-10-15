package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
}
)
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDb.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        final Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(MEAL_1, meal);
    }

    @Test
    public void getNonExistent() {
        assertThrows(NotFoundException.class, () -> service.get(0, USER_ID));
    }

    @Test
    public void getNotOwned() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID, USER_ID));
    }

    @Test
    public void deleteNonExistent() {
        assertThrows(NotFoundException.class, () -> service.delete(0, USER_ID));
    }

    @Test
    public void deleteNotOwned() {
        assertThrows(NotFoundException.class, () -> service.delete(USER_ID, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        final List<Meal> adminMeals = service.getBetweenInclusive(TEST_DATE, TEST_DATE, ADMIN_ID);
        assertMatch(FILTERED_ADMIN_MEALS, adminMeals);
    }

    @Test
    public void getBetweenNullDates() {
        assertMatch(SORTED_USER_MEALS, service.getBetweenInclusive(null, null, USER_ID));
    }

    @Test
    public void getAll() {
        final List<Meal> userMeals = service.getAll(USER_ID);
        assertMatch(SORTED_USER_MEALS, userMeals);
    }

    @Test
    public void update() {
        Meal meal = getUpdated();
        service.update(meal, USER_ID);
        assertMatch(getUpdated(), service.get(meal.getId(), USER_ID));
    }

    @Test
    public void updateNonExistent() {
        Meal meal = getNew();
        meal.setId(1);
        assertThrows(NotFoundException.class, () -> service.update(meal, USER_ID));
    }

    @Test
    public void updatedNotOwned() {
        Meal meal = getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(meal, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal meal = getNew();
        Meal createdMeal = service.create(meal, USER_ID);
        assertMatch(meal, createdMeal);
        assertMatch(service.get(createdMeal.getId(), USER_ID), createdMeal);
    }
}