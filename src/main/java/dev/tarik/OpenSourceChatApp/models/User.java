package dev.tarik.OpenSourceChatApp.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.tarik.OpenSourceChatApp.serializers.ObjectIdSerializer;
import lombok.Builder;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Users")
public class User {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId _id;
    private String username;
    private String password;
    private String avatar_url;
    private Date created_at;
    private Date updated_at;
    private Boolean deleted = false;

    public User(ObjectId _id, String username, String password, String avatar_url, Date created_at, Date updated_at) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.avatar_url = avatar_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public User() {
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
