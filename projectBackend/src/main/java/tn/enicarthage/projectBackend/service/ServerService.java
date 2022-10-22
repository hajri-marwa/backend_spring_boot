package tn.enicarthage.projectBackend.service;

import tn.enicarthage.projectBackend.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {
    Server Create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);//limit the number of the servers to return while calling this method
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);

}
