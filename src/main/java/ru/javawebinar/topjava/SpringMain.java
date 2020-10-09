package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            MealRestController controller = appCtx.getBean(MealRestController.class);
            //todo check npe filtering
            LocalDate startDate = LocalDate.of(2020, 12, 30);
            LocalDate endDate = LocalDate.of(2020, 12, 30);
            LocalTime startTime = LocalTime.of(9, 0, 0);
            LocalTime endTime = LocalTime.of(12, 0, 0);
            print(controller.getAll(startDate, endDate, null, null));
        }

    }
    private static void print(List<MealTo> list) {
        System.out.println("---------------");
        list.forEach(mealTo -> {
            System.out.println("id: " + mealTo.getId());
            System.out.println("dateTime: " + mealTo.getDateTime());
            System.out.println("name: " + mealTo.getDescription());
            System.out.println("calories: " + mealTo.getCalories());
        });
        System.out.println("--------------");
    }
}
