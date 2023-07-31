package dev.tarik.OpenSourceChatApp.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.tarik.OpenSourceChatApp.serializers.ObjectIdListSerializer;
import dev.tarik.OpenSourceChatApp.serializers.ObjectIdSerializer;
import lombok.Builder;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "Conversations")
public class Conversation {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId _id;
    private String name;

    @JsonSerialize(using = ObjectIdListSerializer.class)
    private List<ObjectId> participants = new ArrayList<>();
    private Date created_at;
    private Date updated_at;

    public Conversation(ObjectId _id, String name, List<ObjectId> participants, Date created_at, Date updated_at) {
        this._id = _id;
        this.name = name;
        this.participants = participants;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Conversation() {
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public List<ObjectId> getParticipants() {
        return participants;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParticipants(List<ObjectId> participants) {
        this.participants = participants;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
