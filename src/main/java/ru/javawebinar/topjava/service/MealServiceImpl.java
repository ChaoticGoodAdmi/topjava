package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repo.Repository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

public class MealServiceImpl implements MealService {
    private final Repository<Meal> repo;

    public MealServiceImpl(Repository<Meal> repo) {
        this.repo = repo;
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
