package ru.javawebinar.topjava.repo;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MealRepoByList implements Repository<Meal>{
    private final List<Meal> STORAGE = new ArrayList<>();

    {
        STORAGE.add(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 1, 7, 30, 0), "Breakfast", 300));
        STORAGE.add(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 1, 12, 0, 0), "Lunch", 400));
        STORAGE.add(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 1, 18, 40, 0), "Dinner", 600));
        STORAGE.add(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Breakfast", 350));
        STORAGE.add(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Lunch", 450));
        STORAGE.add(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Dinner", 500));
        STORAGE.add(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Supper", 800));
    }

    @Override
    public List<Meal> getAll() {
        return STORAGE;
    }
}
