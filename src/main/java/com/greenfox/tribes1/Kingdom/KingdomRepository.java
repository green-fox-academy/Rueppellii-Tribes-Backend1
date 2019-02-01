package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KingdomRepository extends JpaRepository<Kingdom,Long> {
  Kingdom findKingdomByApplicationUser(ApplicationUser applicationUser);

  Kingdom findKingdomByApplicationUserName(String applicationUserName);
}
