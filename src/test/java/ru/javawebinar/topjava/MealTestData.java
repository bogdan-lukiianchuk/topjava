package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.sql.Timestamp;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {
    public static final Meal USER_MEAL_1 = TestMealCreator.getMeal(START_SEQ + 2, Timestamp.valueOf("2017-03-10 10:00:00"), "Завтрак", 400);
    public static final Meal USER_MEAL_2 = TestMealCreator.getMeal(START_SEQ + 3, Timestamp.valueOf("2017-03-10 14:00:00"), "Обед", 800);
    public static final Meal USER_MEAL_3 = TestMealCreator.getMeal(START_SEQ + 4, Timestamp.valueOf("2017-03-10 19:00:00"), "Ужин", 1200);
    public static final Meal USER_MEAL_4 = TestMealCreator.getMeal(START_SEQ + 5, Timestamp.valueOf("2017-03-11 10:00:00"), "Завтрак", 400);
    public static final Meal USER_MEAL_5 = TestMealCreator.getMeal(START_SEQ + 6, Timestamp.valueOf("2017-03-11 14:00:00"), "Обед", 800);
    public static final Meal USER_MEAL_6 = TestMealCreator.getMeal(START_SEQ + 7, Timestamp.valueOf("2017-03-11 19:00:00"), "Ужин", 600);
    public static final Meal ADMIN_MEAL_1 = TestMealCreator.getMeal(START_SEQ + 8, Timestamp.valueOf("2017-03-10 10:00:00"), "Завтрак", 1600);
    public static final Meal ADMIN_MEAL_2 = TestMealCreator.getMeal(START_SEQ + 9, Timestamp.valueOf("2017-03-11 14:00:00"), "Обед", 2001);

    private static class TestMealCreator {
        public static Meal getMeal(Integer id, Timestamp timestamp, String description, int calories) {
            return new Meal(id, timestamp.toLocalDateTime(), description, calories);
        }

    }

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual || (
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
            )
    );



}
