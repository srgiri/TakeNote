package com.sam.takenote.controller;

import com.sam.takenote.dto.request.CreateUserRequest;
import com.sam.takenote.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/TakeNote/v1")
public class TakeNoteController {
    @Autowired
    private UserValidator userValidator;

    private static final Logger log = LoggerFactory.getLogger(TakeNoteController.class);

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@Valid @RequestBody final CreateUserRequest createUserRequest,
                                             final BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("User creation request data has validation errors: {}", createUserRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (userValidator.doesSameUserNameExist(createUserRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body("User creation successful");
    }

}
