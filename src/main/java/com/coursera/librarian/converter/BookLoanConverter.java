package com.coursera.librarian.converter;

import com.coursera.librarian.dto.BookLoanDto;
import com.coursera.librarian.model.BookLoan;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookLoanConverter implements Converter<BookLoan, BookLoanDto> {

    private final BookConverter bookConverter;
    private final BorrowerConverter borrowerConverter;
    @Override
    public BookLoanDto convert(BookLoan bookLoan) {
        return BookLoanDto.builder()
                .id(bookLoan.getId())
                .dateOfArrival(bookLoan.getDateOfArrival())
                .dateOfPurchase(bookLoan.getDateOfPurchase())
                .dueDate(bookLoan.getDueDate())
                .book(bookConverter.convert(bookLoan.getBook()))
                .borrower(borrowerConverter.convert(bookLoan.getBorrower()))
                .build();
    }
}
