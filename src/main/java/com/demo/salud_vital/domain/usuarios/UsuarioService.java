package com.demo.salud_vital.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void sincronizarUsuario(String email) {
        if (usuarioRepository.findByLogin(email) == null) {
            // Si el usuario no existe, lo creamos en la base de datos
            Usuario usuario = new Usuario();
            usuario.setLogin(email);
            usuarioRepository.save(usuario);
        }
    }
}