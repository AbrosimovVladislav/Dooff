package com.example.dooff.web;

import com.example.dooff.mapper.DishMapper;
import com.example.dooff.service.DishService;
import com.example.dooff.web.dto.DishDto;
import com.example.dooff.web.dto.SummaryDto;
import com.example.dooff.web.request.CreateDishRequest;
import com.example.dooff.web.request.SummaryRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/basic")
public class BasicController {

  private final DishService dishService;
  private final DishMapper dishMapper;

  @CrossOrigin
  @PostMapping("/summary")
  public ResponseEntity<SummaryDto> getSummary(@RequestBody SummaryRequest request) {
    Pair<Double, Double> summaryInfo = dishService.summary(request.getFoodOnAmount());
    return ResponseEntity.ok(SummaryDto.builder()
        .proteins(summaryInfo.getFirst())
        .calories(summaryInfo.getSecond())
        .build());
  }

  @CrossOrigin
  @GetMapping
  public ResponseEntity<List<DishDto>> getAllDishes() {
    var dishDtos = dishMapper.toDtos(dishService.findAll());
    return ResponseEntity.ok(dishDtos);
  }

  @GetMapping("/recalculate")
  public ResponseEntity<List<DishDto>> recalculateAll() {
    var dishDtos = dishMapper.toDtos(dishService.recalculateAllDishesPFC());
    return ResponseEntity.ok(dishDtos);
  }

  @CrossOrigin
  @GetMapping("/{name}")
  public ResponseEntity<DishDto> getByName(@PathVariable String name) {
    var dishDto = dishMapper.toDto(dishService.getByName(name));
    return ResponseEntity.ok(dishDto);
  }

  @PostMapping("/create")
  public ResponseEntity<DishDto> create(@RequestBody CreateDishRequest request) {
    var dishDto = dishMapper.toDto(dishService.create(request));
    return ResponseEntity.ok(dishDto);
  }

}
