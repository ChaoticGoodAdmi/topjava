package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {

    @Test
    public void getWithUser() {
        Meal mealWithUser = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MEAL_MATCHER.assertMatch(mealWithUser, MealTestData.adminMeal1);
        USER_MATCHER.assertMatch(mealWithUser.getUser(), admin);
    }

    @Test
    public void getWithNotFoundUser() {
        assertThrows(NotFoundException.class, () -> service.getWithUser(MealTestData.NOT_FOUND, UserTestData.NOT_FOUND));
    }

    @Test
    public void getWithUserNotOwned() {
        assertThrows(NotFoundException.class, () -> service.getWithUser(ADMIN_MEAL_ID, USER_ID));

    }
}
