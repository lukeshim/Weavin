package Weavin.Entities;

import Weavin.Entities.Comment;
import Weavin.Entities.ForumPost;
import Weavin.Entities.MarketPost;
import Weavin.Entities.Semester;
import Weavin.Enums.Field;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Getter
@Setter
public class User{
    @Id
    @GeneratedValue
    private int id;

    @Column()
    private String username;

    @Column()
    private String email;

    @Column()
    private String password;

    @Column()
    private Date lastSeenAt;

    @Column()
    private String profilePhoto;

    @Enumerated(EnumType.STRING)
    @Column()
    private Presence presence;

    @Enumerated(EnumType.STRING)
    @Column()
    private Field field;

    @Column()
    private int reports;


    @Enumerated(EnumType.STRING)
    @Column()
    private ReportStatus reportStatus;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private ArrayList<MarketPost> marketPostList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private ArrayList<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private ArrayList<Semester> semesterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private ArrayList<ForumPost> forumPostList;

}