package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealDaoImpl implements MealDao{
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    static {
        LocalDate d1 = LocalDate.of(2017, 3, 1);
        LocalDate d2 = LocalDate.of(2017, 3, 2);
        LocalDate d3 = LocalDate.of(2017, 3, 3);
        LocalTime t10 = LocalTime.of(10, 0);
        LocalTime t14 = LocalTime.of(14, 0);
        LocalTime t18 = LocalTime.of(18, 0);
        mealMap.put(-1, new Meal(-1, LocalDateTime.of(d1, t10), "Завтрак", 500));
        mealMap.put(-2, new Meal(-2, LocalDateTime.of(d2, t10), "Завтрак", 1000));
        mealMap.put(-3, new Meal(-3, LocalDateTime.of(d3, t10), "Завтрак", 1000));
        mealMap.put(-4, new Meal(-4, LocalDateTime.of(d1, t14), "Обед", 1000));
        mealMap.put(-5, new Meal(-5, LocalDateTime.of(d3, t14), "Обед", 1000));
        mealMap.put(-6, new Meal(-6, LocalDateTime.of(d1, t18), "Ужин", 500));
        mealMap.put(-7, new Meal(-7, LocalDateTime.of(d2, t18), "Ужин", 1000));
        mealMap.put(-8, new Meal(-8, LocalDateTime.of(d3, t18), "Ужин", 1000));

    }

    @Override
    public void add(Meal meal) {
        meal.setId(count.incrementAndGet());
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public Meal update(Meal meal) {
        return mealMap.replace(meal.getId(), meal) == null ? null : meal;
    }

    @Override
    public void delete(Integer id) {
        mealMap.remove(id);
    }

    @Override
    public Meal get(Integer id) {
        return mealMap.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return mealMap.values().stream().collect(Collectors.toList());
    }
}
