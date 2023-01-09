package net.yorksolutions.blogappbe.repositories;

import net.yorksolutions.blogappbe.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
}
