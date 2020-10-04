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
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Breakfast", 350));
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Lunch", 450));
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Dinner", 500));
        create(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Supper", 800));
    }

    @Override
    public Meal create(Meal meal) {
        int id = getNewId();
        meal.setId(id);
        storage.put(id, meal);
        log.debug("Meal #{} has been put in repository", meal.getId());
        return meal;
    }

    @Override
    public Meal get(int id) {
        return storage.get(id);
    }

    @Override
    public Meal update(Meal meal) {
        int mealId = meal.getId();
        if (storage.containsKey(mealId)) {
            storage.put(mealId, meal);
            log.debug("Meal #{} has been updated in repository", mealId);
        }
        return meal;
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
        log.debug("Meal #{} has been deleted from repository", id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }

    private int getNewId() {
        return counter.getAndIncrement();
    }
}
