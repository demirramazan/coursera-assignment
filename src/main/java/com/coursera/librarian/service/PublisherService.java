package com.coursera.librarian.service;

import com.coursera.librarian.dto.PublisherDto;
import com.coursera.librarian.request.AuthorRequest;
import com.coursera.librarian.request.PublisherRequest;

import java.util.List;

public interface PublisherService {
    PublisherDto getPublisher(Long id);

    PublisherDto getPublisherByName(String name);

    List<PublisherDto> getAllPublisher();

    PublisherDto savePublisher(PublisherRequest request);

    PublisherDto updatePublisher(PublisherRequest request, Long id);

    void deletePublisher(Long id);

    void deleteAllPublisher();
}
