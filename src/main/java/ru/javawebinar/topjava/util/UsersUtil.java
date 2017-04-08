package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(-1, "Vasya", "vasya@mail.ru", "123456", Role.ROLE_USER),
            new User(-2, "Bogdan", "bogdan@gmail.com", "password", Role.ROLE_USER, Role.ROLE_ADMIN),
            new User(-3, "Dima", "dima@yandex.ru", "dima1", Role.ROLE_USER)
    );

}
