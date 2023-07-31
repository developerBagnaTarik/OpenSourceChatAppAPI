package dev.tarik.OpenSourceChatApp.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.tarik.OpenSourceChatApp.serializers.ObjectIdSerializer;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Messages")
public class Message {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId _id;
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId conversationId;
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId sender_id;
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId messageRepliedId;
    private String text;
    private String stickers;
    private String file;
    private Date date_of_sender;
    private Boolean deleted = false;

    public Message(ObjectId _id, ObjectId conversationId, ObjectId sender_id, ObjectId messageRepliedId,String text, Date date_of_sender) {
        this._id = _id;
        this.conversationId = conversationId;
        this.sender_id = sender_id;
        this.text = text;
        this.date_of_sender = date_of_sender;
        this.messageRepliedId = messageRepliedId;
    }

    public Message() {
    }

    public ObjectId get_id() {
        return _id;
    }

    public ObjectId getConversationId() {
        return conversationId;
    }

    public ObjectId getSender_id() {
        return sender_id;
    }

    public String getStickers() {
        return stickers;
    }

    public String getText() {
        return text;
    }

    public Date getDate_of_sender() {
        return date_of_sender;
    }

    public String getFile() {
        return file;
    }

    public ObjectId getMessageRepliedId() {
        return messageRepliedId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setConversationId(ObjectId conversationId) {
        this.conversationId = conversationId;
    }

    public void setSender_id(ObjectId sender_id) {
        this.sender_id = sender_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate_of_sender(Date date_of_sender) {
        this.date_of_sender = date_of_sender;
    }

    public void setStickers(String stickers) {
        this.stickers = stickers;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setMessageRepliedId(ObjectId messageRepliedId) {
        this.messageRepliedId = messageRepliedId;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
