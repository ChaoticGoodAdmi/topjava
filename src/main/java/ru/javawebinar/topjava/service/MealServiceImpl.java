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
    private final Logger LOG = LoggerFactory.getLogger(MealServiceImpl.class);
    private final Repository<Meal> repo;

    public MealServiceImpl(Repository<Meal> repo) {
        this.repo = repo;
    }

    @Override
    public void saveToRepo(Meal meal) {
        repo.save(meal);
        LOG.debug(meal.toString() + " saved");
    }

    @Override
    public Meal findById(int id) {
        return repo.get(id);
    }

    @Override
    public void updateInRepo(Meal meal) {
        repo.update(meal);
    }

    @Override
    public void deleteFromRepo(int id) {
        repo.delete(id);
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
