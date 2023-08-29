package com.example.dooff.service;

import com.example.dooff.model.DishEntity;
import com.example.dooff.model.DishIngredientEntity;
import com.example.dooff.repo.DishIngredientRepo;
import com.example.dooff.repo.DishRepo;
import com.example.dooff.repo.IngredientRepo;
import com.example.dooff.web.request.CreateDishRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DishService {

  private final DishRepo dishRepo;
  private final DishIngredientRepo dishIngredientRepo;
  private final IngredientRepo ingredientRepo;

  public List<DishEntity> findAll() {
    return dishRepo.findAll();
  }

  public Pair<Double, Double> summary(List<Pair<String, Double>> summaryRequest) {
    Double proteins = 0.0;
    Double calories = 0.0;
    var info = summaryRequest.stream()
        .map(e -> Pair.of(dishRepo.findByName(e.getFirst()), e.getSecond()))
        .map(e -> Pair.of(
            e.getFirst().getProteins() * e.getSecond(),
            e.getFirst().getCalories() * e.getSecond()))
        .toList();

    for (var infoItem : info) {
      proteins += infoItem.getFirst();
      calories += infoItem.getSecond();
    }
    return Pair.of(proteins, calories);
  }

  public List<DishEntity> recalculateAllDishesPFC() {
    var dishes = findAll();
    var recalculatedDishes = dishes.stream().map(this::recalculatePFC).toList();
    return dishRepo.saveAll(recalculatedDishes);
  }

  private DishEntity recalculatePFC(DishEntity dish) {
    Set<DishIngredientEntity> dishIngredients = dish.getDishIngredients();
    List<Map<String, Number>> pfcs = dishIngredients.stream()
        .map(ingredient -> {
          var ingredParams = ingredient.getIngredient();
          double proteins = ingredParams.getProteins() * ingredient.getWeight() / 100;
          double fats = ingredParams.getFats() * ingredient.getWeight() / 100;
          double carbohydrates = ingredParams.getCarbohydrates() * ingredient.getWeight() / 100;
          int calories = ingredParams.getCalories() * ingredient.getWeight() / 100;

          Map<String, Number> map = new HashMap<>();
          map.put("proteins", proteins);
          map.put("fats", fats);
          map.put("carbohydrates", carbohydrates);
          map.put("calories", calories);

          return map;
        })
        .toList();
    var summarizedPFC = summPFC(pfcs);
    dish.setProteins((Double) summarizedPFC.get("proteins"));
    dish.setCalories((Integer) summarizedPFC.get("calories"));
    dish.setFats((Double) summarizedPFC.get("fats"));
    dish.setCarbohydrates((Double) summarizedPFC.get("carbohydrates"));
    return dish;
  }

  private Map<String, Number> summPFC(List<Map<String, Number>> pfcs) {
    Double proteins = 0.0;
    Double fats = 0.0;
    Double carbohydrates = 0.0;
    Integer calories = 0;

    for (var pfc : pfcs) {
      proteins = proteins + (Double) pfc.get("proteins");
      fats = fats + (Double) pfc.get("fats");
      carbohydrates = carbohydrates + (Double) pfc.get("carbohydrates");
      calories = calories + (Integer) pfc.get("calories");
    }

    return Map.of("proteins", proteins, "fats", fats, "carbohydrates", carbohydrates, "calories",
        calories);
  }

  public DishEntity getByName(String name) {
    return dishRepo.findByName(name);
  }

  @Transactional
  public DishEntity create(CreateDishRequest request) {
    DishEntity dish = new DishEntity()
        .setName(request.getName())
        .setType(request.getType())
        .setDishId(dishRepo.getMaxId() + 1);
    DishEntity saved = dishRepo.save(dish);

    request.getIngredients().stream()
        .map(e -> {
          var ingredient = ingredientRepo.findByName(e.getName());
          return new DishIngredientEntity()
              .setDishIngredientId(dish.getDishId() + ":" + ingredient.getIngredientId())
              .setDish(dish)
              .setWeight(e.getWeight())
              .setIngredient(ingredient);
        }).forEach(dishIngredientRepo::save);

    return dishRepo.findById(saved.getDishId())
        .orElseThrow(() -> new RuntimeException("There is no dish with id:" + saved.getDishId()));
  }
}
