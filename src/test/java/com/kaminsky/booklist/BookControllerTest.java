package com.kaminsky.booklist;

import com.kaminsky.booklist.controller.BookController;
import com.kaminsky.booklist.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import com.kaminsky.booklist.entity.Book;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @BeforeEach
    public void setUp() {
        when(bookService.getAllBooks()).thenReturn(new ArrayList<>());

        Book book = new Book();
        book.setId(1L);
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        book.setPublicationYear(1925);

        when(bookService.getBook(1L)).thenReturn(book);

    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("The Great Gatsby"))
                .andExpect(jsonPath("$.author").value("F. Scott Fitzgerald"))
                .andExpect(jsonPath("$.publicationYear").value(1925));

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(1L);
    }

    @Test
    public void testPost() throws Exception {
        String newBookJson = """
                {
                    "title": "The Great Gatsby",
                    "author": "F. Scott Fitzgerald",
                    "publication_year": "1925"
                }
                """;

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newBookJson))
                .andExpect(status().isOk());


        verify(bookService, times(1)).createBook(argThat(book ->
                book.getTitle().equals("The Great Gatsby") &&
                        book.getAuthor().equals("F. Scott Fitzgerald")));
    }
}
