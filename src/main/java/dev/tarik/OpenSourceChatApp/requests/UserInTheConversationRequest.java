package dev.tarik.OpenSourceChatApp.requests;

import org.bson.types.ObjectId;

public class UserInTheConversationRequest {
    private ObjectId idOfTheConversation;
    private ObjectId idOfTheParticipant;

    public UserInTheConversationRequest() {
    }

    public UserInTheConversationRequest(ObjectId idOfTheConversation, ObjectId idOfTheParticipant) {
        this.idOfTheConversation = idOfTheConversation;
        this.idOfTheParticipant = idOfTheParticipant;
    }

    public ObjectId getIdOfTheConversation() {
        return idOfTheConversation;
    }

    public void setIdOfTheConversation(ObjectId idOfTheConversation) {
        this.idOfTheConversation = idOfTheConversation;
    }

    public ObjectId getIdOfTheParticipant() {
        return idOfTheParticipant;
    }

    public void setIdOfTheParticipant(ObjectId idOfTheParticipant) {
        this.idOfTheParticipant = idOfTheParticipant;
    }
}
