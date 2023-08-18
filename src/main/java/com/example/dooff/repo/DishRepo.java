package com.example.dooff.repo;

import com.example.dooff.model.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepo extends JpaRepository<DishEntity, Long> {

  DishEntity findByName(String name);
}
