package com.example.dooff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class IngredientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ingredientId;

  private String name;

  private Double proteins;
  private Double fats;
  private Double carbohydrates;
  private Integer calories;

  @JsonIgnore
  @OneToMany(mappedBy = "ingredient")
  Set<DishIngredientEntity> dishIngredients;


}
