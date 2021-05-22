package com.coursera.librarian.service;

import com.coursera.librarian.dto.AuthorDto;
import com.coursera.librarian.request.AuthorRequest;

import java.util.List;

public interface AuthorService {
    AuthorDto getAuthor(Long id);

    AuthorDto getAuthorByName(String name);

    List<AuthorDto> getAllAuthor();

    AuthorDto saveAuthor(AuthorRequest request);

    AuthorDto updateAuthor(AuthorRequest request, Long id);

    void deleteAuthor(Long id);

    void deleteAllAuthor();
}
