package com.ecomerce.ma.usuario.model.DAO;

import com.ecomerce.ma.usuario.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Usuario crearUsuario(Usuario usuario) {
        entityManager.persist(usuario);
        return usuario;
    }

    public Usuario loginUsuario(String username, String password) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM Usuario u WHERE u.correo  = :username AND u.contrasena = :password",
                            Usuario.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return entityManager.find(Usuario.class, id);
    }
}
