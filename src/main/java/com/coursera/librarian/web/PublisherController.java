package com.coursera.librarian.web;

import com.coursera.librarian.dto.PublisherDto;
import com.coursera.librarian.request.PublisherRequest;
import com.coursera.librarian.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> getPublisher(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.getPublisher(id));
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<PublisherDto> getPublisherByName(@PathVariable String name) {
        return ResponseEntity.ok(publisherService.getPublisherByName(name));
    }

    @GetMapping
    public ResponseEntity<List<PublisherDto>> getAllPublisher() {
        return ResponseEntity.ok(publisherService.getAllPublisher());
    }

    @PostMapping
    public ResponseEntity<PublisherDto> savePublisher(@Valid @RequestBody PublisherRequest publisherRequest) {
        return new ResponseEntity<>(publisherService.savePublisher(publisherRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDto> updatePublisher(@Valid @RequestBody PublisherRequest publisherRequest, @RequestParam Long id) {
        return new ResponseEntity<>(publisherService.updatePublisher(publisherRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAllPublisher() {
        publisherService.deleteAllPublisher();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
