package com.practicaCompleta.HamilPracticaAprendizaje.Controller;



import com.practicaCompleta.HamilPracticaAprendizaje.ExceptionHandler.NotFoundException;
import com.practicaCompleta.HamilPracticaAprendizaje.Model.BookDto;
import com.practicaCompleta.HamilPracticaAprendizaje.Model.CreateBookDto;
import com.practicaCompleta.HamilPracticaAprendizaje.Repository.Entity.Book;
import com.practicaCompleta.HamilPracticaAprendizaje.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController// indica que es un controlador REST. Esto significa que todos los métodos que
// pongas aquí devolverán datos directamente , es decir solo devolvera JSON , nada de diseños  ni mostrar
@RequestMapping("/api/libros") //quiere decir que toda peticion que entre por la ruta /api/libros sera controlada aqui
@RequiredArgsConstructor
public class BookController {


    /**
     * Spring inyecta la clase de BookService usando @Autowired a esta clase para no tener que
     * hacer new BookService()
     */
    private final BookService bookService;


    /**
     * @GetMapping: Configura este método para que responda a peticiones HTTP GET en la ruta base (/api/libros).
     *
     *Cuando hagas una petición GET a esa URL, devolverá una lista en formato JSON con todos los libros guardados en Oracle.
     * @return
     */
    @GetMapping
    public List<BookDto> findAll() {
        return bookService.findAll();
    }


    /**
     * @PostMapping: Responde a peticiones HTTP POST (usadas para crear nuevos recursos).
     *
     * @RequestBody: Le dice a Spring Boot que coja el JSON que le envíes en el cuerpo de la petición
     * (por ejemplo, con el título, autor, precio y stock) y lo convierta automáticamente en un objeto de Java de tipo Libro.
     * @param createBookDto
     * @return
     */
    @PostMapping
    public BookDto save(@RequestBody CreateBookDto createBookDto) {
        return  bookService.save(createBookDto);
    }


    /**
     * @GetMapping("/{id}"): Responde a peticiones GET que incluyan una variable en la URL, por ejemplo: /api/libros/1 (para buscar el libro con ID 1).
     *
     * @PathVariable: Extrae ese número 1 de la ruta web y se lo pasa como parámetro Long id al método de Java.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BookDto findById(@PathVariable Long id) throws NotFoundException {

        return bookService.findById(id);
    }


    /**
     * @DeleteMapping("/{id}"): Responde a peticiones HTTP DELETE a una ruta como /api/libros/1.
     * Extrae el ID con @PathVariable y le ordena al servicio que elimine ese libro de la base de datos.
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
      bookService.deleteById(id);

    }


    /**
     * @PutMapping("/{id}"): Responde a peticiones HTTP PUT a una ruta como /api/libros/1.
     *
     * @PutMapping("/{id}"): Indica que este método se ejecutará cuando alguien haga una petición HTTP de tipo PUT
     * enviando un ID en la ruta (por ejemplo: /api/libros/3).
     *
     * @PathVariable Long id: Extrae el número del ID que pusiste en la URL de la petición.
     * @param id
     * @param createBookDtoDetails
     * @return
     */
    //@PutMapping("/{id}"): Indica que este método se ejecutará cuando alguien haga una petición HTTP de tipo PUT enviando un ID en la ruta
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateLibro(@PathVariable Long id, @RequestBody CreateBookDto createBookDtoDetails) {
        try {

            BookDto libroActualizado = bookService.update(id, createBookDtoDetails);
            return ResponseEntity.ok(libroActualizado);//Si todo sale bien, empaqueta el libro actualizado y responde con un código HTTP 200 (OK).
        } catch (Exception e) {
            return ResponseEntity.notFound().build();//Si el servicio salta con error porque el libro no existe, captura la excepción y
            // devuelve un código HTTP 404 (Not Found) limpio y profesional.
        }
    }
}
