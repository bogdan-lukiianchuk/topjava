package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

public interface MealService {
    void updateOrInsert(Integer id, LocalDateTime dateTime, String description, int calories);

    void delete(Integer id);

    Meal get(Integer id);

    List<MealWithExceed> getAll();
}
