package com.greenfox.tribes1.kingdomelement.Building;

import com.google.common.collect.Iterables;
import com.greenfox.tribes1.kingdomelement.Resources.Food;
import com.greenfox.tribes1.kingdomelement.Resources.Resource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Farm")
@DiscriminatorValue("Farm")
@Getter
@Setter
public class Farm extends Building {

  public Farm() {
    this.setLevel(1L);
    this.setHP(150.0f);
  }

  @Override
  public void buildingUpgrade() {
    increaseLevel();
    increaseFoodAmount();

  }

  private void increaseFoodAmount() {
    List<Resource> resources = getResourceList();
    Food food = getFood(resources);
    food.setAmountPerMinute(food.getAmountPerMinute() + 5L);

  }

  private List<Resource> getResourceList() {
    List<Resource> resourceList = getKingdom().getResources();

    return resourceList.stream()
            .filter(r -> r instanceof Food)
            .collect(Collectors.toList());

  }

  private Food getFood(List<Resource> resourceList) {

    return (Food) Iterables
            .getOnlyElement(resourceList);
  }

}
