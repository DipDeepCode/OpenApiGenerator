package ru.ddc.openapigenerator.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.openapigenerator.entity.Book;
import ru.ddc.openapigenerator.model.BookDto;
import ru.ddc.openapigenerator.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public BookDto save(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        return modelMapper.map(save(book), BookDto.class);
    }

    Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return books
                .stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public BookDto findById(Long id) {
        return modelMapper.map(findBookById(id), BookDto.class);
    }

    @Transactional
    public BookDto update(Long id, BookDto bookDto) {
        Book book = findBookById(id);
        modelMapper.map(bookDto, book);
        return modelMapper.map(bookRepository.save(book), BookDto.class);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }
}
