package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    private static final int INITIAL_ID = 100000;
    public static final int USER_ID = INITIAL_ID;
    public static final int ADMIN_ID = INITIAL_ID + 1;
    public static final int MEAL_ID = INITIAL_ID + 2;
    public static final Meal MEAL_1 = new Meal(MEAL_ID, LocalDateTime.of(2020, 10, 15, 10, 0),
            "Breakfast", 500);
    private static final Meal MEAL_2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2020, 10, 15, 13, 0),
            "Lunch", 1000);
    private static final Meal MEAL_3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2020, 10, 15, 20, 0),
            "Dinner", 500);
    private static final Meal MEAL_4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2020, 10, 16, 0, 0),
            "Midnight snack", 100);
    private static final Meal MEAL_5 = new Meal(MEAL_ID + 4, LocalDateTime.of(2020, 10, 16, 10, 0),
            "Breakfast", 1000);
    private static final Meal MEAL_6 = new Meal(MEAL_ID + 5, LocalDateTime.of(2020, 10, 16, 13, 0),
            "Lunch", 500);
    private static final Meal MEAL_7 = new Meal(MEAL_ID + 6, LocalDateTime.of(2020, 10, 16, 20, 0),
            "Dinner", 410);
    private static final Meal MEAL_8 = new Meal(MEAL_ID + 7, LocalDateTime.of(2020, 10, 16, 10, 0),
            "Admin Breakfast", 1000);
    private static final Meal MEAL_9 = new Meal(MEAL_ID + 8, LocalDateTime.of(2020, 10, 16, 18, 0),
            "Admin Dinner", 1000);

    public static final List<Meal> SORTED_USER_MEALS = Arrays.asList(MEAL_7, MEAL_6, MEAL_5, MEAL_4, MEAL_3, MEAL_2, MEAL_1);
    public static final LocalDate TEST_DATE = LocalDate.of(2020, 10, 16);
    public static final List<Meal> FILTERED_ADMIN_MEALS = Arrays.asList(MEAL_9, MEAL_8);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, 1, 1, 0, 0), "TestData", 1000);
    }

    public static Meal getUpdated() {
        return new Meal(100002, LocalDateTime.of(2020, 1, 1, 0, 0), "UpdatedData", 10);
    }

    public static void assertMatch(Meal expected, Meal actual) {
        assertThat(expected.getId()).isEqualTo(actual.getId());
        assertThat(expected.getDateTime()).isEqualTo(actual.getDateTime());
        assertThat(expected.getDescription()).isEqualTo(actual.getDescription());
        assertThat(expected.getCalories()).isEqualTo(actual.getCalories());
    }

    public static void assertMatch(List<Meal> expected, List<Meal> actual) {
        assertThat(expected.size()).isEqualTo(actual.size());
        IntStream.range(0, actual.size()).forEach(i -> assertMatch(expected.get(i), actual.get(i)));
    }
}
