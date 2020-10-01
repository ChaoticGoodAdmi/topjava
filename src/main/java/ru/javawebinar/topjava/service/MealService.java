package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {
    List<Meal> findAll();
    List<MealTo> findAllWithExcesses(int caloriesDayLimit);
}
