package com.sam.takenote.service;

import com.sam.takenote.entity.Shelf;
import com.sam.takenote.entity.Users;
import com.sam.takenote.exception.TakeNoteGenericException;
import com.sam.takenote.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    @Transactional
    public void createShelf(Users users, String shelfName) throws TakeNoteGenericException {
        if (shelfRepository.countByShelfNameAndUserName(shelfName, users.getUserName()) > 0) {
            throw new TakeNoteGenericException(String.format("Shelf name %s already exists", shelfName));
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
            throw new TakeNoteGenericException(String.format("New shelf name %s already exists", newShelfName));
        }
        Shelf shelf = shelfRepository.findByNameAndUserName(oldShelfName, users.getUserName());
        if (shelf == null) {
            throw new TakeNoteGenericException(String.format("Original shelf name %s is not found", oldShelfName));
        }
        shelf.setShelfName(newShelfName);
        shelf.setUpdatedOn(new Date());
        shelfRepository.save(shelf);
    }
}
