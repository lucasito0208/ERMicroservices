package com.lucasDev.auth.repository;

import com.lucasDev.auth.model.RolTipo;
import com.lucasDev.auth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> getUsuarioByNombre(String usuario);
    Optional<Usuario> getUsuarioById(Long id);
    Optional<Usuario> findByEmail(String email);

    @Query("Select u from Usuario u join u.roles r where r.tipo = :rolTipo")
    List<Usuario> getUsuariosByRol(@Param("rolTipo")RolTipo rolTipo);

    // Ejemplo con @Query
    @Query("Select u from Usuario u where u.email = ?1")
    Optional<Usuario> buscarPorEmail(String email);

}
