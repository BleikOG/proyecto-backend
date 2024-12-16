package com.proyectoIOS.ApiProyecto.controller;

import com.proyectoIOS.ApiProyecto.model.Notas;
import com.proyectoIOS.ApiProyecto.service.NotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notas")
public class NotasController {

    @Autowired
    private NotasService notasService;

    // Obtener todas las notas
    @GetMapping
    public ResponseEntity<List<Notas>> getAllNotas() {
        List<Notas> notas = notasService.getAllNotas();
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }

    // Obtener una nota por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Notas> getNotaById(@PathVariable Integer id) {
        Optional<Notas> nota = notasService.getNotaById(id);
        return nota.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Guardar una nueva nota
    @PostMapping
    public ResponseEntity<Notas> saveNota(@RequestBody Notas nota) {
        Notas savedNota = notasService.saveNota(nota);
        return new ResponseEntity<>(savedNota, HttpStatus.CREATED);
    }

    // Eliminar una nota por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Integer id) {
        notasService.deleteNota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notas> updateNota(@PathVariable Integer id, @RequestBody Notas notaDetails) {
        try {
            Notas updatedNota = notasService.updateNota(id, notaDetails);
            return new ResponseEntity<>(updatedNota, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Notas>> buscarPorTitulo(@RequestParam String titulo) {
        List<Notas> notas = notasService.getNotasByTitulo(titulo);

        if (notas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }

}
