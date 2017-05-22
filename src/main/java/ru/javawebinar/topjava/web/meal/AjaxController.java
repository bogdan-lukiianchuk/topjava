package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/meals")
@SessionAttributes({"startDate", "startTime", "endDate", "endTime"})
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
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
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
    public List<MealWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("startDate", startDate);
        modelAndView.addObject("startTime", startDate);
        modelAndView.addObject("endDate", startDate);
        modelAndView.addObject("endTime", startDate);
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
