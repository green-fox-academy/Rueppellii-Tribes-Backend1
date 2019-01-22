package com.greenfox.tribes1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {

    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public ApplicationUser findByUsername(String username) {
        if (applicationUserRepository.findByUsername(username).isPresent()) {
            return applicationUserRepository.findByUsername(username).get();
        } else return null;
    }

//    @Krisz! itt eredetileg szét volt válogatva a mezők kitöltése meg a szerepel-e már adatbázisban, és dobott hibát is
//      ehhez tartozik a NotProvidedAdvice és az ErrorMessage class - használjuk a refaktorálásnál, ezeket is ha kell
    public ApplicationUser saveUserIfValid(ApplicationUser applicationUser) {
        if (findByUsername(applicationUser.getUsername()) != null ||
                applicationUser.getUsername().isEmpty() ||
                applicationUser.getPassword().isEmpty()){
            return null;
        } else {
            saveUser(applicationUser);
            return applicationUser;
        }
    }

    public void saveUser(ApplicationUser applicationUser) {
            applicationUserRepository.save(applicationUser);
    }


}
