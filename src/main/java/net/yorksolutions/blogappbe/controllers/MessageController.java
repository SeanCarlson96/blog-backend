package net.yorksolutions.blogappbe.controllers;

import net.yorksolutions.blogappbe.DTOs.AppUserDTO;
import net.yorksolutions.blogappbe.DTOs.MessageDTO;
import net.yorksolutions.blogappbe.models.Message;
import net.yorksolutions.blogappbe.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessageController {
    private final MessageService service;
    public MessageController(MessageService service) {
        this.service = service;
    }
    @GetMapping
    public Iterable<Message> getAll(){
        return service.getAll();
    }
    @PostMapping
    public void createMessage(@RequestBody MessageDTO newMessage){
        try{
            service.createMessage(newMessage);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteMessageById(@PathVariable Long id){
        try{
            service.deleteMessageById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public void updateMessage(@PathVariable Long id, @RequestBody MessageDTO updatedMessage) {
        try {
            service.updateMessage(id, updatedMessage);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
