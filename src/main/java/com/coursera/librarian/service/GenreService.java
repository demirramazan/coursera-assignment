package com.coursera.librarian.service;

import com.coursera.librarian.dto.GenreDto;
import com.coursera.librarian.request.GenreRequest;

import java.util.List;

public interface GenreService {
    GenreDto getGenre(Long id);

    GenreDto getGenreByName(String name);

    List<GenreDto> getAll();

    GenreDto saveGenre(GenreRequest genre);

    GenreDto updateGenre(GenreRequest genre, Long id);

    void deleteGenre(Long id);
}
