package com.example.bookmanager.repo;

import com.example.bookmanager.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
