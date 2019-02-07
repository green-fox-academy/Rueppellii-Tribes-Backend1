package com.greenfox.tribes1.Resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="Gold")
@Getter
@Setter
class Gold extends KingdomResource {
}
