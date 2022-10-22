package tn.enicarthage.projectBackend.service.implemetation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.enicarthage.projectBackend.model.AppUser;
import tn.enicarthage.projectBackend.model.Role;
import tn.enicarthage.projectBackend.repo.AppUserRepo;
import tn.enicarthage.projectBackend.repo.RoleRepo;
import tn.enicarthage.projectBackend.service.AppUserService;


import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;





    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("Saving new user {} to the database", appUser.getName());
       // appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepo.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToAppUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
         AppUser appUser = appUserRepo.findByUsername(username);
         Role role = roleRepo.findByName(roleName);
         appUser.getRoles().add(role);
    }

    @Override
    public AppUser getAppUser(String username) {
        log.info("Fetching user {}", username);
        return appUserRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getAppUsers() {
        log.info("Fetching all users");
        return appUserRepo.findAll() ;
    }

}
