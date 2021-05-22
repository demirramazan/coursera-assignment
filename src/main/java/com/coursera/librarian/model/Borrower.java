package com.coursera.librarian.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "borrower")
public class Borrower implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_borrower_gen", sequenceName = "seq_borrower", allocationSize = 1)
    @GeneratedValue(generator = "seq_borrower_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "BORROWER_NAME", length = 25, nullable = false)
    private String name;

    @Email
    @Column(name = "EMAIL", length = 25)
    private String email;

    @Column(name = "PHONE_NUMBER", length = 15)
    private String phoneNumber;
}
