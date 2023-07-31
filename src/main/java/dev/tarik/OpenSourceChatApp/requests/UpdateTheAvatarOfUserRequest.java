package dev.tarik.OpenSourceChatApp.requests;

import org.bson.types.ObjectId;


public class UpdateTheAvatarOfUserRequest {
    private ObjectId _id;
    private String avatar_url;

    public UpdateTheAvatarOfUserRequest() {
    }

    public UpdateTheAvatarOfUserRequest(ObjectId _id, String avatar_url) {
        this._id = _id;
        this.avatar_url = avatar_url;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
