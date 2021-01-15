package com.sam.takenote.repository;

import com.sam.takenote.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<Users, UUID> {
    Long countByUserName(String username);

    Long countByUserNameAndPassword(String username, String password);
}