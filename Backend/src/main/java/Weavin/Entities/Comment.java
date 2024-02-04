package Weavin.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Comment{

    @Id
    @GeneratedValue
    private int id;

    @Column()
    private String body;

    @Column()
    private Date createAt;

    @Column()
    private Date updatedAt;

    @Column()
    private int likes;

    @Column()
    private int reports;

    @Column()
    private boolean reportStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marketPost", referencedColumnName = "id")
    private MarketPost marketPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forumPost", referencedColumnName = "id")
    private ForumPost forumPost;
}