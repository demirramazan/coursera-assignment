package com.coursera.librarian.converter;

import com.coursera.librarian.dto.AuthorDto;
import com.coursera.librarian.model.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter implements Converter<Author, AuthorDto> {
    @Override
    public AuthorDto convert(Author author) {
        return AuthorDto.builder()
                .id(author.getId()).name(author.getName())
                .build();
    }
}
