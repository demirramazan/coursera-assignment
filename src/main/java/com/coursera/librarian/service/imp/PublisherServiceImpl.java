package com.coursera.librarian.service.imp;

import com.coursera.librarian.converter.PublisherConverter;
import com.coursera.librarian.dto.PublisherDto;
import com.coursera.librarian.error.AlreadyEntityException;
import com.coursera.librarian.error.NotFoundException;
import com.coursera.librarian.model.Publisher;
import com.coursera.librarian.repository.PublisherRepository;
import com.coursera.librarian.request.PublisherRequest;
import com.coursera.librarian.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherConverter publisherConverter;

    @Override
    public PublisherDto getPublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageFormat.format("Publisher is not found id : {0}", id)));
        return publisherConverter.convert(publisher);
    }

    @Override
    public PublisherDto getPublisherByName(String name) {
        Publisher publisher = publisherRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("%s name Not Found Publisher", name)));

        return publisherConverter.convert(publisher);
    }

    @Override
    public List<PublisherDto> getAllPublisher() {
        return publisherRepository.findAll().stream().map(publisherConverter::convert).collect(Collectors.toList());
    }

    @Override
    public PublisherDto savePublisher(PublisherRequest request) {
        Optional<Publisher> publisherIsExist = publisherRepository.findByName(request.getName());
        if (publisherIsExist.isPresent()) {
            throw new AlreadyEntityException(MessageFormat.format("%s Publisher already saved", request.getName()));
        }
        Publisher publisher = Publisher.builder().name(request.getName()).build();
        return publisherConverter.convert(publisherRepository.save(publisher));
    }

    @Override
    public PublisherDto updatePublisher(PublisherRequest request, Long id) {
        Publisher updatedPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Not Found Publisher %d", id)));
        updatedPublisher.setName(request.getName());

        return publisherConverter.convert(publisherRepository.save(updatedPublisher));
    }

    @Override
    public void deletePublisher(Long id) {
        try {
            publisherRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(MessageFormat.format("Publisher Not Found id: %d", id));
        }
    }

    @Override
    public void deleteAllPublisher() {
        publisherRepository.deleteAll();
    }
}
