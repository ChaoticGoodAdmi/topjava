package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repo.Repository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

public class MealServiceImpl implements MealService {
    private final Logger log = LoggerFactory.getLogger(MealServiceImpl.class);
    private final Repository<Meal> repo;

    public MealServiceImpl(Repository<Meal> repo) {
        this.repo = repo;
    }

    @Override
    public void create(Meal meal) {
        Meal newMeal = repo.create(meal);
        log.debug("Meal #{} has been created by Service", newMeal.getId());
    }

    @Override
    public Meal findById(int id) {
        return repo.get(id);
    }

    @Override
    public void update(Meal meal) {
        Meal editedMeal = repo.update(meal);
        log.debug("Meal #{} has been updated by Service", editedMeal.getId());
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
        log.debug("Meal #{} has been deleted by Service", id);
    }

    @Override
    public List<Meal> findAll() {
        return repo.getAll();
    }

    @Override
    public List<MealTo> findAllWithExcesses(int caloriesDayLimit) {
        return MealsUtil.filteredByStreams(findAll(), LocalTime.MIN, LocalTime.MAX, caloriesDayLimit);
    }
}
