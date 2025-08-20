package com.libreria.jorge.repository;

import com.libreria.jorge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Query params personalizados
    List<Libro> findByAutorContainingIgnoreCase(String autor);

    List<Libro> findByGeneroIgnoreCase(String genero);

    List<Libro> findByPrecioLessThan(Double precio);

    List<Libro> findByStockGreaterThan(Integer stock);
}
