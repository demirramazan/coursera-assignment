package com.coursera.librarian.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowerRequest {
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;
}
