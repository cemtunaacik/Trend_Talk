package com.trendtalk.app.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_gen")
    @SequenceGenerator(name = "post_gen", sequenceName = "post_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "description", nullable = false, length = 5000)
    private String description;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_id")
    private Integer userId;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("username")
    private String username;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("profile_picture_url")
    private String profilePictureUrl;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<com.trendtalk.app.repository.entity.Comment> comments;

    @PostLoad
    private void postLoad() {
        if (user != null) {
            this.userId = user.getId();
            this.username = user.getNickname();
            this.profilePictureUrl = user.getProfilePictureUrl();
        }
    }
}
