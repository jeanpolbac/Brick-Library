package com.example.bricklibraryminiproject.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "themes")
public class Theme {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    @OneToMany(mappedBy = "theme", orphanRemoval = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LegoSet>legoSetList;

    @ManyToOne
    @JoinColumn(name = "user-id")
    private User user;

    public Theme() {
    }

    public Theme(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
