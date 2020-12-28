package com.sam.takenote.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author Saumya Ranjan Giri
 */

@Entity
@Table(name = "notebook")
@Data
public class NoteBook {
    @Id
    @Column(name = "notebook_id")
    private Integer noteBookId;

    @Column(name = "notebook_name")
    private String noteBookName;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    @Column(name = "user_uuid")
    private UUID userUuid;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;
}
