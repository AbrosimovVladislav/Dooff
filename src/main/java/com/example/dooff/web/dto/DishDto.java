package com.example.dooff.web.dto;

import com.example.dooff.model.DishIngredientEntity;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishDto {

  private Long dishId;

  private String name;

  private Double proteins;
  private Double fats;
  private Double carbohydrates;
  private Integer calories;

  Set<DishIngredientEntity> dishIngredients;

}
