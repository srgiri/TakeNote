package com.sam.takenote.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author Saumya Ranjan Giri
 */

@Entity
@Table(name = "note")
@Data
public class Note {
    @Id
    @Column(name = "note_id")
    private Integer noteId;

    @Column(name = "note_name")
    private String noteName;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "notebook_id")
    private NoteBook noteBook;

    @Column(name = "user_uuid")
    private UUID userUuid;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;
}
