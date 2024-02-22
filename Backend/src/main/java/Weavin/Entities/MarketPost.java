package Weavin.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class MarketPost {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @Column()
    private String productName;

    @Column()
    private String description;

    @Column()
    private Date createdAt;

    @Column()
    private Date updatedAt;

    @Column()
    private boolean isUpdated;

    @Column()
    private String photo;

    @Column()
    private int price;

    @JsonIgnore
    @Column()
    private int reports;

    @Column()
    private boolean reportStatus = false;

    @Column()
    private int views;

    @Column()
    private int likes;

    @Column()
    private boolean sold = false;

    @Column()
    private int stock;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marketPost")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "marketPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tag> tagList;

}