package tn.enicarthage.projectBackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.projectBackend.model.Role;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
