package com.sam.takenote.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    @Getter
    @Setter
    private Integer noteId;

    @Column(name = "note_name")
    @Getter
    @Setter
    private String noteName;

    @Column(name = "text")
    @Getter
    @Setter
    private String text;

    /*@Column(name = "notebook_id")
    @Getter
    @Setter
    private Integer noteBookId;*/

    @ManyToOne
    @JoinColumn(name = "notebook_id"/*, insertable = false, updatable = false*/)
    @Getter
    @Setter
    private NoteBook noteBook;

    @Column(name = "user_uuid")
    @Getter
    @Setter
    private UUID userUuid;

    @Column(name = "is_deleted")
    @Getter
    @Setter
    private Boolean deleted;

    @Column(name = "created_on")
    @Getter
    @Setter
    private Date createdOn;

    @Column(name = "updated_on")
    @Getter
    @Setter
    private Date updatedOn;
}
