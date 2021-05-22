package com.coursera.librarian.dto;

import com.coursera.librarian.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto implements Serializable {
    private Long id;

    private String name;

    private List<Book> books;
}
