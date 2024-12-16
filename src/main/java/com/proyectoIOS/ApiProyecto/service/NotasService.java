package com.proyectoIOS.ApiProyecto.service;

import com.proyectoIOS.ApiProyecto.model.Notas;
import com.proyectoIOS.ApiProyecto.repository.NotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotasService {

    @Autowired
    private NotasRepository notasRepository;

    // Obtener todas las notas
    public List<Notas> getAllNotas() {
        return notasRepository.findAll();
    }

    // Obtener una nota por su ID
    public Optional<Notas> getNotaById(Integer id) {
        return notasRepository.findById(id);
    }

    // Guardar una nueva nota
    public Notas saveNota(Notas nota) {
        return notasRepository.save(nota);
    }

    // Actualizar una nota existente
    public Notas updateNota(Integer id, Notas notaDetails) {
        Optional<Notas> optionalNota = notasRepository.findById(id);

        if (optionalNota.isPresent()) {
            Notas existingNota = optionalNota.get();
            existingNota.setTitulo(notaDetails.getTitulo());
            existingNota.setDescripcion(notaDetails.getDescripcion());
            existingNota.setColor(notaDetails.getColor());

            return notasRepository.save(existingNota);
        } else {
            throw new RuntimeException("Nota no encontrada con ID: " + id);
        }
    }

    // Eliminar una nota por su ID
    public void deleteNota(Integer id) {
        notasRepository.deleteById(id);
    }
    // Buscar notas por título
    public List<Notas> getNotasByTitulo(String titulo) {
        return notasRepository.findByTituloContaining(titulo);
    }
}
