package com.libreria.jorge.controller;

import com.libreria.jorge.model.Libro;
import com.libreria.jorge.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // CRUD Básico
    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibro(@PathVariable Long id) {
        return libroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroService.guardar(libro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        try {
            return ResponseEntity.ok(libroService.actualizar(id, libro));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints con Query Params
    @GetMapping("/buscar/autor")
    public List<Libro> buscarPorAutor(@RequestParam String autor) {
        return libroService.buscarPorAutor(autor);
    }

    @GetMapping("/buscar/genero")
    public List<Libro> buscarPorGenero(@RequestParam String genero) {
        return libroService.buscarPorGenero(genero);
    }

    @GetMapping("/buscar/precio")
    public List<Libro> buscarPorPrecioMenor(@RequestParam Double precio) {
        return libroService.buscarPorPrecioMenor(precio);
    }

    @GetMapping("/buscar/stock")
    public List<Libro> buscarConStockMayor(@RequestParam Integer stock) {
        return libroService.buscarConStockMayor(stock);
    }
}
