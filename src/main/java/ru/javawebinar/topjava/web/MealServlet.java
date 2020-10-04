package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repo.MealRepoInMemory;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private MealService mealService;

    public void init() {
        this.mealService = new MealServiceImpl(new MealRepoInMemory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int caloriesDayLimit = 2000;
        switch (action != null ? action : "list") {
            case "update":
                int id = getId(req);
                Meal meal = id == 0 ? getEmptyMeal() : mealService.findById(getId(req));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("edit.jsp").forward(req, resp);
                log.debug("forward to edit.jsp");
                break;
            case "delete":
                mealService.delete(getId(req));
                resp.sendRedirect("meals");
                log.debug("redirect to meals.jsp");
                break;
            case "list":
            default:
                req.setAttribute("mealList", mealService.findAllWithExcesses(caloriesDayLimit));
                req.getRequestDispatcher("meals.jsp").forward(req, resp);
                log.debug("forward to meals.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int id = getId(req);
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        if (id == 0) {
            mealService.create(new Meal(dateTime, description, calories));
        } else {
            mealService.update(new Meal(id, dateTime, description, calories));
        }
        resp.sendRedirect("meals");
        log.debug("redirect to meals.jsp");
    }

    private int getId(HttpServletRequest req) {
        String param = req.getParameter("id");
        return "".equals(param) ? 0 : Integer.parseInt(param);
    }

    private Meal getEmptyMeal() {
        return new Meal(0,
                LocalDateTime.of(
                        LocalDateTime.now().getYear(),
                        LocalDateTime.now().getMonth(),
                        LocalDateTime.now().getDayOfMonth(),
                        LocalDateTime.now().getHour(),
                        LocalDateTime.now().getMinute()
                ), "", 0);
    }
}
