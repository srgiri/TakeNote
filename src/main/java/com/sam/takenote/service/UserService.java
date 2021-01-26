package com.sam.takenote.service;

import com.sam.takenote.dto.request.CreateUserRequest;
import com.sam.takenote.dto.request.UserAuthRequest;
import com.sam.takenote.entity.Users;
import com.sam.takenote.exception.TakeNoteErrorCodes;
import com.sam.takenote.exception.TakeNoteGenericException;
import com.sam.takenote.helper.JWTHelper;
import com.sam.takenote.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.sam.takenote.exception.TakeNoteErrorCodes.*;

@Service
public class UserService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTHelper jwtHelper;

    @Transactional
    public void createUser(CreateUserRequest createUserRequest) throws TakeNoteGenericException {
        if (userRepository.countByUserName(createUserRequest.getUserName()) > 0) {
            log.warn("user {} already exists", createUserRequest.getUserName());
            throw new TakeNoteGenericException(String.format("User %s already exists",
                    createUserRequest.getUserName()), DATA_ALREADY_EXISTS);
        }
        Users userEntity = new Users();
        userEntity.setUserId(UUID.randomUUID());
        userEntity.setUserName(createUserRequest.getUserName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPassword(createUserRequest.getPassword());
        userRepository.save(userEntity);
    }

    public String getAuthToken(UserAuthRequest userAuthRequest) throws TakeNoteGenericException {
        if (userRepository.countByUserNameAndPassword(userAuthRequest.getUsername(),
                userAuthRequest.getPassword()) == 0) {
            throw new TakeNoteGenericException("Incorrect username or password", INVALID_CREDENTIALS);
        }
        return jwtHelper.generateToken(userAuthRequest.getUsername());
    }

    public Optional<Users> validateToken(String token) throws TakeNoteGenericException {
        String username = jwtHelper.extractUsername(token);
        if (userRepository.countByUserName(username) == 0) {
            throw new TakeNoteGenericException("Invalid token", CLIENT_TOKEN_ERROR);
        }
        if (jwtHelper.validateToken(token, username)) {
            Users users = userRepository.findByUserName(username);
            if (users != null) {
                return Optional.of(users);
            }
        }
        throw new TakeNoteGenericException("User data not found", DATA_NOT_FOUND);
    }
}
