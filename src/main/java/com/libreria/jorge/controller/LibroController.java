package com.libreria.jorge.controller;

import com.libreria.jorge.dto.MensajeResponse;
import com.libreria.jorge.model.Libro;
import com.libreria.jorge.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<?> listarLibros() {
        List<Libro> libros = libroService.listarTodos();
        if (libros.isEmpty()) {
            return ResponseEntity.ok(new MensajeResponse("⚠️ No hay libros registrados"));
        }
        return ResponseEntity.ok(libros);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerLibro(@PathVariable Long id) {
        Optional<Libro> opt = libroService.buscarPorId(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get()); // Devuelve el libro si existe
        } else {
            return ResponseEntity.status(404)
                    .body(new MensajeResponse("⚠️ Libro no encontrado"));
        }
    }

    // Crear libro
    @PostMapping
    public ResponseEntity<?> crearLibro(@RequestBody Libro libro) {
        libroService.guardar(libro);
        return ResponseEntity.status(201)
                .body(new MensajeResponse("✅ Libro creado correctamente"));
    }

    // Actualizar libro
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        try {
            libroService.actualizar(id, libro);
            return ResponseEntity.ok(new MensajeResponse("✏️ Libro actualizado correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404)
                    .body(new MensajeResponse("⚠️ Libro no encontrado"));
        }
    }

    // Eliminar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Long id) {
        try {
            libroService.eliminar(id);
            return ResponseEntity.ok(new MensajeResponse("🗑️ Libro eliminado correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404)
                    .body(new MensajeResponse("⚠️ Libro no encontrado"));
        }
    }

    // ========================
    // Endpoints con Query Params
    // ========================

    @GetMapping("/buscar/autor")
    public ResponseEntity<?> buscarPorAutor(@RequestParam String autor) {
        List<Libro> libros = libroService.buscarPorAutor(autor);
        if (libros.isEmpty()) {
            return ResponseEntity.ok(new MensajeResponse("⚠️ No se encontraron libros del autor: " + autor));
        }
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/buscar/genero")
    public ResponseEntity<?> buscarPorGenero(@RequestParam String genero) {
        List<Libro> libros = libroService.buscarPorGenero(genero);
        if (libros.isEmpty()) {
            return ResponseEntity.ok(new MensajeResponse("⚠️ No se encontraron libros del género: " + genero));
        }
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/buscar/precio")
    public ResponseEntity<?> buscarPorPrecioMenor(@RequestParam Double precio) {
        List<Libro> libros = libroService.buscarPorPrecioMenor(precio);
        if (libros.isEmpty()) {
            return ResponseEntity.ok(new MensajeResponse("⚠️ No se encontraron libros con precio menor a " + precio));
        }
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/buscar/stock")
    public ResponseEntity<?> buscarConStockMayor(@RequestParam Integer stock) {
        List<Libro> libros = libroService.buscarConStockMayor(stock);
        if (libros.isEmpty()) {
            return ResponseEntity.ok(new MensajeResponse("⚠️ No se encontraron libros con stock mayor a " + stock));
        }
        return ResponseEntity.ok(libros);
    }
}

