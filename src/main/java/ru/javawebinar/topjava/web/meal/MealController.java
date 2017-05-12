package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
public class MealController extends MealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(MealController.class);

    @Autowired
    public MealController(MealService service) {
        super(service);
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String meals(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @RequestMapping(value = "/meals/filter", method = RequestMethod.POST)
    public String filter(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            Model model) {
        model.addAttribute(
                "meals",
                getBetween(
                        DateTimeUtil.parseLocalDate(startDate),
                        DateTimeUtil.parseLocalTime(startTime),
                        DateTimeUtil.parseLocalDate(endDate),
                        DateTimeUtil.parseLocalTime(endTime)
                ));
        return "meals";
    }

    @RequestMapping(value = "/meals/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable("id") int id) {
        delete(id);
        return "redirect:/meals";
    }

    @RequestMapping(value = "/meals/update/{id}", name = "meal")
    public String update(Model model, @PathVariable("id") int id) {
        final Meal meal = get(id);
        model.addAttribute("meal", meal);
        return "meal";
    }

    @RequestMapping(value = "/meals/create", name = "meal")
    public String create(Model model) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "meal";
    }


    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String upsert(
            @RequestParam("id") Integer id,
            @RequestParam("dateTime") String dateTime,
            @RequestParam("description") String description,
            @RequestParam("calories") int calories,
            Model model
            ) {
        final Meal meal = new Meal(
                LocalDateTime.parse(dateTime),
                description,
                calories
        );
        if (Objects.isNull(id)) {
            create(meal);
        } else {
            update(meal, id);
        }
        return "redirect:/meals";
    }

//    @RequestMapping(v)

//    @RequestMapping(value = "/meal")
}
