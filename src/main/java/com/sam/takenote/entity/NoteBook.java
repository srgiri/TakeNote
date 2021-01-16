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
@Table(name = "notebook")
@Data
public class NoteBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notebook_id")
    @Getter
    @Setter
    private Integer noteBookId;

    @Column(name = "notebook_name")
    @Getter
    @Setter
    private String noteBookName;

   /* @Column(name = "shelf_id")
    @Getter
    @Setter
    private Integer shelfId;*/

    @ManyToOne
    @JoinColumn(name = "shelf_id"/*, insertable = false, updatable = false*/)
    @Getter
    @Setter
    private Shelf shelf;

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
