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


    /**
     * One-to-many relationship with LegoSet entities where this Theme is the parent theme
     * The "mappedBy" attribute specifies the field in the LegoSet entity that maps to this theme
     * The "orphanRemoval" attribute indicates that orphaned LegoSet records won't be automatically removed
     */
    @OneToMany(mappedBy = "theme", orphanRemoval = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LegoSet>legoSetList;


    /**
     * Many-to-one relationship with the User entity, indicating that each theme is associated with a user
     * The "name" attribute specifies the name of the foreign key column used to link themes to users
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Default constructor
    public Theme() {
    }

    public Theme(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter and Setter methods
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

    public List<LegoSet> getLegoSetList() {
        return legoSetList;
    }

    public void setLegoSetList(List<LegoSet> legoSetList) {
        this.legoSetList = legoSetList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
