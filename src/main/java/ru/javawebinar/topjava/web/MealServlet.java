package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.repo.MealRepoByList;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealServlet extends HttpServlet {
    private final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private MealService mealService;

    public void init() {
        LOG.debug("mealservlet created");
        this.mealService = new MealServiceImpl(new MealRepoByList());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("/meal accessed");
        int caloriesDayLimit = 2000;
        req.setAttribute("mealList", mealService.findAllWithExcesses(caloriesDayLimit));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
