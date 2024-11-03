package ru.ddc.openapigenerator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.openapigenerator.api.BooksApiDelegate;
import ru.ddc.openapigenerator.model.BookDto;
import ru.ddc.openapigenerator.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController implements BooksApiDelegate {
    private final BookService bookService;

    @Override
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Override
    public ResponseEntity<BookDto> getBookById(Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Override
    public ResponseEntity<BookDto> updateBook(Long id, BookDto bookDto) {
        return ResponseEntity.ok(bookService.update(id, bookDto));
    }

    @Override
    public ResponseEntity<Void> deleteBook(Long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }
}
