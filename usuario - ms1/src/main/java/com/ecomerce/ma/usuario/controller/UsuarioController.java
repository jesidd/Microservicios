package com.ecomerce.ma.usuario.controller;

import com.ecomerce.ma.usuario.model.DTO.SaveUsuarioDTO;
import com.ecomerce.ma.usuario.model.DTO.UsuarioDTO;
import com.ecomerce.ma.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/id")
    public UsuarioDTO getUsuario(@PathVariable long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody SaveUsuarioDTO saveUsuarioDTO) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.registrarUsuario(saveUsuarioDTO);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioDTO usuario) {
        UsuarioDTO usuario1 = usuarioService.login(usuario.getCorreo(), usuario.getContrasena());
        if (usuario1 != null) {
            usuario1.setContrasena(null);
            return ResponseEntity.ok(usuario1);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
