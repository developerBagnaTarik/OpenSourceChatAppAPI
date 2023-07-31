package dev.tarik.OpenSourceChatApp.services;

import dev.tarik.OpenSourceChatApp.models.Conversation;
import dev.tarik.OpenSourceChatApp.requests.GroupConversation;
import dev.tarik.OpenSourceChatApp.requests.SimpleConversation;
import dev.tarik.OpenSourceChatApp.requests.UserInTheConversationRequest;
import org.bson.types.ObjectId;

import java.util.List;

public interface ConversationService {

    Conversation createConversation(SimpleConversation simpleConversation);
    Conversation createGroupConversation(GroupConversation groupConversation);
    Conversation addUserInTheConversation(UserInTheConversationRequest addUserInTheConvRequest);
    Conversation deleteUserInTheConversation(UserInTheConversationRequest addUserInTheConvRequest);
    Boolean delete(ObjectId id);
    List<Conversation> getAllConversations();
    List<Conversation> getAllConversationsWhereIsTheUser(ObjectId id);
}
