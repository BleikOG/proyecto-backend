package com.proyectoIOS.ApiProyecto.repository;

import com.proyectoIOS.ApiProyecto.model.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Integer> {

    // Buscar notas cuyo título coincida exactamente
    List<Notas> findByTitulo(String titulo);

    // Buscar notas cuyo título contenga una palabra clave (búsqueda parcial)
    List<Notas> findByTituloContaining(String titulo);
}
