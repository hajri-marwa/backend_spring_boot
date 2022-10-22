package tn.enicarthage.projectBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.projectBackend.model.Response;
import tn.enicarthage.projectBackend.model.Server;
import tn.enicarthage.projectBackend.service.implemetation.ServerServiceImpl;


import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static tn.enicarthage.projectBackend.enumeration.Status.SERVER_UP;


@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerServiceImpl serverService;


    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("servers",serverService.list(30)))
                        .message("servers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server",server))
                        .message(server.getStatus() == SERVER_UP ? "ping success" : "ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server",serverService.Create(server)))
                        .message( "server created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server",serverService.get(id)))
                        .message( "server retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deleted",serverService.delete(id)))
                        .message( "server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping(path = "/image/{fileName}",produces = IMAGE_PNG_VALUE )//gonna return a png type not json
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
       // System.out.println(Files.readAllBytes(Paths.get(System.getProperty("user.home")+"\\Downloads\\images\\"+fileName))) ;
        return Files.readAllBytes(Paths.get("C:/Users/PC/Downloads/images/"+fileName));
        //System.getProperty("user.home")+"/Downloads/images/"+fileName)
    }
}


