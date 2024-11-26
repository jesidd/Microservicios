package com.ecomerce.ma.usuario.service;
import com.ecomerce.ma.usuario.model.DAO.UsuarioDAO;
import com.ecomerce.ma.usuario.model.DTO.SaveUsuarioDTO;
import com.ecomerce.ma.usuario.model.DTO.UsuarioDTO;
import com.ecomerce.ma.usuario.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public UsuarioDTO registrarUsuario(SaveUsuarioDTO saveUsuarioDTO) {
        Usuario usuario = new Usuario(
                saveUsuarioDTO.getNombre(),
                saveUsuarioDTO.getCorreo(),
                saveUsuarioDTO.getContrasena());
        Usuario usuarioCreado = usuarioDAO.crearUsuario(usuario);
        return new UsuarioDTO(
                usuarioCreado.getId(),
                usuarioCreado.getNombre(),
                usuarioCreado.getCorreo(),
                usuarioCreado.getContrasena());
    }


    public UsuarioDTO login(String username, String password) {

        Usuario usuario = usuarioDAO.loginUsuario(username, password);
        if (usuario != null) {return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getContrasena()
        );}
        return null;
    }

    public UsuarioDTO obtenerUsuarioPorId(int id) {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getCorreo(),
            usuario.getContrasena()
        );
    }
}
