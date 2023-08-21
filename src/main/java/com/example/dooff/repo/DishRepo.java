package com.example.dooff.repo;

import com.example.dooff.model.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DishRepo extends JpaRepository<DishEntity, Long> {

  DishEntity findByName(String name);

  @Query(
      value = "SELECT d.dish_id FROM dish_entity d ORDER BY dish_id DESC LIMIT 1",
      nativeQuery = true)
  Long getMaxId();
}
