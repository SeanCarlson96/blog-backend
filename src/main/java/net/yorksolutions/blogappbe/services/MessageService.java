package net.yorksolutions.blogappbe.services;

import net.yorksolutions.blogappbe.DTOs.AppUserDTO;
import net.yorksolutions.blogappbe.DTOs.MessageDTO;
import net.yorksolutions.blogappbe.models.AppUser;
import net.yorksolutions.blogappbe.models.Message;
import net.yorksolutions.blogappbe.repositories.AppUserRepo;
import net.yorksolutions.blogappbe.repositories.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {
    private final AppUserRepo appUserRepo;
    private final MessageRepo messageRepo;
    public MessageService(AppUserRepo appUserRepo, MessageRepo messageRepo) {
        this.appUserRepo = appUserRepo;
        this.messageRepo = messageRepo;
    }
    public Iterable<Message> getAll() {
        return messageRepo.findAll();
    }
    public void createMessage(MessageDTO newMessage)  throws Exception {
        Message message = new Message();
        if(newMessage.title.isPresent())
            message.title = newMessage.title.orElse(null);
        message.body = newMessage.body;
        message.author = appUserRepo.findById(newMessage.authorId).orElse(null);
        message.created_date = newMessage.created_date;
        message.updated_date = newMessage.updated_date;
        if(newMessage.views.isPresent())
            message.views = newMessage.views.orElse(null);
        if(newMessage.commentIds.isPresent()) {
            for (Long commentId : newMessage.commentIds.orElse(null)) {
                Optional<Message> commentWithId = messageRepo.findById(commentId);
                if (commentWithId.isEmpty())
                    throw new Exception();
                commentWithId.ifPresent(message.comments::add);
            }
        }
        if(newMessage.postId.isPresent())
            message.post = messageRepo.findById(newMessage.postId.orElse(null)).orElse(null);
        if(newMessage.recipientId.isPresent())
            message.recipient = appUserRepo.findById(newMessage.recipientId.orElse(null)).orElse(null);
        messageRepo.save(message);
    }
    public void deleteMessageById(Long id) throws Exception {
        Optional<Message> messageWithId = messageRepo.findById(id);
        if (messageWithId.isEmpty())
            throw new Exception();
        messageRepo.deleteById(id);
    }
    public void updateMessage(Long id, MessageDTO updatedMessage) throws Exception {
        Optional<Message> messageWithId = messageRepo.findById(id);
        if (messageWithId.isEmpty())
            throw new Exception();
        Message message = messageWithId.get();
        if(updatedMessage.title.isPresent())
            message.title = updatedMessage.title.orElse(null);
        message.body = updatedMessage.body;
        message.author = appUserRepo.findById(updatedMessage.authorId).orElse(null);
        message.created_date = updatedMessage.created_date;
        message.updated_date = updatedMessage.updated_date;
        if(updatedMessage.views.isPresent())
            message.views = updatedMessage.views.orElse(null);
        if(updatedMessage.commentIds.isPresent()) {
            for (Long commentId : updatedMessage.commentIds.orElse(null)) {
                Optional<Message> commentWithId = messageRepo.findById(commentId);
                if (commentWithId.isEmpty())
                    throw new Exception();
                commentWithId.ifPresent(message.comments::add);
            }
        }
        if(updatedMessage.postId.isPresent())
            message.post = messageRepo.findById(updatedMessage.postId.orElse(null)).orElse(null);
        if(updatedMessage.recipientId.isPresent())
            message.recipient = appUserRepo.findById(updatedMessage.recipientId.orElse(null)).orElse(null);
        messageRepo.save(message);
    }
}
