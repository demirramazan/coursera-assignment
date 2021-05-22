package com.coursera.librarian.request;

import com.coursera.librarian.model.Author;
import com.coursera.librarian.model.Genre;
import com.coursera.librarian.model.Publisher;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    private Long id;

    private String name;

    private String description;

    private String isbn;

    private Integer numberOfPage;

    private Boolean available;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    private Integer edition;

    private Integer rating;

    private String imageUrl;

    private Author author;

    private Genre genre;

    private Publisher publisher;
}
