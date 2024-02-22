package Weavin.Entities;

import Weavin.Enums.Field;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.error.Mark;

@Entity
@Getter
@Setter
@Table(name = "TAGS")
public class Tag {

    @Id
    @GeneratedValue
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ForumPost forumPost;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private MarketPost marketPost;

    @Enumerated(EnumType.STRING)
    @Column
    private Field field;

}
