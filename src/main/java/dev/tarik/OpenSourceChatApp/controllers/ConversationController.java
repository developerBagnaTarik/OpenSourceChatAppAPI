package dev.tarik.OpenSourceChatApp.controllers;

import dev.tarik.OpenSourceChatApp.models.Conversation;
import dev.tarik.OpenSourceChatApp.requests.GroupConversation;
import dev.tarik.OpenSourceChatApp.requests.SimpleConversation;
import dev.tarik.OpenSourceChatApp.requests.UserInTheConversationRequest;
import dev.tarik.OpenSourceChatApp.services.ConversationService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class ConversationController {
    private static final String ENTITY_PATH = "Conversation";
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping(ENTITY_PATH+"/conversations")
    public ResponseEntity<?> getAllConversations(){
        List<Conversation> allConversationsOfTheServiceResponse = conversationService.getAllConversations();
        Map<String,Object> response = new HashMap<>();
        response.put("status_code", HttpStatus.OK.value());
        response.put("data",allConversationsOfTheServiceResponse);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping(ENTITY_PATH+"/conversationsWhereIsTheUser/{id}")
    public ResponseEntity<?> getAllConversationsWhereIsTheUser(@PathVariable("id") ObjectId id){
        Map<String,Object> response = new HashMap<>();
        List<Conversation> allConversationsOfTheServiceResponse = conversationService.getAllConversationsWhereIsTheUser(id);
        if(allConversationsOfTheServiceResponse != null){
            response.put("status_code",HttpStatus.OK.value());
            response.put("data",allConversationsOfTheServiceResponse);
            return ResponseEntity.ok().body(response);
        }
        response.put("status_code",HttpStatus.NO_CONTENT.value());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(ENTITY_PATH+"/create")
    public ResponseEntity<?> add(@RequestBody SimpleConversation simpleConversation){
        Map<String, Object> response = new HashMap<>();
        Conversation conversationOfTheServiceResponse = conversationService.createConversation(simpleConversation);
        if(conversationOfTheServiceResponse != null){
            response.put("status_code",HttpStatus.OK.value());
            response.put("data",conversationOfTheServiceResponse);
        }
        response.put("status_code",HttpStatus.NOT_FOUND.value());
        response.put("message","One of the users does not exists");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping(ENTITY_PATH+"/createGroup")
    public ResponseEntity<?> addGroup(@RequestBody GroupConversation groupConversation){
        Map<String, Object> response = new HashMap<>();
        Conversation conversationGroupOfTheServiceResponse = conversationService.createGroupConversation(groupConversation);
        if(conversationGroupOfTheServiceResponse != null){
            response.put("status_code",HttpStatus.OK.value());
            response.put("data",conversationGroupOfTheServiceResponse);
        }
        response.put("status_code",HttpStatus.NOT_FOUND.value());
        response.put("message","One of the users does not exists");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping(ENTITY_PATH+"/users/add")
    public ResponseEntity<?> addUserToGroupConversation(@RequestBody UserInTheConversationRequest userInTheConversationRequest){
        Map<String, Object> response = new HashMap<>();
        Conversation conversationOfTheServiceResponse = conversationService.addUserInTheConversation(userInTheConversationRequest);
        if(conversationOfTheServiceResponse != null){
            response.put("status_code",HttpStatus.OK.value());
            response.put("data",conversationOfTheServiceResponse);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.put("status_code",HttpStatus.NOT_FOUND.value());
        response.put("message","The conversation is not a group or the user does not exist");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping(ENTITY_PATH+"/users/delete")
    public ResponseEntity<?> deleteUserFromGroupConversation(@RequestBody UserInTheConversationRequest userInTheConversationRequest){
        Map<String, Object> response = new HashMap<>();
        Conversation conversationOfTheServiceResponse = conversationService.deleteUserInTheConversation(userInTheConversationRequest);
        if(conversationOfTheServiceResponse != null){
            response.put("status_code",HttpStatus.OK.value());
            response.put("data",conversationOfTheServiceResponse);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.put("status_code",HttpStatus.NOT_FOUND.value());
        response.put("message","The conversation is not a group or the user does not exist");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
