package com.example.springintroexercise.repositoies;

import com.example.springintroexercise.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

//    @Query("select a from Author as a having")
//    List<Author> getAllAuthorsWithAtleastOneBookBefore1990();
}
