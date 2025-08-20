package com.libreria.jorge.service;

import com.libreria.jorge.model.Libro;
import com.libreria.jorge.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    public Optional<Libro> buscarPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro actualizar(Long id, Libro libroActualizado) {
        return libroRepository.findById(id).map(libro -> {
            libro.setTitulo(libroActualizado.getTitulo());
            libro.setAutor(libroActualizado.getAutor());
            libro.setGenero(libroActualizado.getGenero());
            libro.setPrecio(libroActualizado.getPrecio());
            libro.setStock(libroActualizado.getStock());
            return libroRepository.save(libro);
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }

    // Métodos con query params
    public List<Libro> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Libro> buscarPorGenero(String genero) {
        return libroRepository.findByGeneroIgnoreCase(genero);
    }

    public List<Libro> buscarPorPrecioMenor(Double precio) {
        return libroRepository.findByPrecioLessThan(precio);
    }

    public List<Libro> buscarConStockMayor(Integer stock) {
        return libroRepository.findByStockGreaterThan(stock);
    }
}
