package com.proyectoIOS.ApiProyecto.controller;

import com.proyectoIOS.ApiProyecto.dtos.LoginRequest;
import com.proyectoIOS.ApiProyecto.model.Usuario;
import com.proyectoIOS.ApiProyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Registro de usuario
    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody Usuario usuario) {
        String resultado = usuarioService.registrarUsuario(usuario);
        if (resultado.equals("Usuario registrado exitosamente.")) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.badRequest().body(resultado);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        String usuario = loginRequest.getUsuario();
        String contrasenia = loginRequest.getContrasenia();

        // Llamar al servicio para verificar el login
        Object respuesta = usuarioService.loginUsuario(usuario, contrasenia);

        if (respuesta instanceof Usuario) {
            return ResponseEntity.ok(respuesta);  // Devuelve los detalles del usuario sin la contraseña
        }
        return ResponseEntity.badRequest().body(respuesta);
    }

    // Eliminar usuario por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        String resultado = usuarioService.eliminarUsuario(id);
        if (resultado.equals("Usuario eliminado correctamente.")) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.badRequest().body(resultado);
    }

    // Actualizar usuario
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        String resultado = usuarioService.actualizarUsuario(id, usuario);
        if (resultado.equals("Usuario actualizado correctamente.")) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.badRequest().body(resultado);
    }
}
