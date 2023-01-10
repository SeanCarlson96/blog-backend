package net.yorksolutions.blogappbe.services;


import net.yorksolutions.blogappbe.DTOs.AppUserDTO;
import net.yorksolutions.blogappbe.models.AppUser;
import net.yorksolutions.blogappbe.models.Message;
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
    public void createAppUser(AppUserDTO newAppUser)  throws Exception {
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

    public void updateAppUser(Long id, AppUserDTO updatedAppUser) throws Exception {
        Optional<AppUser> appUserWithId = appUserRepo.findById(id);
        if (appUserWithId.isEmpty())
            throw new Exception();
        AppUser appUser = appUserWithId.get();
        appUser.username = updatedAppUser.username;
        appUser.password = updatedAppUser.password;
        //loop through updatedAppUser.messages
        for (Long messageId : updatedAppUser.messageIds) {
            //get the message for that messageId
            Optional<Message> messageWithId = messageRepo.findById(messageId);
            if (messageWithId.isEmpty())
                throw new Exception();
            //add that message to the Set of Messages that is appUser.messages
            messageWithId.ifPresent(appUser.messages::add);
            //appUser.messages.add(messageWithId.orElse(throw new Exception()));
        }
        appUserRepo.save(appUser);
    }
}
