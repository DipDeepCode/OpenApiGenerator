package ru.ddc.openapigenerator.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ddc.openapigenerator.entity.Author;
import ru.ddc.openapigenerator.entity.Book;
import ru.ddc.openapigenerator.model.AuthorDto;
import ru.ddc.openapigenerator.model.BookDto;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<AuthorDto, Author> authorTypeMap = modelMapper.createTypeMap(AuthorDto.class, Author.class);
        authorTypeMap.addMappings(mapper -> mapper.skip(Author::setId));

        TypeMap<BookDto, Book> bookTypeMap = modelMapper.createTypeMap(BookDto.class, Book.class);
        bookTypeMap.addMappings(mapper -> mapper.skip(Book::setId));

        return modelMapper;
    }
}
