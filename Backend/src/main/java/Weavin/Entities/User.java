package Weavin.Entities;

import Weavin.Enums.Field;
import Weavin.Enums.Presence;
import Weavin.Enums.ReportStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "USERS")
public class User {

    @Id
    @JsonIgnore
    @GeneratedValue
    private int id;

    @Column
    private String username;

    @Column
    private boolean usernameAlreadyChanged;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Date lastSeenAt;

    @Column
    private String profilePhoto;

    @Enumerated(EnumType.STRING)
    @Column
    private Presence presence;

    @Enumerated(EnumType.STRING)
    @Column
    private Field field;

    @JsonIgnore
    @Column
    private int reports;

    @Enumerated(EnumType.STRING)
    @Column
    private ReportStatus reportStatus = ReportStatus.SAFE;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<MarketPost> marketPostList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> commentList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Semester> semesterList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ForumPost> forumPostList;
}