package com.greenfox.tribes1.Progression.DTO;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgressionDTO {

  private Long model_id;
  private Long level;
  private String type; //VARCHAR(255)
  private Kingdom kingdom;
}
