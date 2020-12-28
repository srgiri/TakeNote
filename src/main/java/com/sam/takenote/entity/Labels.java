package com.sam.takenote.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Saumya Ranjan Giri
 */

@Entity
@Table(name = "note")
@Data
public class Labels {
    @Id
    @Column(name = "label_id")
    private Integer labelId;

    @Column(name = "label_name")
    private String labelName;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private Users users;
}
