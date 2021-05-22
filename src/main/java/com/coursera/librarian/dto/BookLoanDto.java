package com.coursera.librarian.dto;

import com.coursera.librarian.model.Book;
import com.coursera.librarian.model.Borrower;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookLoanDto implements Serializable {
    private Long id;

    private LocalDate dateOfPurchase;

    private LocalDate dateOfArrival;

    private LocalDate dueDate;

    private Book book;

    private Borrower borrower;
}
