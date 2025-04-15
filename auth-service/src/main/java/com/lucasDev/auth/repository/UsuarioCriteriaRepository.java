package com.lucasDev.auth.repository;

import com.lucasDev.auth.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Usuario> buscarPorEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = query.from(Usuario.class);

        query.select(usuario).where(cb.equal(usuario.get("email"), email));
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultStream().findFirst();

    }

    public List<Usuario> listarUsuariosActivos() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = query.from(Usuario.class);

        query.select(usuario).where(cb.isTrue(usuario.get("activo")));

        return entityManager.createQuery(query).getResultList();
    }

    // Ejemplo de consulta dinámica con Criteria API
    public List<Usuario> buscarUsuario(String nombre, String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = query.from(Usuario.class);

        Predicate predicate = cb.conjunction();

        if (nombre != null && !nombre.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(usuario.get("nombre"), nombre));
        }

        if (email != null && !email.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(usuario.get("email"), email));
        }

        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }



}
