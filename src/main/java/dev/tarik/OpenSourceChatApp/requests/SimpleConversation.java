package dev.tarik.OpenSourceChatApp.requests;

import org.bson.types.ObjectId;

public class SimpleConversation {
    private ObjectId user1Id;
    private ObjectId user2Id;

    public SimpleConversation() {
    }

    public SimpleConversation(ObjectId user1Id, ObjectId user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public ObjectId getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(ObjectId user1Id) {
        this.user1Id = user1Id;
    }

    public ObjectId getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(ObjectId user2Id) {
        this.user2Id = user2Id;
    }
}
