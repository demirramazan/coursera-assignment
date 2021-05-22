package com.coursera.librarian.request;

import com.coursera.librarian.model.Book;
import com.coursera.librarian.model.Borrower;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookLoanRequest {
    private Long id;

    private LocalDate dateOfPurchase;

    private LocalDate dateOfArrival;

    private LocalDate dueDate;

    private Book book;

    private Borrower borrower;
}
