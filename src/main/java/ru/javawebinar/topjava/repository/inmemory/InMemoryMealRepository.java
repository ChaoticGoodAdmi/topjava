package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    private InMemoryMealRepository() {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
        save(new Meal(LocalDateTime.of(2020, 10, 13, 7, 0), "User2 Breakfast", 500), 2);
        save(new Meal(LocalDateTime.of(2020, 10, 13, 12, 0), "User2 Lunch", 1000), 2);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.debug("save {}", meal);
        Map<Integer, Meal> userMeals = repository.computeIfAbsent(userId, HashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        } else if (userMeals.get(meal.getId()) == null) {
            return null;
        }
        userMeals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals != null && userMeals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        final Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(Predicate<Meal> filter, int userId) {
        log.info("getAll from user {}", userId);
        return repository.getOrDefault(userId, new HashMap<>()).values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

