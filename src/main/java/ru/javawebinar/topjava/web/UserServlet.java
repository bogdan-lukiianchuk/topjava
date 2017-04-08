package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);
    @Autowired
    private AdminRestController userController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        userController=appCtx.getBean(AdminRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("doPost request = ", request.getRequestURI());
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        boolean isAdmin = "on".equals(request.getParameter("admin"));
        //todo check fields for non Null and non empty

        User user = isAdmin ?
                new User(id.isEmpty() ? null : Integer.parseInt(id),
                        name,
                        email,
                        pass,
                        Role.ROLE_USER,
                        Role.ROLE_ADMIN
                ) :
                new User(id.isEmpty() ? null : Integer.parseInt(id),
                        name,
                        email,
                        pass,
                        Role.ROLE_USER
                );

        LOG.info(user.isNew() ? "Create {}" : "Update {}", user);
        if (user.isNew()) {
            userController.create(user);
        } else {
            userController.update(user, AuthorizedUser.id());
        }
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                LOG.info("Delete {}", id);
                userController.delete(id);
                response.sendRedirect("users");
                break;
            case "authorize":
                AuthorizedUser.setId(getId(request));
                LOG.info("Authorized as ", userController.get(AuthorizedUser.id()));
                response.sendRedirect("meals?action=all");
                break;
            case "create":
            case "update":
                final User user = action.equals("create") ?
                        new User() :
                        userController.get(getId(request));
                request.setAttribute("user", user);
                request.getRequestDispatcher("/user.jsp").forward(request, response);
                break;
            case "all":
            default:
                LOG.info("getAll");
                request.setAttribute("users",
                        userController.getAll());
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
