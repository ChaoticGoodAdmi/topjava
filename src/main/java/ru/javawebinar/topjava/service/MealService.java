package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {
    void create(Meal meal);

    Meal findById(int id);

    void update(Meal meal);

    void delete(int id);

    List<Meal> findAll();

    List<MealTo> findAllWithExcesses(int caloriesDayLimit);
}
