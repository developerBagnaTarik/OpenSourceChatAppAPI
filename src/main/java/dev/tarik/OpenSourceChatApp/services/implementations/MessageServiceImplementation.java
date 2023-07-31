package dev.tarik.OpenSourceChatApp.services.implementations;

import dev.tarik.OpenSourceChatApp.models.Conversation;
import dev.tarik.OpenSourceChatApp.models.Message;
import dev.tarik.OpenSourceChatApp.models.User;
import dev.tarik.OpenSourceChatApp.repositories.ConversationRepository;
import dev.tarik.OpenSourceChatApp.repositories.MessageRepository;
import dev.tarik.OpenSourceChatApp.repositories.UserRepository;
import dev.tarik.OpenSourceChatApp.services.MessageService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageServiceImplementation implements MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public MessageServiceImplementation(MessageRepository messageRepository, ConversationRepository conversationRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Message sendMessage(Message message) {
        if(message.getSender_id() != null && message.getConversationId() != null && (message.getText() != null || message.getStickers() != null || message.getFile() != null)){
            Conversation conversation = conversationRepository.findById(message.getConversationId()).orElse(null);
            User user = userRepository.findById(message.getSender_id()).orElse(null);
            if(conversation != null && user != null){
                if(message.getMessageRepliedId() != null){
                    Message messageFromDatabase = messageRepository.findById(message.getMessageRepliedId()).orElse(null);
                    if(messageFromDatabase != null){
                        message.setDate_of_sender(new Date());
                        return messageRepository.save(message);
                    }
                }else {
                    message.setDate_of_sender(new Date());
                    return messageRepository.save(message);
                }
            }
        }
        return null;
    }

    @Override
    public Boolean deleteMessage(ObjectId id) {
        Message message = messageRepository.findById(id).orElse(null);
        if(message != null){
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll(Sort.by(Sort.Order.desc("date_of_sender")));
    }

    @Override
    public List<Message> getAllMessagesByConversation(ObjectId id) {
        List<Message> allMessagesByConversation = messageRepository.findAllMessagesByConversation(id);
        if(allMessagesByConversation.size() >0){
            return allMessagesByConversation;
        }
        return null;
    }
}
