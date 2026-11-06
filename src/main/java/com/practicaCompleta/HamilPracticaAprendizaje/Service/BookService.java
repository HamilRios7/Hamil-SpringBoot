package com.practicaCompleta.HamilPracticaAprendizaje.Service;


import com.practicaCompleta.HamilPracticaAprendizaje.ExceptionHandler.NotFoundException;
import com.practicaCompleta.HamilPracticaAprendizaje.Mapper.BookMapper;
import com.practicaCompleta.HamilPracticaAprendizaje.Model.BookDto;
import com.practicaCompleta.HamilPracticaAprendizaje.Model.CreateBookDto;
import com.practicaCompleta.HamilPracticaAprendizaje.Repository.Entity.Book;
import com.practicaCompleta.HamilPracticaAprendizaje.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//El Servicio se coloca en el medio para cumplir con varias funciones clave

// El controlador solo se encarga de recibir peticiones web (HTTP),
// y el repositorio de hablar con la base de datos. El servicio procesa la lógica

//Si varias partes de tu aplicación necesitan guardar un libro,
// toda la lógica de guardado vive en un solo sitio.



@Service //warns that this class is a service component , this makes Spring control the cycle of life
@RequiredArgsConstructor
public class BookService {


    /**
     * Spring inyecta la clase de BookRepository usando @Autowired a esta clase para no tener que
     * hacer new BookRepository()
     */

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    /**
     * Llama al método nativo findAll() de JPA para traer
     * una lista con todos los libros guardados en Oracle y devolvérsela a quien se la pida.
     * @return
     */
    // List all the books
    public List<BookDto> findAll() {

        return bookMapper.bookdtoToBookDto(bookRepository.findAll());
    }


    /**
     * Recibe un objeto libro y utiliza el método save() de JPA. Si el libro no tiene ID,
     * lo crea (INSERT); si ya tiene ID, lo actualiza (UPDATE) en la base de datos de Oracle.
     *
     * @param createBookDto
     * @return
     */
    //Save the book
    public BookDto save(CreateBookDto createBookDto) {
        Book book=bookMapper.createBookDtoToBook(createBookDto);
        return  bookMapper.booktoBookDto( bookRepository.save(book));
    }


    /**
     * Busca un libro específico por su clave primaria. Devuelve un Optional<Libro>,
     * que es una caja elegante de Java que avisa de forma segura si el libro existía o
     * si devolvió un resultado vacío (por si acaso alguien busca un ID que no existe).
     * @param id
     * @return
     */
    //Find a book by id
    public BookDto findById(Long id) throws NotFoundException {

        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            BookDto bookDto = bookMapper.booktoBookDto(book);
            return bookDto;
        } else {
            throw new NotFoundException("Not found");
        }
    }


    /**
     * Borra de la base de datos el registro que
     * coincida con ese ID utilizando el método deleteById() de JPA.
     * @param id
     */
    //Delete a book
    public void deleteById(Long id) {

        bookRepository.deleteById(id);
    }

    /**
     * Actualiza un libro existente en la base de datos.
     *
     * @param id
     * @param createBookdtoActualizado
     * @return
     */
    // Lógica para actualizar: busca el libro, cambia sus datos y los guarda
    public BookDto update(Long id, CreateBookDto createBookdtoActualizado) {
        Book bookActualizado=bookMapper.createBookDtoToBook(createBookdtoActualizado);
        return bookRepository.findById(id).map(libro -> {
            libro.setTitulo(bookActualizado.getTitulo());
            libro.setAutor(bookActualizado.getAutor());
            libro.setPrecio(bookActualizado.getPrecio());
            libro.setStock(bookActualizado.getStock());
            bookRepository.save(libro);
            return bookMapper.booktoBookDto(libro);
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }
}
