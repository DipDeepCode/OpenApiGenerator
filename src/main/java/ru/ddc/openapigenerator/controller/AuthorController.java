package ru.ddc.openapigenerator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.openapigenerator.api.AuthorsApiDelegate;
import ru.ddc.openapigenerator.model.AuthorDto;
import ru.ddc.openapigenerator.model.BookDto;
import ru.ddc.openapigenerator.service.AuthorService;
import ru.ddc.openapigenerator.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController implements AuthorsApiDelegate {
    private final AuthorService authorService;
    private final BookService bookService;

    @Override
    public ResponseEntity<AuthorDto> createAuthor(AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.save(authorDto));
    }

    @Override
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @Override
    public ResponseEntity<AuthorDto> getAuthorById(Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @Override
    public ResponseEntity<AuthorDto> updateAuthor(Long id, AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.update(id, authorDto));
    }

    @Override
    public ResponseEntity<Void> deleteAuthor(Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<AuthorDto> addBook(Long authorId, BookDto bookDto) {
        return ResponseEntity.ok(authorService.addBook(authorId, bookDto));
    }
}
