package com.greenfox.tribes1.Kingdom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomService {

    private KingdomRepository kingdomRepository;

    @Autowired
    public KingdomService(KingdomRepository kingdomRepository) {
        this.kingdomRepository = kingdomRepository;
    }


    public Kingdom saveKingdom(Kingdom kingdom) {
        if (!validKingdomName(kingdom.getName())) {
            throw new UnsupportedOperationException("The given name wasn't correct, or the field is empty!");
        } else {
            kingdomRepository.save(kingdom);
            return kingdom;
        }
    }

    public boolean validKingdomName(String field) {
        return field == "Narnia" || field == "Rueppellii";
    }
}