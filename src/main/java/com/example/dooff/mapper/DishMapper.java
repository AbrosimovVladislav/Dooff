package com.example.dooff.mapper;

import com.example.dooff.model.DishEntity;
import com.example.dooff.web.dto.DishDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

  public List<DishDto> toDtos(List<DishEntity> dishes) {
    return dishes.stream().map(this::toDto).toList();
  }

  private DishDto toDto(DishEntity dish) {
    return DishDto.builder()
        .dishId(dish.getDishId())
        .name(dish.getName())
        .calories(dish.getCalories())
        .dishIngredients(dish.getDishIngredients())
        .carbohydrates(dish.getCarbohydrates())
        .proteins(dish.getProteins())
        .fats(dish.getFats())
        .build();
  }

}
