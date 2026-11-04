package com.practicaCompleta.HamilPracticaAprendizaje.Service;


import com.practicaCompleta.HamilPracticaAprendizaje.Model.Book;
import com.practicaCompleta.HamilPracticaAprendizaje.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//El Servicio se coloca en el medio para cumplir con varias funciones clave

// El controlador solo se encarga de recibir peticiones web (HTTP),
// y el repositorio de hablar con la base de datos. El servicio procesa la lógica

//Si varias partes de tu aplicación necesitan guardar un libro,
// toda la lógica de guardado vive en un solo sitio.



@Service //warns that this class is a service component , this makes Spring control the cycle of life

public class BookService {


    /**
     * Spring inyecta la clase de BookRepository usando @Autowired a esta clase para no tener que
     * hacer new BookRepository()
     */
    @Autowired
    private BookRepository bookRepository;


    /**
     * Llama al método nativo findAll() de JPA para traer
     * una lista con todos los libros guardados en Oracle y devolvérsela a quien se la pida.
     * @return
     */
    // List all the books
    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    /**
     * Recibe un objeto libro y utiliza el método save() de JPA. Si el libro no tiene ID,
     * lo crea (INSERT); si ya tiene ID, lo actualiza (UPDATE) en la base de datos de Oracle.
     *
     * @param book
     * @return
     */
    //Save the book
    public Book save(Book book) {

        return bookRepository.save(book);

    }


    /**
     * Busca un libro específico por su clave primaria. Devuelve un Optional<Libro>,
     * que es una caja elegante de Java que avisa de forma segura si el libro existía o
     * si devolvió un resultado vacío (por si acaso alguien busca un ID que no existe).
     * @param id
     * @return
     */
    //Find a book by id
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }


    /**
     * Borra de la base de datos el registro que
     * coincida con ese ID utilizando el método deleteById() de JPA.
     * @param book
     */
    //Delete a book
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    /**
     * Actualiza un libro existente en la base de datos.
     *
     * @param id
     * @param libroActualizado
     * @return
     */
    // Lógica para actualizar: busca el libro, cambia sus datos y los guarda
    public Book update(Long id, Book libroActualizado) {
        return bookRepository.findById(id).map(libro -> {
            libro.setTitulo(libroActualizado.getTitulo());
            libro.setAutor(libroActualizado.getAutor());
            libro.setPrecio(libroActualizado.getPrecio());
            libro.setStock(libroActualizado.getStock());
            return bookRepository.save(libro);
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }
}
