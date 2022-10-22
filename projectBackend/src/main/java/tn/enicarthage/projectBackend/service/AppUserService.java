package tn.enicarthage.projectBackend.service;

import tn.enicarthage.projectBackend.model.AppUser;
import tn.enicarthage.projectBackend.model.Role;

import java.util.List;

public interface AppUserService {
    AppUser saveUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToAppUser(String username,String roleName);
    AppUser getAppUser(String username);
    List<AppUser> getAppUsers();
}
