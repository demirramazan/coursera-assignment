package com.coursera.librarian.repository;

import com.coursera.librarian.model.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    @Query("from BookLoan bl where bl.book.name like %:bookName% ")
    List<BookLoan> findByBook_Name(String bookName);

    List<BookLoan> findByBook_Id(Long bookId);

    List<BookLoan> findByBorrower_Id(Long borrowerId);

    @Query("from BookLoan  where dueDate between ?1 and ?2")
    List<BookLoan> findByDueDateBetween(LocalDate startDate, LocalDate endDate);
}
