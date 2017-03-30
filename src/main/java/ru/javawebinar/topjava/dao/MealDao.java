package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    void add(Meal meal);

    void delete(Integer id);

    Meal update(Meal meal);

    Meal get(Integer id);

    List<Meal> getAll();
}
