package com.example.dooff.model;

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
public class DishEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long dishId;

  private String name;

  private Double proteins;
  private Double fats;
  private Double carbohydrates;
  private Integer calories;

  private Integer type;

  private String comments;

  @OneToMany(mappedBy = "dish")
  Set<DishIngredientEntity> dishIngredients;


}
