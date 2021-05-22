package com.coursera.librarian.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto implements Serializable {
    @NotNull(message = "Genre Id Not Null")
    private Long id;

    @NotNull(message = "Genre Name Not Null")
    private String name;

}
