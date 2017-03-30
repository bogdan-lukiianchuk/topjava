package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private MealService service = new MealServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug(request.getServletPath());
        switch (request.getServletPath()) {
            case "/meals":
                List<MealWithExceed> meals = service.getAll();
                request.setAttribute("meals", meals);
                LOG.debug("redirect to meals");
                //response.sendRedirect("meals.jsp");
                request.getRequestDispatcher("meals.jsp").forward(request, response);
                break;
            case "/mealsEditable":
                if("edit".equals(request.getParameter("action"))) {
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Meal meal = service.get(id);
                        LOG.debug(meal.toString());
                        request.setAttribute("id", meal.getId());
                        request.setAttribute("date", meal.getDate());
                        request.setAttribute("time", meal.getTime());
                        request.setAttribute("description", meal.getDescription());
                        request.setAttribute("calories", meal.getCalories());
                        request.getRequestDispatcher("mealUpsert.jsp").forward(request,response);
                        return;
                    } catch (Exception e) {
                        //do nothing
                    }
                }
                if("delete".equals(request.getParameter("action"))) {
                    try {
                        LOG.debug(request.getParameter("id"));
                        int id = Integer.parseInt(request.getParameter("id"));
                        service.delete(id);
                    } catch (Exception e) {
                        //do nothing
                    }
                }
                meals = service.getAll();
                request.setAttribute("meals", meals);
                LOG.debug("redirect to mealsEditable");
                request.getRequestDispatcher("mealsEditable.jsp").forward(request, response);
                break;
        }
    }
}
