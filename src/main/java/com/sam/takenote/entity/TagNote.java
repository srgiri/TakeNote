package com.sam.takenote.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author Saumya Ranjan Giri
 */

@Entity
@Table(name = "note")
@Data
public class TagNote {
    @Id
    @Column(name = "tag_note_id")
    private Integer tagNoteId;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private Users users;
}
