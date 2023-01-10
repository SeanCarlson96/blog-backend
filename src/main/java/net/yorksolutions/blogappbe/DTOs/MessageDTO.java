package net.yorksolutions.blogappbe.DTOs;

import java.util.Date;
import java.util.Optional;

public class MessageDTO {
    public Optional<Long> id;
    public String title;
    public String body;
    public Long authorId;
    public Date created_date;
    public Date updated_date;
    public Long views;
    public Iterable<Long> commentsIds;
    public Long postId;
    public Long recipientId;
}
