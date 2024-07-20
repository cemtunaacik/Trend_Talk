package com.trendtalk.app.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clothes")
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clothes_gen")
    @SequenceGenerator(name = "clothes_gen", sequenceName = "clothes_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
}
