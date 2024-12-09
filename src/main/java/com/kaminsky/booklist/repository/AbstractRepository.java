package com.kaminsky.booklist.repository;

import com.kaminsky.booklist.entity.Book;
import java.util.List;

public interface AbstractRepository {

    void save(Book book);
    Book findById(Long id);
    List<Book> findAll();
    void deleteById(Long id);
}
