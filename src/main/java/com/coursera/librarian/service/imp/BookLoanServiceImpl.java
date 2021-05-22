package com.coursera.librarian.service.imp;

import com.coursera.librarian.converter.BookLoanConverter;
import com.coursera.librarian.dto.BookLoanDto;
import com.coursera.librarian.error.NotFoundException;
import com.coursera.librarian.model.Book;
import com.coursera.librarian.model.BookLoan;
import com.coursera.librarian.model.Borrower;
import com.coursera.librarian.repository.BookLoanRepository;
import com.coursera.librarian.repository.BookRepository;
import com.coursera.librarian.repository.BorrowerRepository;
import com.coursera.librarian.request.BookLoanRequest;
import com.coursera.librarian.service.BookLoanService;
import com.coursera.librarian.util.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookLoanConverter bookLoanConverter;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    private final MessageService messageService;

    @Override
    public BookLoanDto getBookLoan(Long id) {
        return bookLoanConverter.convert(bookLoanRepository.getOne(id));
    }

    @Override
    public List<BookLoanDto> getBookLoanByBookName(String bookName) {
        List<BookLoan> bookLoanList = bookLoanRepository.findByBook_Name(bookName);
        return convertToBookLoanDtoList(bookLoanList);
    }

    private List<BookLoanDto> convertToBookLoanDtoList(List<BookLoan> bookLoanList) {
        LinkedList<BookLoanDto> bookLoanDtos = new LinkedList<>();
        bookLoanList.forEach(bookLoan -> {
            bookLoanDtos.add(bookLoanConverter.convert(bookLoan));
        });
        return bookLoanDtos;
    }

    @Override
    public List<BookLoanDto> getBookLoanByBookId(Long bookId) {
        List<BookLoan> bookLoanList = bookLoanRepository.findByBook_Id(bookId);
        return convertToBookLoanDtoList(bookLoanList);
    }

    @Override
    public List<BookLoanDto> getBookByBorrowerId(Long borrowerId) {
        List<BookLoan> bookLoanList = bookLoanRepository.findByBorrower_Id(borrowerId);
        return convertToBookLoanDtoList(bookLoanList);
    }

    @Override
    public List<BookLoanDto> getBookLoansByDueDate(LocalDate startDate, LocalDate endDate) {
        List<BookLoan> bookLoanList = bookLoanRepository.findByDueDateBetween(startDate, endDate);
        return convertToBookLoanDtoList(bookLoanList);
    }

    @Override
    public List<BookLoanDto> getAllBookLoan(Pageable pageable) {
        return bookLoanRepository.findAll(pageable).stream()
                .map(bookLoanConverter::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BookLoanDto saveBookLoan(BookLoanRequest bookLoanRequest) {
        Book book = bookRepository.findById(bookLoanRequest.getBookId())
                .orElseThrow(() -> new NotFoundException(messageService.getMessage("book.notfoundbyid", bookLoanRequest.getBookId())));
        if (!book.getAvailable()) {
            throw new NotFoundException(messageService.getMessage("book.notavailable"));
        }
        Borrower borrower = borrowerRepository.findById(bookLoanRequest.getBorrowerId())
                .orElseThrow(() -> new NotFoundException(messageService.getMessage("borrower.notfoundbyid", bookLoanRequest.getBorrowerId())));

        BookLoan bookLoan = bookLoanRepository.save(convertRequestToBookLoan(bookLoanRequest, book, borrower));
        return bookLoanConverter.convert(bookLoan);
    }

    private BookLoan convertRequestToBookLoan(BookLoanRequest bookLoanRequest, Book book, Borrower borrower) {
        return BookLoan.builder().dateOfPurchase(bookLoanRequest.getDateOfPurchase())
                .dueDate(bookLoanRequest.getDueDate()).dateOfArrival(bookLoanRequest.getDateOfArrival())
                .book(book)
                .borrower(borrower)
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BookLoanDto updateBookLoan(BookLoanRequest request, Long id) {
        BookLoan bookLoan = bookLoanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageService.getMessage("bookloan.notfoundbyid", id)));
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundException(messageService.getMessage("book.notfoundbyid", request.getBookId())));

        Borrower borrower = borrowerRepository.findById(request.getBorrowerId())
                .orElseThrow(() -> new NotFoundException(messageService.getMessage("borrower.notfoundbyid", request.getBorrowerId())));

        BookLoan updatedBookLoan = convertRequestToBookLoan(request, book, borrower);
        updatedBookLoan.setId(bookLoan.getId());
        return bookLoanConverter.convert(updatedBookLoan);
    }

    @Override
    public void deleteBookLoan(Long id) {
        try {
            bookLoanRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(messageService.getMessage("bookloan.notfoundbyid", id));
        }

    }
}
