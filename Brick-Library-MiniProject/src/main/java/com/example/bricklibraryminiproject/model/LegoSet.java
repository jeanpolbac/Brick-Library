package com.example.bricklibraryminiproject.model;

import javax.persistence.*;

@Entity
@Table(name = "legosets")
public class LegoSet {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String theme;

    @Column
    private int pieceCount;

    @Column
    private int releaseYear;


    public LegoSet() {
    }

    public LegoSet(Long id, String name, String theme, int pieceCount, int releaseYear) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.pieceCount = pieceCount;
        this.releaseYear = releaseYear;
    }
}
