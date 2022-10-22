package tn.enicarthage.projectBackend.service.implemetation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.enicarthage.projectBackend.enumeration.Status;
import tn.enicarthage.projectBackend.model.Server;
import tn.enicarthage.projectBackend.repo.ServerRepo;
import tn.enicarthage.projectBackend.service.ServerService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;


//dependency injection
@Service
@Transactional
@Slf4j//lombok log in the console
@RequiredArgsConstructor//creates a constructor an puts the serverRepo field into it
public class ServerServiceImpl  implements ServerService {

    private final ServerRepo serverRepo;
    @Override
    public Server Create(Server server) {
        log.info("saving new server: {}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    //picks a random image from  4 and assign it to imageurl
    private String setServerImageUrl() {
        String[] imageNames = { "server1.png", "server2.png", "server3.png", "server4.png"};
        System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/server1.png").toUriString());
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/server2.png").toUriString();
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("pinging server IP: {}",ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);//gives us the Inet address to check the server's status
        server.setStatus(address.isReachable(10000)? Status.SERVER_UP:Status.SERVER_DOWN);//if we reach the server within this timeout it's status would be up
        serverRepo.save(server);
        return server;
    }

    //in case of having a lot of servers it's better to set a limit number
    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching all servers");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();//page 0 and the limit number nto a list


    }

    @Override
    public Server get(Long id) {
        log.info("server by id: {}",id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("saving server: {}",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("deleting server by id: {}",id);
        serverRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
