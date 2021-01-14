package com.sam.takenote.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Saumya Ranjan Giri
 */

@Entity
@Table(name = "note")
@Data
public class TagNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_note_id")
    @Getter
    @Setter
    private Integer tagNoteId;

    @Column(name = "tag_id")
    @Getter
    @Setter
    private Integer tagId;

    @Column(name = "note_id")
    @Getter
    @Setter
    private Integer noteId;

    @Column(name = "user_uuid")
    @Getter
    @Setter
    private UUID userUuid;

    @ManyToOne
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    @Getter
    @Setter
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "note_id", insertable = false, updatable = false)
    @Getter
    @Setter
    private Note note;

    @ManyToOne
    @JoinColumn(name = "user_uuid", insertable = false, updatable = false)
    @Getter
    @Setter
    private Users users;
}
