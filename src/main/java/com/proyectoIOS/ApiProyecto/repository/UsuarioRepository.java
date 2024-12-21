package com.proyectoIOS.ApiProyecto.repository;

import com.proyectoIOS.ApiProyecto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por nombre de usuario
    Optional<Usuario> findByUsuario(String usuario);

    // Buscar usuario por correo electrónico
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);

    // Buscar usuario por ID
    Optional<Usuario> findById(Long id);

    // Eliminar usuario por ID
    void deleteById(Long id);
}
