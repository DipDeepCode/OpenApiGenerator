package ru.ddc.openapigenerator.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.openapigenerator.entity.Author;
import ru.ddc.openapigenerator.entity.Book;
import ru.ddc.openapigenerator.model.AuthorDto;
import ru.ddc.openapigenerator.model.BookDto;
import ru.ddc.openapigenerator.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    private final BookService bookService;

    @Transactional
    public AuthorDto save(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        return modelMapper.map(authorRepository.save(author), AuthorDto.class);
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors
                .stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public AuthorDto findById(Long id) {
        return modelMapper.map(findAuthorById(id), AuthorDto.class);
    }

    @Transactional
    public AuthorDto update(Long id, AuthorDto authorDto) {
        Author author = findAuthorById(id);
        modelMapper.map(authorDto, author);
        return modelMapper.map(authorRepository.save(author), AuthorDto.class);
    }

    @Transactional
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Transactional
    public AuthorDto addBook(Long id, BookDto bookDto) {
        Author author = findAuthorById(id);
        Book book = bookService.save(modelMapper.map(bookDto, Book.class));
        System.out.println(book);
        author.addBook(book);
        Author author1 = authorRepository.save(author);
        System.out.println(author1);
        System.out.println(author1.getBooks());
        return modelMapper.map(author1, AuthorDto.class);
    }

    Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }
}
