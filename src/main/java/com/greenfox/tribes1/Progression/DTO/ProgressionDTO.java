package com.greenfox.tribes1.Progression.DTO;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressionDTO {
  private Long model_id;
  private Long level;
  private String type;
  private Kingdom kingdom;
}
