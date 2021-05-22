package com.coursera.librarian.service;

import com.coursera.librarian.dto.BookDto;
import com.coursera.librarian.request.BookRequest;

import java.util.List;

public interface BookService {
    BookDto getBook(Long id);

    BookDto getBookByName(String name);

    List<BookDto> getAllBook();

    BookDto saveBook(BookRequest request);

    BookDto updateBook(BookRequest request, Long id);

    void deleteBook(Long id);

    void deleteAllBook();

    List<BookDto> getBookByGenre(Long genreId);
}
