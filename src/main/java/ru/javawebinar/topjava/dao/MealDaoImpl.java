package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao{
    AtomicInteger count = new AtomicInteger(0);
    private Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    @Override
    public void add(Meal meal) {
        meal.setId(count.incrementAndGet());
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public void delete(Integer mealId) {
        mealMap.remove(mealId);
    }

    @Override
    public Meal update(Meal meal) {
        //as meal is backed by memory, it's already updated
        return meal;
    }

    @Override
    public Meal get(Integer mealId) {
        return mealMap.get(mealId);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealMap.values());
    }
}
