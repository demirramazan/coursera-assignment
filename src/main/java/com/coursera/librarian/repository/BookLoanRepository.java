package com.coursera.librarian.repository;

import com.coursera.librarian.model.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    List<BookLoan> findByBook_Name(String bookName);

    @Query("from BookLoan  where dueDate between ?1 and ?2")
    List<BookLoan> findByDueDateBetween(LocalDate startDate, LocalDate endDate);
}
