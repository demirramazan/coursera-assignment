package com.coursera.librarian.service;

import com.coursera.librarian.dto.BookLoanDto;
import com.coursera.librarian.request.BookLoanRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface BookLoanService {

    BookLoanDto getBookLoan(Long id);

    List<BookLoanDto> getBookLoanByBookName(String bookName);

    List<BookLoanDto> getBookLoanByBookId(Long bookId);

    List<BookLoanDto> getBookByBorrowerId(Long borrowerId);

    List<BookLoanDto> getBookLoansByDueDate(LocalDate startDate, LocalDate endDate);

    List<BookLoanDto> getAllBookLoan(Pageable pageable);

    BookLoanDto saveBookLoan(BookLoanRequest bookLoanRequest);

    BookLoanDto updateBookLoan(BookLoanRequest request, Long id);

    void deleteBookLoan(Long id);

}
