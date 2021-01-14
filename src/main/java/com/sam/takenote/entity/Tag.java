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
@Table(name = "tags")
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    @Getter
    @Setter
    private Integer tagId;

    @Column(name = "tag_name")
    @Getter
    @Setter
    private String tagName;

    @Column(name = "user_uuid")
    @Getter
    @Setter
    private UUID userUuid;

    @ManyToOne
    @JoinColumn(name = "user_uuid", insertable = false, updatable = false)
    @Getter
    @Setter
    private Users users;
}
