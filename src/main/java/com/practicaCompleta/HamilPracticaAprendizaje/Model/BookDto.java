package com.practicaCompleta.HamilPracticaAprendizaje.Model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Book entity representing a book in the library")
public class BookDto {


    private Long id;



    @Schema(description = "The title of the book", example = "The Great Gatsby")
    private String titulo;


    private String autor;

    private Integer stock;
    private Double precio;

}
