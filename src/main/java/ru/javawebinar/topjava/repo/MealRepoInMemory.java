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
    private final Logger LOG = LoggerFactory.getLogger(MealRepoInMemory.class);
    private final Map<Integer, Meal> STORAGE = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(1);

    public MealRepoInMemory() {
        save(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 1, 7, 30, 0), "Breakfast", 300));
        save(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 1, 12, 0, 0), "Lunch", 400));
        save(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 1, 18, 40, 0), "Dinner", 600));
        save(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Breakfast", 350));
        save(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Lunch", 450));
        save(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Dinner", 500));
        save(new Meal(LocalDateTime.of(2020, Month.OCTOBER, 2, 12, 0, 0), "Supper", 800));
    }

    @Override
    public void save(Meal meal) {
        int id = getNewId();
        meal.setId(id);
        STORAGE.putIfAbsent(id, meal);
    }

    @Override
    public Meal get(int id) {
        return STORAGE.get(id);
    }

    @Override
    public void update(Meal meal) {
        STORAGE.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        STORAGE.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(STORAGE.values());
    }

    @Override
    public int size() {
        return counter.intValue();
    }

    @Override
    public int getId(Meal meal) {
        for (Map.Entry<Integer, Meal> entry : STORAGE.entrySet()) {
            if (entry.getValue() == meal) {
                return entry.getKey();
            }
        }
        return -1;
    }

    private int getNewId() {
        return counter.getAndIncrement();
    }
}
