package com.example.bricklibraryminiproject.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "legosets")
public class LegoSet {
    private Long id;

    private String name;
    private String theme;
    private int pieceCount;
    private int releaseYear;

}
