package Weavin.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "SEMESTERS")
public class Semester {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column
    private double preSuGpa;

    @Column
    private double postSuGpa;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<Course> courseList;

}
