package com.kaminsky.booklist.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kaminsky.booklist.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository implements AbstractRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Book book) {
        String query = "INSERT INTO books (title, author, publication_year) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, book.getTitle());
    }

    @Override
    public Book findById(Long id) {
        try {
            String query = "SELECT * FROM books WHERE id = ?";
            return (Book) jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper(Book.class));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Book> findAll() {
        String query = "SELECT * FROM books";

        List<Book> books = jdbcTemplate.query(query, new BeanPropertyRowMapper(Book.class));

        return books;
    }

    @Override
    public void deleteById(Long id) {
    String query = "DELETE FROM books WHERE id = ?";
    try {
        jdbcTemplate.update(query, id);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
