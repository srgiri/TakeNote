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
@Table(name = "shelf")
@Data
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelf_id")
    @Getter
    @Setter
    private Integer shelfId;

    @Column(name = "shelf_name")
    @Getter
    @Setter
    private String shelfName;

    @Column(name = "is_deleted")
    @Getter
    @Setter
    private Boolean deleted;

    /*@Column(name = "user_uuid")
    @Getter
    @Setter
    private UUID userUuid;*/

    @ManyToOne
    @JoinColumn(name = "user_uuid"/*, insertable = false, updatable = false*/)
    @Getter
    @Setter
    private Users users;

    @Column(name = "created_on")
    @Getter
    @Setter
    private Date createdOn;

    @Column(name = "updated_on")
    @Getter
    @Setter
    private Date updatedOn;
}
