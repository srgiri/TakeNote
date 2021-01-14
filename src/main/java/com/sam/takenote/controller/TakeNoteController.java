package com.sam.takenote.controller;

import com.sam.takenote.dto.request.CreateUserRequest;
import com.sam.takenote.exception.TakeNoteGenericException;
import com.sam.takenote.service.UserService;
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
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(TakeNoteController.class);

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@Valid @RequestBody final CreateUserRequest createUserRequest,
                                             final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("User creation request data has validation errors: {}", createUserRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            userService.createUser(createUserRequest);
        } catch (TakeNoteGenericException te) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(te.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("User creation successful");
    }

}
