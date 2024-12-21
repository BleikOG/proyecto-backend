package com.proyectoIOS.ApiProyecto.service;

import com.proyectoIOS.ApiProyecto.model.Usuario;
import com.proyectoIOS.ApiProyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Registro de usuario
    public String registrarUsuario(Usuario usuario) {
        // Verificar si el nombre de usuario ya está en uso
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(usuario.getUsuario());
        if (usuarioExistente.isPresent()) {
            return "El nombre de usuario ya está en uso.";
        }

        // Verificar si el correo electrónico ya está en uso
        Optional<Usuario> usuarioPorCorreo = usuarioRepository.findByCorreoElectronico(usuario.getCorreoElectronico());
        if (usuarioPorCorreo.isPresent()) {
            return "El correo electrónico ya está en uso.";
        }

        // Guardar el nuevo usuario
        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente.";
    }

    // Login de usuario
    public Object loginUsuario(String usuario, String contrasenia) {
        // Buscar el usuario por nombre de usuario
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuario(usuario);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioEncontrado = usuarioOptional.get();

            // Comparar las contraseñas de forma directa
            if (usuarioEncontrado.getContrasenia().equals(contrasenia)) {
                // Eliminar la contraseña antes de retornar los datos
                usuarioEncontrado.setContrasenia(null);
                return usuarioEncontrado; // Devuelve el usuario sin la contraseña
            } else {
                return "Contraseña incorrecta.";
            }
        } else {
            return "Usuario no encontrado.";
        }
    }

    // Eliminar usuario por ID
    public String eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuario eliminado correctamente.";
        }
        return "Usuario no encontrado.";
    }

    // Actualizar usuario
    public String actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setUsuario(usuarioActualizado.getUsuario());
            usuario.setContrasenia(usuarioActualizado.getContrasenia());
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellido(usuarioActualizado.getApellido());
            usuario.setCorreoElectronico(usuarioActualizado.getCorreoElectronico());
            usuarioRepository.save(usuario);
            return "Usuario actualizado correctamente.";
        }
        return "Usuario no encontrado.";
    }
}
