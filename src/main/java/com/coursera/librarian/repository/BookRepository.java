package com.coursera.librarian.repository;

import com.coursera.librarian.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("from Book b, Genre g where b.genre.id=g.id and g.id=?1 ")
    List<Book> findByGenre_Id(Long genreId);

    Optional<Book> findByNameContaining(String name);

}
