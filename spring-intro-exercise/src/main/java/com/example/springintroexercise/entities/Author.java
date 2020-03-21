package com.example.springintroexercise.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public Set<Book> getBooks() {
        return books;
    }






    public Author(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName= lastName;
    }


    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    private Set<Book> books;


    public Author() {
    }


        @Column(name = "first_name")
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

    @Column(name = "last_name",nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
