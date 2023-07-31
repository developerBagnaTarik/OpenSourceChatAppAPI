package dev.tarik.OpenSourceChatApp.requests;

import org.bson.types.ObjectId;

public class GroupConversation {

    private String name;
    private ObjectId user1Id;
    private ObjectId user2Id;

    public GroupConversation() {
    }

    public GroupConversation(String name, ObjectId user1Id, ObjectId user2Id) {
        this.name = name;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
