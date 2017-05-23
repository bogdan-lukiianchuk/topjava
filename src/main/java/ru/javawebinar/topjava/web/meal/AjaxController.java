package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/meals")
//@SessionAttributes({"startDate", "startTime", "endDate", "endTime"})
public class AjaxController extends AbstractMealController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll(HttpServletRequest request) {
//        return super.getAll();
        return super.getBetween(
                (LocalDate) request.getSession().getAttribute("startDate"),
                (LocalTime) request.getSession().getAttribute("startTime"),
                (LocalDate) request.getSession().getAttribute("endDate"),
                (LocalTime) request.getSession().getAttribute("endTime")
                );
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

    @PostMapping(value = "/filter")
    public void filter(HttpServletRequest request,
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        request.getSession().setAttribute("startDate", startDate);
        request.getSession().setAttribute("startTime", startTime);
        request.getSession().setAttribute("endDate", endDate);
        request.getSession().setAttribute("endTime", endTime);
    }

    @PostMapping(value = "/filterClear")
    public void filter(HttpServletRequest request) {
        request.getSession().setAttribute("startDate", null);
        request.getSession().setAttribute("startTime", null);
        request.getSession().setAttribute("endDate", null);
        request.getSession().setAttribute("endTime", null);
    }

}
