package com.coursera.librarian.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_book_gen", sequenceName = "seq_book", allocationSize = 1)
    @GeneratedValue(generator = "seq_book_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "BOOK_NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "isbn", length = 50)
    private String isbn;

    @Column(name = "number_of_page")
    private Integer numberOfPage;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "edition")
    private Integer edition;

    @Size(min = 1, max = 10, message = "Rating must be between 1 and 10.")
    @Column(name = "rating")
    private Integer rating;

    @Column(name = "image_url",length = 100)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

}
