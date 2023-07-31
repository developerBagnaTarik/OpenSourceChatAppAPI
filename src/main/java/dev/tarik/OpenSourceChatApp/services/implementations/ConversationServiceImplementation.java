package dev.tarik.OpenSourceChatApp.services.implementations;

import dev.tarik.OpenSourceChatApp.models.Conversation;
import dev.tarik.OpenSourceChatApp.models.User;
import dev.tarik.OpenSourceChatApp.repositories.ConversationRepository;
import dev.tarik.OpenSourceChatApp.repositories.UserRepository;
import dev.tarik.OpenSourceChatApp.requests.GroupConversation;
import dev.tarik.OpenSourceChatApp.requests.SimpleConversation;
import dev.tarik.OpenSourceChatApp.requests.UserInTheConversationRequest;
import dev.tarik.OpenSourceChatApp.services.ConversationService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ConversationServiceImplementation implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public ConversationServiceImplementation(ConversationRepository conversationRepository,UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    //  ADD A SIMPLE CONVERSATION
    @Override
    public Conversation createConversation(SimpleConversation simpleConversation) {
        User user1 = userRepository.findById(simpleConversation.getUser1Id()).orElse(null);
        User user2 = userRepository.findById(simpleConversation.getUser2Id()).orElse(null);

        if(user1 != null && user2 != null){
            Conversation conversation = new Conversation();
            conversation.setCreated_at(new Date());
            conversation.setName(null);
            conversation.setUpdated_at(null);
            conversation.getParticipants().add(user1.get_id());
            conversation.getParticipants().add(user2.get_id());

            return conversationRepository.save(conversation);
        }
        return null;
    }

    //  ADD GROUP CONVERSATION
    @Override
    public Conversation createGroupConversation(GroupConversation groupConversation) {

        User user1 = userRepository.findById(groupConversation.getUser1Id()).orElse(null);
        User user2 = userRepository.findById(groupConversation.getUser2Id()).orElse(null);

        if(user1 != null && user2 != null && groupConversation.getName() != null){
            Conversation conversation = new Conversation();
            conversation.setCreated_at(new Date());
            conversation.setName(groupConversation.getName());
            conversation.setUpdated_at(null);
            conversation.getParticipants().add(user1.get_id());
            conversation.getParticipants().add(user2.get_id());

            return conversationRepository.save(conversation);
        }
        return null;
    }

    //  ADD A USER TO A GROUP
    @Override
    public Conversation addUserInTheConversation(UserInTheConversationRequest addUserInTheConvRequest) {
        Conversation conversation = conversationRepository.findById(addUserInTheConvRequest.getIdOfTheConversation()).orElse(null);
        if(conversation != null && conversation.getName() != null){
            User user = userRepository.findById(addUserInTheConvRequest.getIdOfTheParticipant()).orElse(null);
            if(user != null){
                conversation.getParticipants().add(user.get_id());
                return conversationRepository.save(conversation);
            }
        }
        return null;
    }

    //  DELETE A USER FROM A GROUP
    @Override
    public Conversation deleteUserInTheConversation(UserInTheConversationRequest addUserInTheConvRequest) {
        Conversation conversation = conversationRepository.findById(addUserInTheConvRequest.getIdOfTheConversation()).orElse(null);
        if(conversation.getName() != null){
            User user = userRepository.findById(addUserInTheConvRequest.getIdOfTheParticipant()).orElse(null);
            if(user != null){
                conversation.getParticipants().remove(user.get_id());
                return conversationRepository.save(conversation);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(ObjectId id) {
        return null;
    }

    @Override
    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }

    @Override
    public List<Conversation> getAllConversationsWhereIsTheUser(ObjectId id) {
        return conversationRepository.getAllConversationsWhereIsTheUser(id);
    }
}
