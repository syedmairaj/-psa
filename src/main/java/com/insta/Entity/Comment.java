package com.insta.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user")
    private String user;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}