package com.coursera.librarian.converter;

import com.coursera.librarian.dto.BorrowerDto;
import com.coursera.librarian.model.Borrower;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BorrowerConverter implements Converter<Borrower, BorrowerDto> {
    @Override
    public BorrowerDto convert(Borrower borrower) {
        return BorrowerDto.builder()
                .id(borrower.getId()).name(borrower.getName())
                .email(borrower.getEmail())
                .phoneNumber(borrower.getPhoneNumber())
                .build();
    }
}
