package com.sam.takenote.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Saumya Ranjan Giri
 */

@Entity
@Table(name = "shelf")
@Data
public class Shelf {
    @Id
    @Column(name = "shelf_id")
    private Integer shelfId;

    @Column(name = "shelf_name")
    private String shelfName;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private Users users;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;
}
