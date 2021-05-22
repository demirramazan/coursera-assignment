package com.coursera.librarian.converter;

import com.coursera.librarian.dto.BookLoanDto;
import com.coursera.librarian.model.BookLoan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookLoanConverter implements Converter<BookLoan, BookLoanDto> {
    @Override
    public BookLoanDto convert(BookLoan bookLoan) {
        return BookLoanDto.builder()
                .id(bookLoan.getId()).dateOfArrival(bookLoan.getDateOfArrival())
                .dateOfPurchase(bookLoan.getDateOfPurchase()).dueDate(bookLoan.getDueDate())
                .book(bookLoan.getBook()).borrower(bookLoan.getBorrower()).build();
    }
}
