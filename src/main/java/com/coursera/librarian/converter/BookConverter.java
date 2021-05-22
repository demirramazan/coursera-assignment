package com.coursera.librarian.converter;

import com.coursera.librarian.dto.BookDto;
import com.coursera.librarian.model.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookConverter implements Converter<Book, BookDto> {
    private final AuthorConverter authorConverter;
    private final PublisherConverter publisherConverter;
    private final GenreConverter genreConverter;

    public BookConverter(AuthorConverter authorConverter, PublisherConverter publisherConverter, GenreConverter genreConverter) {
        this.authorConverter = authorConverter;
        this.publisherConverter = publisherConverter;
        this.genreConverter = genreConverter;
    }

    @Override
    public BookDto convert(Book book) {
        BookDto bookDto = BookDto.builder().id(book.getId())
                .name(book.getName())
                .available(book.getAvailable())
                .description(book.getDescription())
                .edition(book.getEdition())
                .imageUrl(book.getImageUrl())
                .isbn(book.getIsbn()).numberOfPage(book.getNumberOfPage())
                .publishDate(book.getPublishDate())
                .rating(book.getRating())
                .author(authorConverter.convert(book.getAuthor()))
                .genre(genreConverter.convert(book.getGenre()))
                .publisher(publisherConverter.convert(book.getPublisher()))
                .build();

        return bookDto;
    }
}
