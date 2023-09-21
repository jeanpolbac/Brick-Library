package com.example.bricklibraryminiproject.model;

import javax.persistence.*;

@Entity
@Table(name = "themes")
public class Theme {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Theme() {
    }

    public Theme(Long id, String name) {
        this.id = id;
        this.name = name;
    }


}
