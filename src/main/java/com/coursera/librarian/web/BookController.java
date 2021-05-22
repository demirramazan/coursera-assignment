package com.coursera.librarian.web;

import com.coursera.librarian.dto.BookDto;
import com.coursera.librarian.request.BookRequest;
import com.coursera.librarian.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @GetMapping("/byName")
    public ResponseEntity<BookDto> getBookByName(@RequestParam String name) {
        return ResponseEntity.ok(bookService.getBookByName(name));
    }
    @GetMapping("/byGenre/{genreId}")
    public ResponseEntity<List<BookDto>> getBookByName(@PathVariable Long genreId) {
        return ResponseEntity.ok(bookService.getBookByGenre(genreId));
    }
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBook() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PostMapping
    public ResponseEntity<BookDto> saveBook(@Valid @RequestBody BookRequest BookRequest) {
        return new ResponseEntity<>(bookService.saveBook(BookRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookRequest BookRequest, @RequestParam Long id) {
        return new ResponseEntity<>(bookService.updateBook(BookRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity deleteAllBook() {
        bookService.deleteAllBook();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
