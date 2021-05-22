package com.coursera.librarian.service.imp;

import com.coursera.librarian.util.Util;
import com.coursera.librarian.converter.BookConverter;
import com.coursera.librarian.dto.BookDto;
import com.coursera.librarian.error.NotFoundException;
import com.coursera.librarian.model.Author;
import com.coursera.librarian.model.Book;
import com.coursera.librarian.model.Genre;
import com.coursera.librarian.model.Publisher;
import com.coursera.librarian.repository.AuthorRepository;
import com.coursera.librarian.repository.BookRepository;
import com.coursera.librarian.repository.GenreRepository;
import com.coursera.librarian.repository.PublisherRepository;
import com.coursera.librarian.request.BookRequest;
import com.coursera.librarian.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookConverter bookConverter;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;


    @Override
    public BookDto getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageFormat.format("Book is not found id : {0}", id)));
        return bookConverter.convert(book);
    }

    @Override
    public BookDto getBookByName(String name) {
        Book book = bookRepository.findByNameContaining(name)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("{0} name Not Found Book", name)));

        return bookConverter.convert(book);
    }

    @Override
    public List<BookDto> getBookByGenre(Long genreId) {
        List<Book> bookList = bookRepository.findByGenre_Id(genreId);
        List<BookDto> bookDtoList = Collections.emptyList();
        if (Util.isCollectionNotEmpty(bookList)) {
            bookDtoList = bookList.stream()
                    .map(bookConverter::convert).collect(Collectors.toList());
        }
        return bookDtoList;
    }

    @Override
    public List<BookDto> getAllBook() {
        return bookRepository.findAll().stream()
                .map(bookConverter::convert).collect(Collectors.toList());
    }

    @Override
    public BookDto saveBook(BookRequest request) {
        Book saveBookData = convertToBookData(request);
        return bookConverter.convert(bookRepository.save(saveBookData));
    }

    private Book convertToBookData(BookRequest request) {

        Author author = authorRepository.findById(request.getAuthor().getId())
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("{0} id author not found", request.getAuthor().getId())));

        Publisher publisher = publisherRepository.findById(request.getAuthor().getId())
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("{0} id publisher not found", request.getAuthor().getId())));

        Genre genre = genreRepository.findById(request.getAuthor().getId())
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("{0} id genre not found", request.getAuthor().getId())));

        return Book.builder().name(request.getName())
                .available(request.getAvailable())
                .description(request.getDescription())
                .edition(request.getEdition())
                .imageUrl(request.getImageUrl())
                .isbn(request.getIsbn())
                .numberOfPage(request.getNumberOfPage())
                .publishDate(request.getPublishDate())
                .rating(request.getRating())
                .author(author)
                .publisher(publisher)
                .genre(genre)
                .build();
    }

    @Override
    public BookDto updateBook(BookRequest request, Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("{0} id book not found", id)));
        Book updatedBook = updateBookConvertDate(request, book);
        return bookConverter.convert(bookRepository.save(updatedBook));
    }

    private Book updateBookConvertDate(BookRequest request, Book book) {
        Book bookUpdated = convertToBookData(request);
        bookUpdated.setId(book.getId());
        return bookUpdated;
    }

    @Override
    public void deleteBook(Long id) {
        try {
            genreRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(MessageFormat.format("Book Not Found id: {0}", id));
        }
    }

    @Override
    public void deleteAllBook() {
        genreRepository.deleteAll();
    }
}
