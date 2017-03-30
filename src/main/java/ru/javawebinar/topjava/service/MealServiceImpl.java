package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.List;

public class MealServiceImpl implements MealService {
    private MealDao mealDao = new MealDaoImpl();

    @Override
    public void updateOrInsert(Integer id, LocalDateTime dateTime, String description, int calories) {
        if (null == id) {
            mealDao.add(new Meal(0, dateTime, description, calories));
        } else {
            mealDao.update(new Meal(id, dateTime, description, calories));
        }
    }

    @Override
    public void delete(Integer id) {
        mealDao.delete(id);
    }

    @Override
    public Meal get(Integer id) {
        return mealDao.get(id);
    }

    @Override
    public List<MealWithExceed> getAll() {
        return MealsUtil.getAllWithExceeded(mealDao.getAll(), 2000);
    }
}
