package dev.tarik.OpenSourceChatApp.services;

import dev.tarik.OpenSourceChatApp.models.Message;
import org.bson.types.ObjectId;

import java.util.List;

public interface MessageService {
    Message sendMessage(Message message);
    Boolean deleteMessage(ObjectId id);
    List<Message> getAllMessages();
    List<Message> getAllMessagesByConversation(ObjectId id);
}
