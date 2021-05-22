package com.coursera.librarian.service;

import com.coursera.librarian.dto.BorrowerDto;
import com.coursera.librarian.request.BorrowerRequest;

import java.util.List;

public interface BorrowerService {
    BorrowerDto getBorrower(Long id);

    BorrowerDto getBorrowerByName(String name);

    List<BorrowerDto> getAllBorrower();

    BorrowerDto saveBorrower(BorrowerRequest request);

    BorrowerDto updateBorrower(BorrowerRequest request, Long id);

    void deleteBorrower(Long id);

    void deleteAllBorrower();
}
