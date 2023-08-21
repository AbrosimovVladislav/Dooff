package com.example.dooff.web.request;

import com.example.dooff.web.dto.DishIngredientDto;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateDishRequest {

  private String name;
  private Integer type;
  List<DishIngredientDto> ingredients;

}

