package com.coursera.librarian.service.imp;

import com.coursera.librarian.converter.GenreConverter;
import com.coursera.librarian.request.GenreRequest;
import com.coursera.librarian.service.GenreService;
import com.coursera.librarian.web.GenreController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreServiceIntegrationTest {

    @InjectMocks
    private GenreController genreController;

    @MockBean
    private GenreConverter converter;
    @MockBean
    private GenreService genreService;
    @Autowired
    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mvc = MockMvcBuilders.standaloneSetup(genreController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void postSaveNewGenre() throws Exception {
        GenreRequest genrereq = GenreRequest.builder().name("Fantasy").build();

        MvcResult result = mvc
                .perform(post("/genres")
                        .content(objectMapper.writeValueAsString(genrereq)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String responseMessage = response.getContentAsString();
    }
}
