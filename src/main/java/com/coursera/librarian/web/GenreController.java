package com.coursera.librarian.web;

import com.coursera.librarian.dto.GenreDto;
import com.coursera.librarian.request.GenreRequest;
import com.coursera.librarian.service.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
@Api(value = "GenreRestController Genres Rest Operations")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Genre get by id", response = GenreDto.class)
    public ResponseEntity<GenreDto> getGenre(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(genreService.getGenre(id));
    }

    @GetMapping("/byName/{name}")
    @ApiOperation(value = "Genre get by name", response = GenreDto.class)
    public ResponseEntity<GenreDto> getGenreByName(@PathVariable(name = "name") String genreName) {
        return ResponseEntity.ok(genreService.getGenreByName(genreName));
    }

    @GetMapping
    @ApiOperation(value = "Get All Genre", response = List.class)
    public ResponseEntity<List<GenreDto>> getAllGenre() {
        return ResponseEntity.ok(genreService.getAll());
    }

    @PostMapping
    public ResponseEntity<GenreDto> saveGenre(@Valid @RequestBody GenreRequest genreRequest) {
        return new ResponseEntity<>(genreService.saveGenre(genreRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GenreDto> updateGenre(@Valid @RequestBody GenreRequest genreRequest, @PathVariable Long id) {
        return new ResponseEntity<>(genreService.updateGenre(genreRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
