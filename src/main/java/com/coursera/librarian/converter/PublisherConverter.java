package com.coursera.librarian.converter;

import com.coursera.librarian.dto.PublisherDto;
import com.coursera.librarian.model.Publisher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PublisherConverter implements Converter<Publisher, PublisherDto> {
    @Override
    public PublisherDto convert(Publisher publisher) {
        return PublisherDto.builder()
                .id(publisher.getId()).name(publisher.getName())
                .build();
    }
}
