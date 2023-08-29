package com.example.dooff.web.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryRequest {

  List<Pair<String,Double>> foodOnAmount;

}
