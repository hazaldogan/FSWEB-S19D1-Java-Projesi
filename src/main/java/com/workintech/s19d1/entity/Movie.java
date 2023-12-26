package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="movie",schema = "fsweb")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="directorName")
    private String directorName;

    @Column(name="rating")
    private int rating;

    @Column(name="release_date")
    private LocalDate releaseDate;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="movie_actor", schema="fsweb", joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id"))
    private List<Actor> actorList;

    public void addActor(Actor actor){
        if(actorList == null){
            actorList = new ArrayList<>();
        }
        actorList.add(actor);
    }
}
