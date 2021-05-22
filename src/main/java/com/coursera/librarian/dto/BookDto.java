package com.coursera.librarian.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class BookDto implements Serializable {
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

    private AuthorDto author;

    private GenreDto genre;

    private PublisherDto publisher;
}
