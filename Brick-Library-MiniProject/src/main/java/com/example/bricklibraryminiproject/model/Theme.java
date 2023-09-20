package com.example.bricklibraryminiproject.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "themes")
public class Theme {
    private Long id;

    private String name;
}
