package com.coursera.librarian.service.imp;

import com.coursera.librarian.converter.GenreConverter;
import com.coursera.librarian.dto.GenreDto;
import com.coursera.librarian.error.AlreadyEntityException;
import com.coursera.librarian.error.NotFoundException;
import com.coursera.librarian.model.Genre;
import com.coursera.librarian.repository.GenreRepository;
import com.coursera.librarian.request.GenreRequest;
import com.coursera.librarian.service.GenreService;
import com.coursera.librarian.util.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreConverter genreConverter;
    private final MessageService messageService;

    @Override
    public GenreDto getGenre(Long id) {
        final String errorMessage = messageService.getMessage("genre.notfoundbyid", id);
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(errorMessage));
        return genreConverter.convert(genre);
    }

    @Override
    public GenreDto getGenreByName(String name) {
        final String errorMessage = messageService.getMessage("genre.notfoundbyname", name);
        Genre genre = genreRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(errorMessage));

        return genreConverter.convert(genre);
    }

    @Override
    public List<GenreDto> getAll() {
        List<Genre> genreList = genreRepository.findAll();
        List<GenreDto> genreDtoList = new ArrayList<>();
        genreList.forEach(genre -> {
            genreDtoList.add(genreConverter.convert(genre));
        });

        return genreDtoList;
    }

    @Override
    public GenreDto saveGenre(GenreRequest genreReq) {
        final String errorMessage = messageService.getMessage("genre.alreadysaved", genreReq.getName());

        Optional<Genre> genreIsExist = genreRepository.findByName(genreReq.getName());
        if (genreIsExist.isPresent()) {
            throw new AlreadyEntityException(errorMessage);
        }
        Genre genre = Genre.builder().name(genreReq.getName()).build();
        return genreConverter.convert(genreRepository.save(genre));
    }


    @Override
    public GenreDto updateGenre(GenreRequest genreReq, Long id) {
        final String errorMessage = messageService.getMessage("genre.notfoundbyid", id);
        Genre updatedGenre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(errorMessage));
        updatedGenre.setName(genreReq.getName());

        return genreConverter.convert(genreRepository.save(updatedGenre));
    }

    @Override
    public void deleteGenre(Long id) {
        final String errorNotFoundMessage = messageService.getMessage("genre.notfoundbyid", id);
        final String errorRelationMessage = messageService.getMessage("genre.relationfound", id);
        try {
            genreRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(errorNotFoundMessage);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(errorRelationMessage);
        }
    }
}
