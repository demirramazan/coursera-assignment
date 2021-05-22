package com.coursera.librarian.service.imp;

import com.coursera.librarian.converter.AuthorConverter;
import com.coursera.librarian.dto.AuthorDto;
import com.coursera.librarian.error.AlreadyEntityException;
import com.coursera.librarian.error.NotFoundException;
import com.coursera.librarian.model.Author;
import com.coursera.librarian.repository.AuthorRepository;
import com.coursera.librarian.request.AuthorRequest;
import com.coursera.librarian.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;

    @Override
    public AuthorDto getAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("{0} id Author not found!", id)));
        return authorConverter.convert(author);
    }

    @Override
    public AuthorDto getAuthorByName(String name) {
        Author author = authorRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("{0} Author name not found!", name)));
        return authorConverter.convert(author);
    }

    @Override
    public List<AuthorDto> getAllAuthor() {
        return authorRepository.findAll().stream().map(authorConverter::convert).collect(Collectors.toList());
    }

    @Override
    public AuthorDto saveAuthor(AuthorRequest request) {
        Optional<Author> authorIsExist = authorRepository.findByName(request.getName());
        if (authorIsExist.isPresent()) {
            throw new AlreadyEntityException(MessageFormat.format("{0} Author already saved", request.getName()));
        }
        Author author = Author.builder().name(request.getName()).build();
        return authorConverter.convert(authorRepository.save(author));
    }

    @Override
    public AuthorDto updateAuthor(AuthorRequest request, Long id) {
        Author updatedAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Author Not Found  {0}", id)));
        updatedAuthor.setName(request.getName());

        return authorConverter.convert(authorRepository.save(updatedAuthor));
    }

    @Override
    public void deleteAuthor(Long id) {
        try {
            authorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(MessageFormat.format("Author Not Found id: {0}", id));
        }
    }

    @Override
    public void deleteAllAuthor() {
        authorRepository.deleteAll();
    }
}
