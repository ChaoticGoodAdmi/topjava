package ru.javawebinar.topjava.service.datajpa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.NOT_FOUND;
import static ru.javawebinar.topjava.UserTestData.USER_MATCHER;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getWithMeals() {
        User userWithMeals = service.getWithMeals(UserTestData.USER_ID);
        USER_MATCHER.assertMatch(userWithMeals, UserTestData.getWithMeals());
        MEAL_MATCHER.assertMatch(MealTestData.meals, userWithMeals.getMeals());
    }

    @Test
    public void getWithNotFoundMeals() {
        assertThrows(NotFoundException.class, () -> service.getWithMeals(NOT_FOUND));
    }

    @Test
    public void getWithEmptyMeals() {
        User newUser = UserTestData.getNew();
        service.create(newUser);
        assertThrows(NotFoundException.class, () -> service.getWithMeals(newUser.id()));
    }
}
