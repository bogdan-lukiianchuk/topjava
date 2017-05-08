package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
//    @Modifying
//    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
//    int delete(@Param("id") int id, @Param("userId") int userId);
    int deleteByIdAndUserId(int id, int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Meal m" +
            " SET m.dateTime=:dateTime, m.description=:description, m.calories=:calories" +
            " WHERE m.id=:id AND m.user.id=:userId")
    Meal save(@Param("id") int id,
              @Param("userId") int userId,
              @Param("dateTime")LocalDateTime dateTime,
              @Param("description") String description,
              @Param("calories") int calories);

    Meal findByIdAndUserId(int id, int userId);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int userId);

    List<Meal> findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime start, LocalDateTime end);

}
