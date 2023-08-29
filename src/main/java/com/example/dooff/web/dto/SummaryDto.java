package com.example.dooff.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SummaryDto {

  private Double proteins;
  private Double calories;

}
