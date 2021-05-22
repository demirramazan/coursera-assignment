package com.coursera.librarian.web;

import com.coursera.librarian.dto.BookLoanDto;
import com.coursera.librarian.request.BookLoanRequest;
import com.coursera.librarian.service.BookLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("bookloan")
@RequiredArgsConstructor
public class BookLoanController {
    private final BookLoanService bookLoanService;

    @GetMapping("/{id}")
    public ResponseEntity<BookLoanDto> getBookLoan(@PathVariable Long id) {
        return ResponseEntity.ok(bookLoanService.getBookLoan(id));
    }


    @GetMapping
    public ResponseEntity<List<BookLoanDto>> getAllBookLoan(PageRequest pageRequest) {
        return ResponseEntity.ok(bookLoanService.getAllBookLoan());
    }

    @PostMapping
    public ResponseEntity<BookLoanDto> saveBook(@Valid @RequestBody BookLoanRequest bookLoanRequest) {
        return new ResponseEntity<>(bookLoanService.saveBookLoan(bookLoanRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookLoanDto> updateBook(@Valid @RequestBody BookLoanRequest BookRequest, @RequestParam Long id) {
        return new ResponseEntity<>(bookLoanService.updateBookLoan(BookRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        bookLoanService.deleteBookLoan(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
