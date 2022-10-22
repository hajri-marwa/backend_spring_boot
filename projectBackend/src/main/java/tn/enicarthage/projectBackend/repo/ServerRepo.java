package tn.enicarthage.projectBackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.projectBackend.model.Server;

public interface ServerRepo extends JpaRepository<Server,Long> {
    Server findByIpAddress(String ipAddress);//select a server comparing the ip address with the one in parameter
}
