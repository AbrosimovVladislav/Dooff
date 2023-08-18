package com.example.dooff.mapper;

import com.example.dooff.model.DishEntity;
import com.example.dooff.web.dto.DishDto;
import java.util.List;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

  public List<DishDto> toDtos(List<DishEntity> dishes) {
    return dishes.stream().map(this::toDto).toList();
  }

  public DishDto toDto(DishEntity dish) {
    return DishDto.builder()
        .dishId(dish.getDishId())
        .name(dish.getName())
        .calories(dish.getCalories())
        .ingredients(dish.getDishIngredients().stream().map(e -> Pair.of(e.getIngredient().getName(), e.getWeight())).toList())
        .carbohydrates(dish.getCarbohydrates())
        .proteins(dish.getProteins())
        .fats(dish.getFats())
        .build();
  }

}
