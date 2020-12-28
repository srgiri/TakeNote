package com.sam.takenote.validators;

import com.sam.takenote.dto.request.CreateUserRequest;
import com.sam.takenote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    @Autowired
    private UserRepository userRepository;

    public boolean doesSameUserNameExist(CreateUserRequest createUserRequest) {
        return userRepository.countByUserName(createUserRequest.getUserName()) > 0;
    }
}
