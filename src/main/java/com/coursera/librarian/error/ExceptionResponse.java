package com.coursera.librarian.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private int resultCode;
    private String resultMessage;
    private String resultMessageDetail;
}
