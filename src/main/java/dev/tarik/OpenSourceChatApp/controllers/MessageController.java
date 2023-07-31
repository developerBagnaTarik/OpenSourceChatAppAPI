package dev.tarik.OpenSourceChatApp.controllers;

import dev.tarik.OpenSourceChatApp.models.Message;
import dev.tarik.OpenSourceChatApp.services.MessageService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class MessageController {
    private static final String ENTITY_PATH = "Message";
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(ENTITY_PATH+"/messages")
    public ResponseEntity<?> getAllMessages(){
        List<Message> allMessageOfTheServiceResponse = messageService.getAllMessages();
        Map<String ,Object> response = new HashMap<>();
        response.put("status_code", HttpStatus.OK.value());
        response.put("data",allMessageOfTheServiceResponse);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(ENTITY_PATH+"/messages/conversations/{id}")
    public ResponseEntity<?> getAllMessagesByConversation(@PathVariable ObjectId id){
        List<Message> allMessageByConversationOfTheServiceResponse = messageService.getAllMessagesByConversation(id);
        Map<String ,Object> response = new HashMap<>();
        if(allMessageByConversationOfTheServiceResponse != null){
            response.put("status_code", HttpStatus.OK.value());
            response.put("data",allMessageByConversationOfTheServiceResponse);
            return ResponseEntity.ok().body(response);
        }
        response.put("status_code", HttpStatus.NO_CONTENT.value());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @PostMapping(ENTITY_PATH+"/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message){
        Message messageOfTheServiceResponse = messageService.sendMessage(message);
        Map<String ,Object> response = new HashMap<>();
        if(messageOfTheServiceResponse != null){
            response.put("status_code", HttpStatus.OK.value());
            response.put("data", messageOfTheServiceResponse);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.put("status_code", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
