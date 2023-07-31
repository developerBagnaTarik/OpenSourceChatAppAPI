package dev.tarik.OpenSourceChatApp.repositories;

import dev.tarik.OpenSourceChatApp.models.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, ObjectId> {

    @Query("{'conversationId': ?0,'deleted': false}")
    List<Message> findAllMessagesByConversation(ObjectId id);
}
