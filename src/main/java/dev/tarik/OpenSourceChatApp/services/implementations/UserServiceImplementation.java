package dev.tarik.OpenSourceChatApp.services.implementations;

import dev.tarik.OpenSourceChatApp.models.User;
import dev.tarik.OpenSourceChatApp.repositories.UserRepository;
import dev.tarik.OpenSourceChatApp.requests.UpdateTheAvatarOfUserRequest;
import dev.tarik.OpenSourceChatApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //  ADD A USER TO OUR DATABASE
    @Override
    public User add(User user) {
        if(user.getUsername()!=null && user.getPassword() != null){
            //  We get all users that have the same username
            List<User> listOfUsersWithThisUsername = userRepository.findAllByUsername(user.getUsername());
            //  If we don't have a user with this username
            if (listOfUsersWithThisUsername.size() ==0){
                user.setCreated_at(new Date());
                user.setUpdated_at(null);
                //  We save the user
                return userRepository.save(user);
            }
        }
        //  Else we return null
        return null;
    }

    //  UPDATE THE AVATAR URL OF THE USER
    @Override
    public User updateTheAvatarUrl(UpdateTheAvatarOfUserRequest updateTheAvatarOfUserRequest) {
        if(updateTheAvatarOfUserRequest.getAvatar_url()!=null && updateTheAvatarOfUserRequest.get_id() !=null){
            //  We get the user with this id
            //  If no user with this id userOfTheDatabase will be null
            User userOfTheDatabase = userRepository.findById(updateTheAvatarOfUserRequest.get_id()).orElse(null);
            //  If userOfTheDatabase is not null
            if(userOfTheDatabase != null){
                userOfTheDatabase.setUpdated_at(new Date());
                userOfTheDatabase.setAvatar_url(updateTheAvatarOfUserRequest.getAvatar_url());
                //  We updated the user avatar
                return userRepository.save(userOfTheDatabase);
            }
        }
        //  Else we return null
        return null;
    }

    //  LOGICAL SUPPRESSION OF A USER
    @Override
    public Boolean delete(ObjectId id) {
        //  NO NEED BUT WE CAN IMPLEMENT IT
        //  Logic suppression
        //  We get the user with this id
        //  If no user with this id userOfTheDatabase will be null
        User userOfTheDatabase = userRepository.findById(id).orElse(null);
        //  If userOfTheDatabase is not null
        if(userOfTheDatabase != null){
            userOfTheDatabase.setDeleted(true);
            //  We save the user with deleted attribute equals to true
            userRepository.save(userOfTheDatabase);
            //  And we return true
            return true;
        }
        //  Else we return false
        return false;
    }

    //  LIST OF ALL USERS
    @Override
    public List<User> findUsers() {
        return userRepository.findAll(Sort.by(Sort.Order.asc("username")));
    }

    //  LIST OF ALL USERS THAT ARE DIFFERENT FROM THE CONNECTED USER
    @Override
    public List<User> findUsersThatAreDifferentFrom(ObjectId idUser) {
        return userRepository.findUsersThatAreDifferentFrom(idUser);
    }
}
