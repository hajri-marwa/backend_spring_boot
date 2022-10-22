package tn.enicarthage.projectBackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.projectBackend.model.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
