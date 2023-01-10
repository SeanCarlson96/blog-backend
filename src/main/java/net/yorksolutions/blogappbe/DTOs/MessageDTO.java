package net.yorksolutions.blogappbe.DTOs;

import java.util.Date;
import java.util.Optional;

public class MessageDTO {
    public Optional<Long> id;
    public Optional<String> title;
    public String body;
    public Long authorId;
    public Date created_date;
    public Date updated_date;
    public Optional<Long> views;
    public Optional<Iterable<Long>> commentIds;
    public Optional<Long> postId;
    public Optional<Long> recipientId;
}
