package com.coursera.librarian.converter;

import com.coursera.librarian.dto.GenreDto;
import com.coursera.librarian.model.Genre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter implements Converter<Genre, GenreDto> {
    @Override
    public GenreDto convert(Genre genre) {
        return GenreDto.builder()
                .id(genre.getId()).name(genre.getName())
                .build();
    }

}
