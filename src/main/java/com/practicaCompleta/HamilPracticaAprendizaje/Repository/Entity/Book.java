package com.practicaCompleta.HamilPracticaAprendizaje.Repository.Entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //Means you can save this object as a row in the data base
@Table(name="libros")  //joins this class with the table of Oracle call libros
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Book entity representing a book in the library")
public class Book {

    @Id //defined as the primary key of the table
    @GeneratedValue(strategy= GenerationType.AUTO) // indicates that the value of the primary key will increase automatically
    private Long id;


    @Column(nullable = false) //specifies that this column doesnt accept null values

    private String titulo;

    @Column(nullable = false)
    private String autor;

    private Integer stock;
    private Double precio;



}
