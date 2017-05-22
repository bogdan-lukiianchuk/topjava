package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/meals")
public class AjaxController extends AbstractMealController {
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    public Meal get(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createM(
            @RequestParam("id") Integer id,
            @RequestParam("dateTime")LocalDateTime dateTime,
            @RequestParam("description") String description,
            @RequestParam("calories") int calories
            ) {
        Meal meal = new Meal(id, dateTime, description, calories);
        super.create(meal);
    }

    @Override
    public void update(Meal meal, int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        throw new UnsupportedOperationException();
    }
}
