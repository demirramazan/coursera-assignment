package com.coursera.librarian.web;

import com.coursera.librarian.dto.BorrowerDto;
import com.coursera.librarian.request.BorrowerRequest;
import com.coursera.librarian.service.BorrowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("borrower")
@RequiredArgsConstructor
public class BorrowerController {
    private final BorrowerService borrowerService;

    @GetMapping("/{id}")
    public ResponseEntity<BorrowerDto> getBorrower(@PathVariable Long id) {
        return ResponseEntity.ok(borrowerService.getBorrower(id));
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<BorrowerDto> getBorrowerByName(@PathVariable String name) {
        return ResponseEntity.ok(borrowerService.getBorrowerByName(name));
    }

    @GetMapping
    public ResponseEntity<List<BorrowerDto>> getAllBorrower() {
        return ResponseEntity.ok(borrowerService.getAllBorrower());
    }

    @PostMapping
    public ResponseEntity<BorrowerDto> saveBorrower(@Valid @RequestBody BorrowerRequest BorrowerRequest) {
        return new ResponseEntity<>(borrowerService.saveBorrower(BorrowerRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowerDto> updateBorrower(@Valid @RequestBody BorrowerRequest BorrowerRequest, @RequestParam Long id) {
        return new ResponseEntity<>(borrowerService.updateBorrower(BorrowerRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBorrower(@PathVariable Long id) {
        borrowerService.deleteBorrower(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAllBorrower() {
        borrowerService.deleteAllBorrower();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
