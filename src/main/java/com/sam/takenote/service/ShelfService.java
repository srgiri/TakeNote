package com.sam.takenote.service;

import com.sam.takenote.entity.Shelf;
import com.sam.takenote.entity.Users;
import com.sam.takenote.exception.TakeNoteGenericException;
import com.sam.takenote.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.sam.takenote.exception.TakeNoteErrorCodes.DATA_ALREADY_EXISTS;
import static com.sam.takenote.exception.TakeNoteErrorCodes.DATA_NOT_FOUND;

@Service
public class ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    @Transactional
    public void createShelf(Users users, String shelfName) throws TakeNoteGenericException {
        if (shelfRepository.countByShelfNameAndUserName(shelfName, users.getUserName()) > 0) {
            throw new TakeNoteGenericException(String.format("Shelf name %s already exists", shelfName), DATA_ALREADY_EXISTS);
        }
        Shelf shelf = new Shelf();
        shelf.setShelfName(shelfName);
        shelf.setUsers(users);
        shelf.setDeleted(false);
        shelf.setCreatedOn(new Date());
        shelf.setUpdatedOn(new Date());
        shelfRepository.save(shelf);
    }

    @Transactional
    public void renameShelf(Users users, String oldShelfName, String newShelfName) throws TakeNoteGenericException {
        if (shelfRepository.countByShelfNameAndUserName(newShelfName, users.getUserName()) > 0) {
            throw new TakeNoteGenericException(String.format("New shelf name %s already exists", newShelfName), DATA_ALREADY_EXISTS);
        }
        Shelf shelf = shelfRepository.findByNameAndUserName(oldShelfName, users.getUserName());
        if (shelf == null) {
            throw new TakeNoteGenericException(String.format("Original shelf name %s is not found", oldShelfName), DATA_NOT_FOUND);
        }
        shelf.setShelfName(newShelfName);
        shelf.setUpdatedOn(new Date());
        shelfRepository.save(shelf);
    }
}
