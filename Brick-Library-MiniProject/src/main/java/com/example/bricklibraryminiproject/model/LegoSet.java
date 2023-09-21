package com.example.bricklibraryminiproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private int pieceCount;

    @Column
    private int releaseYear;


    @ManyToOne
    @JoinColumn(name = "theme_id")
    @JsonIgnore
    private Theme theme;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public LegoSet() {
    }


    public LegoSet(Long id, String name, int pieceCount, int releaseYear, Theme theme) {
        this.id = id;
        this.name = name;
        this.pieceCount = pieceCount;
        this.releaseYear = releaseYear;
        this.theme = theme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount) {
        this.pieceCount = pieceCount;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "LegoSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                ", pieceCount=" + pieceCount +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
