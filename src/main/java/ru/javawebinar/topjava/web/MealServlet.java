package ru.javawebinar.topjava.web;

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
    private MealService mealService;
    private int caloriesDayLimit = 2000;

    public void init() {
        this.mealService = new MealServiceImpl(new MealRepoInMemory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action != null ? action : "list") {
            case "update":
                int id = getId(req);
                Meal meal = id == 0 ? getEmptyMeal() : mealService.findById(getId(req));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("edit.jsp").forward(req, resp);
                break;
            case "delete":
                mealService.deleteFromRepo(getId(req));
                resp.sendRedirect("meals");
                break;
            case "limit":
                req.setAttribute("limit", caloriesDayLimit);
                req.getRequestDispatcher("limit.jsp").forward(req, resp);
                break;
            case "list":
            default:
                req.setAttribute("mealList", mealService.findAllWithExcesses(caloriesDayLimit));
                req.getRequestDispatcher("meals.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("pageName");
        if ("limit".equals(action)) {
            caloriesDayLimit = Integer.parseInt(req.getParameter("limit"));
        } else if ("update".equals(action)) {
            int id = getId(req);
            LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
            String description = req.getParameter("description");
            int calories = Integer.parseInt(req.getParameter("calories"));
            if (id == 0) {
                mealService.saveToRepo(new Meal(dateTime, description, calories));
            } else {
                mealService.updateInRepo(new Meal(id, dateTime, description, calories));
            }
        }
        resp.sendRedirect("meals");
    }

    private int getId(HttpServletRequest req) {
        String param = req.getParameter("id");
        return "".equals(param) ? 0 : Integer.parseInt(param);
    }

    private Meal getEmptyMeal() {
        return new Meal(0, LocalDateTime.now(), "", 0);
    }
}
