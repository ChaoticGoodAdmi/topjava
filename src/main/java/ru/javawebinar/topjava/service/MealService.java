package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int userId) {
        checkAuthorisation(meal, userId);
        return repository.save(meal);
    }

    public void delete(int id, int userId) {
        checkAuthorisation(repository.get(id), userId);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Meal get(int id, int userId) {
        checkAuthorisation(repository.get(id), userId);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    public List<Meal> getAllFiltered(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return repository.getAllFiltered(userId, startDate, endDate, startTime, endTime);
    }

    public void update(Meal meal, int userId) {
        checkAuthorisation(meal, userId);
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    private void checkAuthorisation(Meal meal, int userId) {
        int ownerId = meal.getUserId();
        if (ownerId != userId) {
            throw new NotFoundException("Authorisation failed");
        }
    }
}