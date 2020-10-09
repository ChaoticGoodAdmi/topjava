package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(MealRestController.class);
    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        log.info("getAll of user {}", authUserId());
        return MealsUtil.getTos(service.getAll(authUserId()), authUserCaloriesPerDay());
    }

    public List<MealTo> getAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        startDate = replaceWithBoundaryValue(startDate, LocalDate.MIN);
        endDate = replaceWithBoundaryValue(endDate, LocalDate.MAX);
        startTime = replaceWithBoundaryValue(startTime, LocalTime.MIN);
        endTime = replaceWithBoundaryValue(endTime, LocalTime.MAX);
        log.info("getAll for user {} from {} {} till {} {}", authUserId(), startDate, startTime, endDate, endTime);
        return MealsUtil.getTos(service.getAllFiltered(authUserId(), startDate, endDate, startTime, endTime), authUserCaloriesPerDay());
    }

    public Meal get(int id) {
        log.info("get meal {}", id);
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        meal.setUserId(authUserId());
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete meal {}", id);
        service.delete(id, authUserId());
    }

    public void update(Meal meal) {
        log.info("update {}", meal);
        meal.setUserId(authUserId());
        service.update(meal, authUserId());
    }

    private LocalTime replaceWithBoundaryValue(LocalTime lt, LocalTime min) {
        if (lt == null) {
            lt = min;
        }
        return lt;
    }

    private LocalDate replaceWithBoundaryValue(LocalDate ld, LocalDate min) {
        if (ld == null) {
            ld = min;
        }
        return ld;
    }
}