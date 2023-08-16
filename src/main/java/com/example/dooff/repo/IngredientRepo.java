package com.example.dooff.repo;

import com.example.dooff.model.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<IngredientEntity, Long> {

}
