package com.practicaCompleta.HamilPracticaAprendizaje.Model;


import jakarta.persistence.*;

@Entity //Means you can save this object as a row in the data base
@Table(name="libros")  //joins this class with the table of Oracle call libros
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

    //constructor required by JPA
    public Book() {}

    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
