package ru.ddc.openapigenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.openapigenerator.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
