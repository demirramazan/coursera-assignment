package com.coursera.librarian.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book_loan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookLoan implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_bookloan_gen", sequenceName = "seq_bookloan", allocationSize = 1)
    @GeneratedValue(generator = "seq_bookloan_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @Column(name = "date_of_arrival")
    private LocalDate dateOfArrival;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false,
             foreignKey = @ForeignKey(name = "FK_BOOK"))
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id", referencedColumnName = "id", nullable = false,
             foreignKey = @ForeignKey(name = "FK_BORROWER"))
    private Borrower borrower;

}
