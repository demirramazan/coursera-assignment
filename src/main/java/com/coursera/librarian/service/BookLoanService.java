package com.coursera.librarian.service;

import com.coursera.librarian.dto.BookLoanDto;
import com.coursera.librarian.request.BookLoanRequest;

import java.time.LocalDate;
import java.util.List;

public interface BookLoanService {

    BookLoanDto getBookLoan(Long id);

    List<BookLoanDto> getBookByBookName(String bookName);

    BookLoanDto getBookByBookId(Long bookId);

    BookLoanDto getBookByBorrowerId(Long borrowerId);

    BookLoanDto getBookByBorrowerId(String borrowerName);

    List<BookLoanDto> getBookLoansByDueDate(LocalDate startDate, LocalDate endDate);

    List<BookLoanDto> getAllBookLoan();

    BookLoanDto saveBookLoan(BookLoanRequest bookLoanRequest);

    BookLoanDto updateBookLoan(BookLoanRequest request, Long id);

    void deleteBookLoan(Long id);

}
