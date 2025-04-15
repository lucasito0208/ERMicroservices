package com.lucasDev.auth.repository;

import com.lucasDev.auth.model.RolTipo;
import com.lucasDev.auth.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<RolTipo> getRolById(Long id);



}
