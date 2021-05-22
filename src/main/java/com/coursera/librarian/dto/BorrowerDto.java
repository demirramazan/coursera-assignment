package com.coursera.librarian.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowerDto implements Serializable {
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;
}
