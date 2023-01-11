package net.yorksolutions.blogappbe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String title;
    public String body;
    @ManyToOne
    @JsonIgnoreProperties("messages")
    public AppUser author;
    public Date created_date;
    public Date updated_date;
    public Long views;
    @OneToMany
    public Set<Message> comments;
    @ManyToOne
    @JsonIgnoreProperties("comments")
    public Message post;
    @OneToOne
    public AppUser recipient;
    public Message(Long id, String title, String body, AppUser author, Date created_date, Date updated_date, Long views, Set<Message> comments, Message post, AppUser recipient) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.views = views;
        this.comments = comments;
        this.post = post;
        this.recipient = recipient;
    }
    public Message() {
    }
}