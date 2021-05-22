package com.coursera.librarian.web;

import com.coursera.librarian.dto.AuthorDto;
import com.coursera.librarian.request.AuthorRequest;
import com.coursera.librarian.service.AuthorService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
@Api(value="AuthorRestController",description="Authors Rest Operations Controller")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<AuthorDto> getAuthorByName(@PathVariable String name) {
        return ResponseEntity.ok(authorService.getAuthorByName(name));
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthor() {
        return ResponseEntity.ok(authorService.getAllAuthor());
    }

    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@Valid @RequestBody AuthorRequest AuthorRequest) {
        return new ResponseEntity<>(authorService.saveAuthor(AuthorRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@Valid @RequestBody AuthorRequest AuthorRequest, @RequestParam Long id) {
        return new ResponseEntity<>(authorService.updateAuthor(AuthorRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAllAuthor() {
        authorService.deleteAllAuthor();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
