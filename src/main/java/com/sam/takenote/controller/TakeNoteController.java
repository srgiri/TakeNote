package com.sam.takenote.controller;

import com.sam.takenote.dto.request.CreateUserRequest;
import com.sam.takenote.dto.request.UserAuthRequest;
import com.sam.takenote.entity.Users;
import com.sam.takenote.exception.TakeNoteGenericException;
import com.sam.takenote.service.ShelfService;
import com.sam.takenote.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/TakeNote/v1")
public class TakeNoteController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShelfService shelfService;

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
            return ResponseEntity.status(HttpStatus.CREATED).body("User creation successful");
        } catch (TakeNoteGenericException te) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(te.getMessage());
        }
    }

    @PostMapping("/authentication")
    public ResponseEntity<String> getUserAuthToken(@Valid @RequestBody final UserAuthRequest userAuthRequest,
                                                   final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("User authentication request data has validation errors: {}", userAuthRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            String token = userService.getAuthToken(userAuthRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(token);
        } catch (TakeNoteGenericException te) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(te.getMessage());
        }
    }

    @PostMapping("/createShelf")
    public ResponseEntity<String> createShelf(@RequestParam String shelfName, @RequestHeader String token) {
        try {
            Optional<Users> usersOptional = userService.validateToken(token);
            shelfService.createShelf(usersOptional.get(), shelfName);
            return ResponseEntity.status(HttpStatus.CREATED).body("Shelf created successfully");
        } catch (TakeNoteGenericException te) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(te.getMessage());
        }
    }

    @PostMapping("/renameShelf")
    public ResponseEntity<String> renameShelf(@RequestParam String oldShelfName, @RequestParam String newShelfName,
                                              @RequestHeader String token) {
        try {
            Optional<Users> usersOptional = userService.validateToken(token);
            shelfService.renameShelf(usersOptional.get(), oldShelfName, newShelfName);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Shelf renamed successfully");
        } catch (TakeNoteGenericException te) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(te.getMessage());
        }
    }

   /* @GetMapping("/checkToken")
    public ResponseEntity<String> checkToken(@RequestParam String token) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.valueOf(userService.validateToken(token)));
        } catch (TakeNoteGenericException te) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(te.getMessage());
        }
    }*/

}
