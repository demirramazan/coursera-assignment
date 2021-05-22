package com.coursera.librarian.service.imp;

import com.coursera.librarian.converter.BorrowerConverter;
import com.coursera.librarian.dto.BorrowerDto;
import com.coursera.librarian.error.AlreadyEntityException;
import com.coursera.librarian.error.NotFoundException;
import com.coursera.librarian.model.Borrower;
import com.coursera.librarian.repository.BorrowerRepository;
import com.coursera.librarian.request.BorrowerRequest;
import com.coursera.librarian.service.BorrowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowerServiceImpl implements BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private final BorrowerConverter borrowerConverter;

    @Override
    public BorrowerDto getBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageFormat.format("Borrower is not found id : {0}", id)));
        return borrowerConverter.convert(borrower);
    }

    @Override
    public BorrowerDto getBorrowerByName(String name) {
        Borrower borrower = borrowerRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("{0} name Not Found Borrower", name)));

        return borrowerConverter.convert(borrower);
    }

    @Override
    public List<BorrowerDto> getAllBorrower() {
        return borrowerRepository.findAll().stream().map(borrowerConverter::convert).collect(Collectors.toList());
    }

    @Override
    public BorrowerDto saveBorrower(BorrowerRequest request) {
        Optional<Borrower> borrowerIsExist = borrowerRepository.findByName(request.getName());
        if (borrowerIsExist.isPresent()) {
            throw new AlreadyEntityException(MessageFormat.format("{0} Borrower already saved", request.getName()));
        }
        Borrower borrower = Borrower.builder().name(request.getName()).build();
        return borrowerConverter.convert(borrowerRepository.save(borrower));
    }

    @Override
    public BorrowerDto updateBorrower(BorrowerRequest request, Long id) {
        Borrower updatedBorrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Not Found Borrower {0}", id)));
        updatedBorrower.setName(request.getName());

        return borrowerConverter.convert(borrowerRepository.save(updatedBorrower));
    }

    @Override
    public void deleteBorrower(Long id) {
        try {
            borrowerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(String.format("Borrower Not Found id: %d", id));
        }
    }

    @Override
    public void deleteAllBorrower() {
        borrowerRepository.deleteAll();
    }
}
