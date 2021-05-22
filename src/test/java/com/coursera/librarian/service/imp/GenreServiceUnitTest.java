package com.coursera.librarian.service.imp;

import com.coursera.librarian.model.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenreServiceUnitTest {

    private static List<Genre> genreList = null;

    @BeforeEach
    public void setUp() {
        genreList = new ArrayList<>();
        genreList.add(Genre.builder().id(1l).name("Fantasy").build());
        genreList.add(Genre.builder().id(2l).name("Mystery").build());
        genreList.add(Genre.builder().id(3l).name("Romance").build());
        genreList.add(Genre.builder().id(4l).name("Contemporary").build());
        genreList.add(Genre.builder().id(5l).name("Dystopian").build());
        genreList.add(Genre.builder().id(6l).name("Thriller").build());
    }

    @AfterEach
    public void tearDown() {
        genreList = new ArrayList<>();
    }

    @Test
    public void getGenre() {
        Genre genre = genreList.stream().filter(g -> g.getId().equals(1l)).findFirst().orElse(null);
        assertNotNull(genre);
        assertEquals("Fantasy", genre.getName());
    }

    @Test
    public void getGenreByName() {
        Genre genre = genreList.stream().filter(g -> g.getName().equals("Mystery")).findFirst().orElse(null);
        assertTrue(genre != null);
        assertEquals(2, genre.getId());
    }

    @Test
    public void getAll() {
        assertTrue(genreList.size() == 6);
        assertFalse(genreList.isEmpty());
    }

    @Test
    public void saveGenre() {
        Genre genre = Genre.builder().id(7l).name("Sci-Fi").build();
        assertTrue(genreList.stream().noneMatch(g -> g.getId().equals(genre.getId())));
        assertFalse(genreList.stream().anyMatch(g -> g.getName().equals(genre.getName())));
        genreList.add(genre);
    }

    @Test
    public void updateGenre() {
        Genre genre = genreList.stream().filter(g -> g.getId().equals(2L)).findFirst().orElse(null);
        assertNotNull(genre);
        genre.setName("Mystery Update");
        assertNotNull(genreList.stream().filter(g -> g.getName().equals(genre.getName())).findFirst().get());
    }

    @Test
    public void deleteGenre() {
        genreList.remove(genreList.stream().filter(g -> g.getId().equals(1L)).findFirst().get());
        assertTrue(genreList.stream().noneMatch(genre -> genre.getId().equals(1L)));
    }
}