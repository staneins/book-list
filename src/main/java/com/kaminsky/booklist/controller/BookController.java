package com.kaminsky.booklist.controller;

import com.kaminsky.booklist.service.BookService;
import org.springframework.web.bind.annotation.*;
import com.kaminsky.booklist.entity.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
            Book book = bookService.getBook(id);
            return ResponseEntity.ok().body(book);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.createBook(book);
    }

    @PutMapping("{id}")
    public void updateBook(@RequestBody Book book, @PathVariable Long id) {
        book.setId(id);
        bookService.updateBook(book);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
