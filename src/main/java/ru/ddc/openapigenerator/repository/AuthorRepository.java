package ru.ddc.openapigenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.openapigenerator.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
