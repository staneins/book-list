package com.kaminsky.booklist.service;

import com.kaminsky.booklist.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.kaminsky.booklist.entity.Book;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void createBook(Book book) {
        repository.save(book);
    }

    public Book getBook(Long id) {
        return repository.findById(id);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public void updateBook(Book book) {
        repository.save(book);
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }


}
