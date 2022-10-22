package tn.enicarthage.projectBackend.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.enicarthage.projectBackend.model.AppUser;
import tn.enicarthage.projectBackend.model.Role;
import tn.enicarthage.projectBackend.service.AppUserService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppUserController {
    private final AppUserService appUserService;


    @GetMapping("/users")
    public ResponseEntity<List<AppUser>>getAppUsers() {
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }
    @PostMapping("/user/save")
    public ResponseEntity<AppUser>saveUsers(@RequestBody AppUser appUser) {
        URI uri =  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveUser(appUser));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri =  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
        appUserService.addRoleToAppUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }
}
@Data
class RoleToUserForm{
    private String username;
    private String roleName;

}
