package com.sam.takenote.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Saumya Ranjan Giri
 */

@Entity
@Table(name = "users")
public class Users implements Serializable {
    @Id
    @Column(name = "user_uuid")
    @Getter
    @Setter
    private UUID userId;

    @Column(name = "username")
    @Getter
    @Setter
    private String userName;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;
}
