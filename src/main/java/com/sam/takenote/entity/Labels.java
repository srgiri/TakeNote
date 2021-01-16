package com.sam.takenote.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Saumya Ranjan Giri
 */

@Entity
@Table(name = "note")
@Data
public class Labels {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "label_id")
    @Getter
    @Setter
    private Integer labelId;

    @Column(name = "label_name")
    @Getter
    @Setter
    private String labelName;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    @Getter
    @Setter
    private Users users;
}
