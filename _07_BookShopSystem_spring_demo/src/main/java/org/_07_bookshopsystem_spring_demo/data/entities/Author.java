package org._07_bookshopsystem_spring_demo.data.entities;

import jakarta.persistence.*;
import org._07_bookshopsystem_spring_demo.data.entities.Base.BaseEntity;

import java.util.Set;

@Entity
@Table(name = "Athors")
public class Author extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER)
    private Set<Book> books;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author() {}

    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }


}
