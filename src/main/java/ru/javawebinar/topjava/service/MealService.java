package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {
    void saveToRepo(Meal meal);

    Meal findById(int id);

    void updateInRepo(Meal meal);

    void deleteFromRepo(int id);

    List<Meal> findAll();

    List<MealTo> findAllWithExcesses(int caloriesDayLimit);
}
