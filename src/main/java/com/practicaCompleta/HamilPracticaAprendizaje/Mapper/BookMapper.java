package com.practicaCompleta.HamilPracticaAprendizaje.Mapper;

import com.practicaCompleta.HamilPracticaAprendizaje.Model.BookDto;
import com.practicaCompleta.HamilPracticaAprendizaje.Model.CreateBookDto;
import com.practicaCompleta.HamilPracticaAprendizaje.Repository.Entity.Book;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;


@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookdtoToBook (BookDto bookDto);

    List<BookDto> bookdtoToBookDto (List<Book> books);

    BookDto booktoBookDto (Book book);

    Book createBookDtoToBook (CreateBookDto createBookDto);

}
