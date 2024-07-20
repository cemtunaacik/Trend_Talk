package com.trendtalk.app.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "looks")
public class Look {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "look_gen")
    @SequenceGenerator(name = "look_gen", sequenceName = "look_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
}
