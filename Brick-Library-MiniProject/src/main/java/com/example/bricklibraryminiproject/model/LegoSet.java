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
    private String theme;

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

    public LegoSet(Long id, String name, String theme, int pieceCount, int releaseYear) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.pieceCount = pieceCount;
        this.releaseYear = releaseYear;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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
