package com.sam.takenote.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author Saumya Ranjan Giri
 */

@Entity
@Table(name = "tags")
@Data
public class Tag {
    @Id
    @Column(name = "tag_id")
    private Integer tagId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private Users users;
}
