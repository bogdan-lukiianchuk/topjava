package ru.javawebinar.topjava.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = Meal.ALL_SORTED_FOR_USER, query = "SELECT m FROM Meal m WHERE m.user.id=:user_id ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.GET_FOR_USER, query = "SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = Meal.UPDATE_FOR_USER, query = "UPDATE Meal" +
                " SET dateTime=:date_time, description=:description, calories=:calories" +
                " WHERE id=:id AND user.id=:user_id"),
        @NamedQuery(name = Meal.BETWEEN_DATES_SORTED_FOR_USER, query = "SELECT m FROM Meal m" +
                " WHERE m.user.id=:user_id AND m.dateTime BETWEEN :startDate AND :endDate" +
                " ORDER BY m.dateTime DESC")
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"})})
public class Meal extends BaseEntity {
    public static final String DELETE = "Meal.delete";
    public static final String ALL_SORTED_FOR_USER = "Meal.getAllSortedForUser";
    public static final String GET_FOR_USER = "Meal.getForUser";
    public static final String UPDATE_FOR_USER = "Meal.updateForUser";
    public static final String BETWEEN_DATES_SORTED_FOR_USER = "Meal.betweenDatesSortedForUser";

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "calories", nullable = false)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" )
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
