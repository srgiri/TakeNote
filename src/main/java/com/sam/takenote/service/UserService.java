package com.sam.takenote.service;

import com.sam.takenote.dto.request.CreateUserRequest;
import com.sam.takenote.entity.Users;
import com.sam.takenote.exception.TakeNoteGenericException;
import com.sam.takenote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUser(CreateUserRequest createUserRequest) throws TakeNoteGenericException {
        if (userRepository.countByUserName(createUserRequest.getUserName()) > 0) {
            throw new TakeNoteGenericException(String.format("User %s already exists"
                    , createUserRequest.getUserName()));
        }
        Users userEntity = new Users();
        userEntity.setUserId(UUID.randomUUID());
        userEntity.setUserName(createUserRequest.getUserName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPassword(createUserRequest.getPassword());
        userRepository.save(userEntity);
    }
}
