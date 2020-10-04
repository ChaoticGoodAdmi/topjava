package ru.javawebinar.topjava.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealRepoInMemory implements Repository<Meal> {
    private final Logger log = LoggerFactory.getLogger(MealRepoInMemory.class);
    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    public MealRepoInMemory() {
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 1, 7, 30, 0), "Breakfast", 300));
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 1, 12, 0, 0), "Lunch", 400));
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 1, 18, 40, 0), "Dinner", 600));
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 7, 0, 0), "Breakfast", 350));
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Lunch", 450));
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 16, 0, 0), "Dinner", 500));
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 20, 0, 0), "Supper", 800));
    }

    @Override
    public Meal create(Meal meal) {
        int id = getNewId();
        meal.setId(id);
        storage.put(id, meal);
        log.debug("Meal #{} has been CREATED in Repository", meal.getId());
        return meal;
    }

    @Override
    public Meal get(int id) {
        Meal meal = storage.get(id);
        log.debug("Meal #{} has been RETRIEVED from Repository", meal.getId());
        return meal;
    }

    @Override
    public Meal update(Meal meal) {
        int mealId = meal.getId();
        storage.replace(mealId, meal);
        log.debug("Meal #{} has been UPDATED in Repository", mealId);
        return meal;
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
        log.debug("Meal #{} has been DELETED from Repository", id);
    }

    @Override
    public List<Meal> getAll() {
        ArrayList<Meal> meals = new ArrayList<>(storage.values());
        log.debug("Repository RETRIEVED this amount of items: {}", meals.size());
        return meals;
    }

    private int getNewId() {
        return counter.getAndIncrement();
    }
}
