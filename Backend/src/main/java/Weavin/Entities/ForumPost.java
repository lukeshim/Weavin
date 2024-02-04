package Weavin.Entities;
import Weavin.Enums.Field;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "FORUM_POSTS")
public class ForumPost {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column
    private String title;

    @Column
    private String body;

    @Enumerated(EnumType.STRING)
    @Column
    private Field field;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @Column
    private boolean isUpdated;

    @Column
    private int likes;

    @Column
    private int views;

    @Column
    private int reports;

    @Column
    private boolean reportStatus;

    @OneToMany(mappedBy = "forumPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<Comment> commentList;

    @OneToMany(mappedBy = "forumPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<Tag> tagList;

}