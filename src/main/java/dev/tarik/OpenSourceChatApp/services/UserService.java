package dev.tarik.OpenSourceChatApp.services;

import dev.tarik.OpenSourceChatApp.models.User;
import dev.tarik.OpenSourceChatApp.requests.UpdateTheAvatarOfUserRequest;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    User add(User user);
    User updateTheAvatarUrl(UpdateTheAvatarOfUserRequest updateTheAvatarOfUserRequest);
    Boolean delete(ObjectId id);
    List<User> findUsers();
    List<User> findUsersThatAreDifferentFrom(ObjectId idUser);
}
