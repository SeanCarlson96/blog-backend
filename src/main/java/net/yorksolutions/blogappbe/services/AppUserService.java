package net.yorksolutions.blogappbe.services;


import net.yorksolutions.blogappbe.models.AppUser;
import net.yorksolutions.blogappbe.repositories.AppUserRepo;

import net.yorksolutions.blogappbe.repositories.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserService {
    private final AppUserRepo appUserRepo;
    private final MessageRepo messageRepo;
    public AppUserService(AppUserRepo appUserRepo, MessageRepo messageRepo) {
        this.appUserRepo = appUserRepo;
        this.messageRepo = messageRepo;
    }
    public Iterable<AppUser> getAll() {
        return appUserRepo.findAll();
    }
    public AppUser getUserByUsernameAndPassword(String username, String password) {
        return appUserRepo.findAppUserByUsernameAndPassword(username, password).orElse(null);
    }
    public void createAppUser(AppUser newAppUser)  throws Exception {
        Optional<AppUser> appUserWithUsername = appUserRepo.findAppUserByUsername(newAppUser.username);
        if (appUserWithUsername.isPresent())
            throw new Exception();
        AppUser appUser = new AppUser();
        appUser.username = newAppUser.username;
        appUser.password = newAppUser.password;
        appUserRepo.save(appUser);
    }
    public void deleteAppUserById(Long id) throws Exception {
        Optional<AppUser> appUserWithId = appUserRepo.findById(id);
        if (appUserWithId.isEmpty())
            throw new Exception();
        appUserRepo.deleteById(id);
    }

    public void updateAppUser(Long id, AppUser updatedAppUser) throws Exception {
        Optional<AppUser> appUserWithId = appUserRepo.findById(id);
        if (appUserWithId.isEmpty())
            throw new Exception();
//TODO might need to change this method to accept AppUserDTO
        //might also need to use my me
        AppUser appUser = appUserWithId.get();
        appUser.username = updatedAppUser.username;
        appUser.password = updatedAppUser.password;
        appUser.messages = updatedAppUser.messages;

        appUserRepo.save(appUser);
    }
}
