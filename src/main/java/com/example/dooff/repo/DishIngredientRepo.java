package com.example.dooff.repo;

import com.example.dooff.model.DishIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishIngredientRepo extends JpaRepository<DishIngredientEntity, String> {

}
