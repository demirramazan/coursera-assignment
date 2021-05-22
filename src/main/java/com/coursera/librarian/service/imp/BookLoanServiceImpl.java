package com.coursera.librarian.service.imp;

import com.coursera.librarian.converter.BookLoanConverter;
import com.coursera.librarian.dto.BookLoanDto;
import com.coursera.librarian.repository.BookLoanRepository;
import com.coursera.librarian.request.BookLoanRequest;
import com.coursera.librarian.service.BookLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookLoanConverter bookLoanConverter;

    @Override
    public BookLoanDto getBookLoan(Long id) {
        return bookLoanConverter.convert(bookLoanRepository.getOne(id));
    }

    @Override
    public List<BookLoanDto> getBookByBookName(String bookName) {
        bookLoanRepository.findByBook_Name(bookName);
        return null;
    }

    @Override
    public BookLoanDto getBookByBookId(Long bookId) {
        return null;
    }

    @Override
    public BookLoanDto getBookByBorrowerId(Long borrowerId) {
        return null;
    }

    @Override
    public BookLoanDto getBookByBorrowerId(String borrowerName) {
        return null;
    }

    @Override
    public List<BookLoanDto> getBookLoansByDueDate(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<BookLoanDto> getAllBookLoan() {
        return null;
    }

    @Override
    public BookLoanDto saveBookLoan(BookLoanRequest bookLoanRequest) {
        return null;
    }

    @Override
    public BookLoanDto updateBookLoan(BookLoanRequest request, Long id) {
        return null;
    }

    @Override
    public void deleteBookLoan(Long id) {

    }
}
