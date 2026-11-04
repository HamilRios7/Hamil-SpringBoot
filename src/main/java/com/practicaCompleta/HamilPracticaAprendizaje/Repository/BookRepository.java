package com.practicaCompleta.HamilPracticaAprendizaje.Repository;

import com.practicaCompleta.HamilPracticaAprendizaje.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // warns that this interface its a component to access data
/**
 * Al heredar de Jpa , le señalas que gestione la entidad Book con la clave primaria de tipo Long.
 * Con esto, Jpa te da los metodos ya hechos sin escribir una linea de codigo. Spring genera la implementacion
 * al arrancar la aplicacion
 */
public interface BookRepository extends JpaRepository<Book, Long> {


}
