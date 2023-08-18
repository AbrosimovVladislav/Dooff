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
        .proteins(Math.round(dish.getProteins() * 100.0) / 100.0)
        .fats(Math.round(dish.getFats() * 100.0) / 100.0)
        .carbohydrates(Math.round(dish.getCarbohydrates() * 100.0) / 100.0)
        .calories(dish.getCalories())
        .ingredients(dish.getDishIngredients().stream()
            .map(e -> Pair.of(e.getIngredient().getName(), e.getWeight())).toList())
        .type(type(dish.getType()))
        .build();
  }

  private String type(Integer typeId) {
    return switch (typeId) {
      case 1 -> "Обед";
      case 2 -> "Ужин";
      case 3 -> "Десерт";
      default -> "Обед";
    };
  }

}
