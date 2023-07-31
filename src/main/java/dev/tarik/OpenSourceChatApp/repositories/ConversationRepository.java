package dev.tarik.OpenSourceChatApp.repositories;

import dev.tarik.OpenSourceChatApp.models.Conversation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ConversationRepository extends MongoRepository<Conversation, ObjectId> {

    @Query("{'participants': ?0}")
    List<Conversation> getAllConversationsWhereIsTheUser(ObjectId id);
}
