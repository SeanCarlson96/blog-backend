package net.yorksolutions.blogappbe.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    @Column(unique = true)
    public String username;
    public String password;
    @OneToMany
    public Set<Message> messages;
    public AppUser(Long id, String username, String password, Set<Message> messages) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.messages = messages;
    }
    public AppUser() {
    }
}