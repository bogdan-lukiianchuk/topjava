package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    //private MealRepository repository;
    private MealRestController mealRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//        repository = new InMemoryMealRepositoryImpl();
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealRestController=appCtx.getBean(MealRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Meal meal = new Meal(AuthorizedUser.id(),
                id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        if (meal.isNew()) {
            mealRestController.create(meal, AuthorizedUser.id());
        } else {
            mealRestController.update(meal, AuthorizedUser.id());
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            int userId = AuthorizedUser.id();

            switch (action == null ? "all" : action) {
                case "delete":
                    int id = getId(request);
                    LOG.info("Delete {}", id, userId);
                    mealRestController.delete(id, userId);
                    response.sendRedirect("meals");
                    break;
                case "create":
                case "update":
                    final Meal meal = action.equals("create") ?
                            new Meal(userId, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                            mealRestController.get(getId(request), userId);
                    request.setAttribute("meal", meal);
                    request.getRequestDispatcher("/meal.jsp").forward(request, response);
                    break;
                case "all":
                default:
                    LOG.info("getAll");
                    request.setAttribute("meals",
                            MealsUtil.getWithExceeded(mealRestController.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
                    break;
            }

        } catch (Exception e) {
            //todo
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

}