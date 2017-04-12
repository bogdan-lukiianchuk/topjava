package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(USER_MEAL_1.getId(), USER_ID);
        MATCHER.assertEquals(meal, USER_MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNonExisting() throws Exception {
        service.get(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getAnothersMeal() throws Exception {
        service.get(ADMIN_MEAL_1.getId(), USER_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(ADMIN_MEAL_1.getId(), ADMIN_ID);
        MATCHER.assertCollectionEquals(
                service.getAll(ADMIN_ID),
                Collections.singletonList(ADMIN_MEAL_2)
        );
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(1, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAnothersMeal() throws Exception {
        service.delete(USER_MEAL_1.getId(), ADMIN_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(
                Collections.emptyList(),
                service.getBetweenDates(
                        LocalDate.of(2016, 1, 1),
                        LocalDate.of(2017, 1, 1),
                        USER_ID
                )
        );
        MATCHER.assertCollectionEquals(
                Arrays.asList(
                        USER_MEAL_3,
                        USER_MEAL_2,
                        USER_MEAL_1
                ),
                service.getBetweenDates(
                        USER_MEAL_1.getDate(),
                        USER_MEAL_3.getDate(),
                        USER_ID
                )
        );
        MATCHER.assertCollectionEquals(
                Collections.emptyList(),
                service.getBetweenDates(
                        service.getAll(USER_ID).stream()
                                .map(Meal::getDate)
                                .max(Comparator.naturalOrder())
                                .get()
                                .plusDays(1),
                        LocalDate.of(3000, 1, 1),
                        USER_ID
                )
        );

    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(
                Collections.emptyList(),
                service.getBetweenDateTimes(
                        LocalDate.of(2016, 1, 1).atStartOfDay(),
                        LocalDate.of(2017, 1, 1).atStartOfDay(),
                        USER_ID
                )
        );
        MATCHER.assertCollectionEquals(
                Arrays.asList(
                        USER_MEAL_3,
                        USER_MEAL_2,
                        USER_MEAL_1
                ),
                service.getBetweenDateTimes(
                        USER_MEAL_1.getDateTime(),
                        USER_MEAL_3.getDateTime(),
                        USER_ID
                )
        );
        MATCHER.assertCollectionEquals(
                Collections.emptyList(),
                service.getBetweenDateTimes(
                        service.getAll(USER_ID).stream()
                                .map(Meal::getDateTime)
                                .max(Comparator.naturalOrder())
                                .get()
                                .plusMinutes(1),
                        LocalDate.of(3000, 1, 1).atStartOfDay(),
                        USER_ID
                )
        );
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(
                Arrays.asList(
                        USER_MEAL_6,
                        USER_MEAL_5,
                        USER_MEAL_4,
                        USER_MEAL_3,
                        USER_MEAL_2,
                        USER_MEAL_1
                ),
                service.getAll(USER_ID)
        );
        MATCHER.assertCollectionEquals(
                Arrays.asList(
                        ADMIN_MEAL_2,
                        ADMIN_MEAL_1
                ),
                service.getAll(ADMIN_ID)
        );
        MATCHER.assertCollectionEquals(
                Collections.emptyList(),
                service.getAll(1)
        );
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(
                USER_MEAL_1.getId(),
                LocalDate.of(2017,1,1).atStartOfDay(),
                "updated",
                300
        );
        service.update(updated, USER_ID);
        MATCHER.assertEquals(
                updated,
                service.get(USER_MEAL_1.getId(), USER_ID)
        );
    }

    @Test(expected = NotFoundException.class)
    public void updateAnothersMeal() {
        Meal updated = new Meal(
                ADMIN_MEAL_1.getId(),
                LocalDate.of(2017,1,1).atStartOfDay(),
                "updated",
                300
        );
        MATCHER.assertEquals(null, service.update(updated, USER_ID));
        MATCHER.assertEquals(ADMIN_MEAL_1, service.get(ADMIN_MEAL_1.getId(), ADMIN_ID));
    }

    @Test
    public void save() throws Exception {
        Meal newTestMeal = new Meal(LocalDateTime.now(), "NewTestMeal", 1000);
        Meal created = service.save(newTestMeal, ADMIN_ID);
        newTestMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(
                service.getAll(ADMIN_ID),
                Arrays.asList(created, ADMIN_MEAL_2, ADMIN_MEAL_1)
                );
    }

}