package com.example.springintroexercise.repositoies;


import com.example.springintroexercise.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate realaseDate);


    @Query("select b from Book as b where concat(b.author.firstName, ' ' , b.author.lastName) = 'George Powell' " +
            "order by b.releaseDate desc, b.title asc")
    List<Book> booksFromGeorgePowell();


}
