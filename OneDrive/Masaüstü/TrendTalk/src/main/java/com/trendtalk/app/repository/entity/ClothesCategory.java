package com.trendtalk.app.repository.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "clothes_category")
public class ClothesCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clothes_category_gen")
    @SequenceGenerator(name = "clothes_category_gen", sequenceName = "clothes_category_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "name", nullable = false)
    private String name;
}
