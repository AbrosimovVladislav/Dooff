package com.example.dooff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class DishIngredientEntity {

  @Id
  private String dishIngredientId;

  private Integer weight;

  @ManyToOne
  @JoinColumn(name = "ingredient_id")
  IngredientEntity ingredient;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "dish_id")
  DishEntity dish;

}
