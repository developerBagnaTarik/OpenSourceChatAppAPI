package dev.tarik.OpenSourceChatApp.repositories;

import dev.tarik.OpenSourceChatApp.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    @Query("{'username': ?0}")
    List<User> findAllByUsername(String username);

    @Query("{'_id': {'$ne': ?0}}")
    List<User> findUsersThatAreDifferentFrom(ObjectId idUser);
}
